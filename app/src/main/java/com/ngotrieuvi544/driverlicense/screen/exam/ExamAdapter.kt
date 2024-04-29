package com.ngotrieuvi544.driverlicense.screen.exam

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.ngotrieuvi544.driverlicense.R
import com.ngotrieuvi544.driverlicense.data.model.Exam
import com.ngotrieuvi544.driverlicense.data.model.ExamState
import com.ngotrieuvi544.driverlicense.data.model.findCreateExamRuleByLicenseTypeString
import com.ngotrieuvi544.driverlicense.databinding.ItemExamListLayoutBinding
import com.ngotrieuvi544.driverlicense.utils.OnClickItem
import com.ngotrieuvi544.driverlicense.utils.OnClickItemPosition
import com.ngotrieuvi544.driverlicense.utils.base.BaseRecyclerViewAdapter
import com.ngotrieuvi544.driverlicense.utils.base.BaseViewHolder
import com.ngotrieuvi544.driverlicense.utils.constant.AppConstant
import com.ngotrieuvi544.driverlicense.utils.extensions.getResourceColor
import com.ngotrieuvi544.driverlicense.utils.extensions.toDateTimeDetail

class ExamAdapter :
    BaseRecyclerViewAdapter<Exam, ItemExamListLayoutBinding, ExamAdapter.ViewHolder>(Exam.getDiffCallBack()) {

    private var doExamButtonCallBack: OnClickItem<Int>? = null
    private var watchExamHistoryCallBack: OnClickItemPosition<Exam>? = null

    override fun registerOnClickItemEvent(onClickItem: OnClickItem<Exam>) {
        this.clickItemInterface = onClickItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemExamListLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    fun registerOnClickExamButtonEvent(onClickItem: OnClickItem<Int>) {
        this.doExamButtonCallBack = onClickItem
    }

    fun registerOnClickWatchExamHistoryEvent(onClickItem: OnClickItemPosition<Exam>) {
        this.watchExamHistoryCallBack = onClickItem
    }

    private fun getSelectedColor(binding: ItemExamListLayoutBinding, color: Int) =
        binding.root.context.getResourceColor(color)

    companion object {
        private const val WATCH_RESULT = "Xem lại"
        private const val CONTINUE_DOING_TEST = "Tiếp tục"
    }

    inner class ViewHolder(
        override val binding: ItemExamListLayoutBinding,
    ) : BaseViewHolder<Exam, ItemExamListLayoutBinding>(binding) {

        override fun onBindData(data: Exam) {
            binding.apply {
                textExamTitle.text = "Đề số ${adapterPosition + 1}"

                if (data.examState == ExamState.UNDEFINED.value) {
                    val examRules =
                        com.ngotrieuvi544.driverlicense.data.model.findCreateExamRuleByLicenseTypeString(
                            data.examType
                        )
                    textDescription.text = "${examRules.totalNumberOfQuestion} câu/${examRules.examDurationByMinutes} phút"
                }

                if (data.examState != ExamState.UNDEFINED.value) {
                    buttonDoExam.text = WATCH_RESULT
                    textDescription.text =
                        "Đúng ${data.numbersOfCorrectAnswer} / ${data.listQuestions.size} câu"
                    textExamState.text = if (data.examState == ExamState.PASSED.value) "ĐẠT" else "KHÔNG ĐẠT"
                }

                if(data.examState == ExamState.UNDEFINED.value &&
                    data.currentTimeStamp != AppConstant.EXAM_TEST_FULL_TIME
                    && data.currentTimeStamp != AppConstant.DEFAULT_NOT_HAVE_TIME_STAMP) {
                    buttonDoExam.text = CONTINUE_DOING_TEST
                    textDescription.text =
                        "Còn ${data.currentTimeStamp.toDateTimeDetail()}"
                    textExamState.text = "ĐANG THI"
                    if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                        root.setCardBackgroundColor(getSelectedColor(binding, R.color.yellow_pastel_low_alpha))
                    } else {
                        root.setCardBackgroundColor(getSelectedColor(binding, R.color.yellow_pastel))
                    }
                }

                val idColor = when (data.examState) {
                    ExamState.PASSED.value -> R.color.green_pastel
                    ExamState.FAILED_BY_N0T_ENOUGH_SCORE.value,
                    ExamState.FAILED_BY_MUST_NOT_WRONG_QUESTION.value -> R.color.red_pastel
                    else -> null
                }

                idColor?.let {
                    root.setCardBackgroundColor(getSelectedColor(binding, idColor))
                }

                buttonDoExam.setOnClickListener {
                    doExamButtonCallBack?.invoke(adapterPosition)
                }

                buttonWatchExamHistory.setOnClickListener {
                    watchExamHistoryCallBack?.invoke(adapterPosition, data)
                }
            }
        }
    }
}
