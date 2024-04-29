package com.ngotrieuvi544.driverlicense.screen.exam.watchhistory

import androidx.core.view.isVisible
import com.ngotrieuvi544.driverlicense.data.model.Exam
import com.ngotrieuvi544.driverlicense.databinding.FragmentExamWatchHistoryLayoutBinding
import com.ngotrieuvi544.driverlicense.screen.exam.ExamViewModel
import com.ngotrieuvi544.driverlicense.screen.mainscreen.MainActivity
import com.ngotrieuvi544.driverlicense.utils.base.BaseFragment
import com.ngotrieuvi544.driverlicense.utils.constant.AppConstant
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ExamWatchHistoryFragment
    : BaseFragment<FragmentExamWatchHistoryLayoutBinding>(FragmentExamWatchHistoryLayoutBinding::inflate) {

    override val viewModel by sharedViewModel<ExamViewModel>()

    private val examWatchHistoryAdapter by lazy { ExamWatchHistoryAdapter() }

    override fun initData() {
        val currentExam = arguments?.get(AppConstant.KEY_BUNDLE_CURRENT_EXAM) as? Exam
        val currentExamPosition = arguments?.get(AppConstant.KEY_BUNDLE_CURRENT_EXAM_POSITION) as? Int
        if(currentExam == null) {
            viewBinding.layoutVisibleWhenDataIsEmpty.root.isVisible = true
        }

        currentExam?.let {
            (activity as? MainActivity)?.updateTitleToolbar("Đề ${currentExamPosition?.plus(1)} hạng ${it.examType}")

            viewBinding.recyclerViewExamHistory.apply {
                setHasFixedSize(true)
                adapter = examWatchHistoryAdapter
            }

            examWatchHistoryAdapter.submitList(it.listExamHistory)

            if(currentExam.listExamHistory.isEmpty()) {
                viewBinding.layoutVisibleWhenDataIsEmpty.root.isVisible = true
            }
        }
    }

    override fun handleEvent() {
        //Not implement
    }

    override fun bindData() {
        //Not implement
    }
}