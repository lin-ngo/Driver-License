package com.ngotrieuvi544.driverlicense.screen.exam.result

import android.app.AlertDialog
import android.content.SharedPreferences
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SmoothScroller
import com.ngotrieuvi544.driverlicense.R
import com.ngotrieuvi544.driverlicense.data.model.Exam
import com.ngotrieuvi544.driverlicense.data.model.ExamState
import com.ngotrieuvi544.driverlicense.data.model.LicenseType
import com.ngotrieuvi544.driverlicense.data.model.NewQuestionWithState
import com.ngotrieuvi544.driverlicense.data.model.StateQuestionOption
import com.ngotrieuvi544.driverlicense.databinding.FragmentExamResultBinding
import com.ngotrieuvi544.driverlicense.screen.exam.ExamFragment
import com.ngotrieuvi544.driverlicense.screen.exam.ExamViewModel
import com.ngotrieuvi544.driverlicense.screen.mainscreen.MainActivity
import com.ngotrieuvi544.driverlicense.utils.base.BaseFragment
import com.ngotrieuvi544.driverlicense.utils.constant.AppConstant
import com.ngotrieuvi544.driverlicense.utils.extensions.getCurrentThemeBackgroundColor
import com.ngotrieuvi544.driverlicense.utils.extensions.getSelectedColor
import com.ngotrieuvi544.driverlicense.utils.extensions.isCurrentDarkMode
import com.ngotrieuvi544.driverlicense.utils.extensions.showToast
import com.ngotrieuvi544.driverlicense.utils.extensions.toDateTimeMMSS
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ExamResultFragment
    : BaseFragment<FragmentExamResultBinding>(FragmentExamResultBinding::inflate) {

    override val viewModel by sharedViewModel<ExamViewModel>()

    private val questionExamAdapter by lazy { ExamResultQuestionAdapter() }

    private val smoothScroller by lazy {
        object : LinearSmoothScroller(context) {
            override fun getVerticalSnapPreference(): Int {
                return SNAP_TO_START
            }
        }
    }

    private val currentLicenseType
        get() = arguments?.getString(AppConstant.KEY_BUNDLE_CURRENT_LICENSE_TYPE) ?: LicenseType.A1.type

    override fun initData() {
        (activity as? MainActivity)?.apply {
            addCallbackResetExamButton {
                val builder = AlertDialog.Builder(context)
                    .setTitle(ExamFragment.DIALOG_TITLE)
                    .setMessage("Bạn có muốn thi lại bài thi này không?")
                    .setPositiveButton(
                        "Thi ngay"
                    ) { _, _ ->
                        viewModel.resetStateCurrentExam(this@ExamResultFragment.currentLicenseType){
                            findNavController().apply {
                                popBackStack()
                                viewModel.setVisibleFinishExamButton(true)
                                navigate(R.id.action_nav_exam_to_nav_detail_exam)
                            }
                        }
                    }
                    .setNegativeButton("Thi sau") { _, _ ->
                        viewModel.resetStateCurrentExam(this@ExamResultFragment.currentLicenseType){
                            findNavController().popBackStack()
                        }
                    }
                    .setNeutralButton(ExamFragment.DIALOG_NEGATIVE_BUTTON_TEXT) { _, _ ->
                        //Not-op
                    }
                    .setCancelable(false)
                val dialog = builder.create()
                dialog.show()
            }
        }
        viewBinding.apply {
            val isDarkModeOn = inject<SharedPreferences>().value.isCurrentDarkMode()

            if (isDarkModeOn) {
                layoutExamResult.setBackgroundColor(getCurrentThemeBackgroundColor())
            } else {
                layoutExamResult.setBackgroundColor(getSelectedColor(R.color.background_color))
            }

            recyclerViewExamQuestion.apply {
                setHasFixedSize(true)
                itemAnimator = null
                adapter = questionExamAdapter
            }

        }
        viewModel.getCurrentExam()?.let { exam ->
            updateData(exam)
            viewBinding.buttonWatchExamHistory.setOnClickListener {
                findNavController().navigate(
                    R.id.action_nav_exam_result_to_nav_watch_history,
                    bundleOf(
                        AppConstant.KEY_BUNDLE_CURRENT_EXAM to exam,
                        AppConstant.KEY_BUNDLE_CURRENT_EXAM_POSITION to viewModel.currentExamPosition.value
                    )
                )
            }
        }
    }

    override fun handleEvent() {
        //Not implement
    }

    override fun bindData() {
        //Not implement
    }

    private fun updateData(exam: Exam) = with(viewBinding) {
        when (exam.examState) {
            ExamState.PASSED.value -> {
                textExamStateResult.apply {
                    text = "Đạt"
                    setBackgroundResource(R.drawable.bg_corner_20dp_green_pastel)
                }
                textExamResultDescription.text = "Bạn đã vượt qua bài kiểm tra này!!" 
            }

            ExamState.FAILED_BY_N0T_ENOUGH_SCORE.value -> {
                textExamStateResult.apply {
                    text = "Không đạt"
                    setBackgroundResource(R.drawable.bg_corner_20dp_red_pastel)
                }
                textExamResultDescription.text = "Số lượng câu đúng chưa đạt"
            }

            ExamState.FAILED_BY_MUST_NOT_WRONG_QUESTION.value  -> {
                textExamStateResult.apply {
                    text = "Không đạt"
                    setBackgroundResource(R.drawable.bg_corner_20dp_red_pastel)
                }

                textExamResultDescription.text = "Sai câu điểm liệt"
            }
        }

        textTimeDone.text = exam.timeExamDone.toDateTimeMMSS()

        textExamDone.text = "${exam.numbersOfCorrectAnswer}/${exam.listQuestionOptions.size}"

        textCorrectAnswer.text =
            exam.listQuestionOptions.count { selection -> selection.stateNumber == StateQuestionOption.CORRECT.type }
                .toString()
        textWrongAnswer.text =
            exam.listQuestionOptions.count { selection ->
                selection.stateNumber == StateQuestionOption.INCORRECT.type
                        && selection.position != AppConstant.NONE_POSITION
            }
                .toString()
        //Với các câu không trả lời được thì vị trí sẽ là -1
        textNotAnswered.text =
            exam.listQuestionOptions.count { selection -> selection.position == AppConstant.NONE_POSITION }
                .toString()

        questionExamAdapter.submitList(exam.listQuestions.map { NewQuestionWithState(it) })
        questionExamAdapter.updateQuestionStateList(exam.listQuestionOptions)

        questionExamAdapter.setUpdateCallBack {
            smoothScroller.targetPosition = it
            viewBinding.recyclerViewExamQuestion.layoutManager?.startSmoothScroll(smoothScroller)
        }
    }

    override fun onDestroyView() {
        (activity as? MainActivity)?.removeCallbackResetExamButton()
        super.onDestroyView()
    }
}