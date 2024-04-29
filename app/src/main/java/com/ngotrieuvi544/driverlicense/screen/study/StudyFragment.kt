package com.ngotrieuvi544.driverlicense.screen.study

import androidx.navigation.fragment.findNavController
import com.ngotrieuvi544.driverlicense.R
import com.ngotrieuvi544.driverlicense.databinding.FragmentStudyBinding
import com.ngotrieuvi544.driverlicense.utils.base.BaseFragment
import com.ngotrieuvi544.driverlicense.utils.base.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class StudyFragment : BaseFragment<FragmentStudyBinding>(FragmentStudyBinding::inflate) {

    override val viewModel by sharedViewModel<StudyViewModel>()

    private val baseViewModel by sharedViewModel<BaseViewModel>()

    private val studyAdapter by lazy { StudyAdapter() }

    override fun initData() {
        baseViewModel.setVisibleResetButton(true)
        viewBinding.recyclerViewQuestionCategory.adapter = studyAdapter
    }

    override fun handleEvent() {
        studyAdapter.registerOnClickPositionEvent {
            viewModel.setCurrentStudyCategoryPosition(it)
            findNavController().navigate(R.id.action_nav_study_to_nav_detail_study)
        }
    }

    override fun bindData() {
        viewModel.listStudyCategory.observe(viewLifecycleOwner) {
            studyAdapter.submitList(it.map { it.copy() })
        }
    }

    override fun onDestroyView() {
        baseViewModel.setVisibleResetButton(false)
        super.onDestroyView()
    }
}
