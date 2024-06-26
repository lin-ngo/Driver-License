package com.ngotrieuvi544.driverlicense.screen.study

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ngotrieuvi544.driverlicense.data.model.StudyCategory
import com.ngotrieuvi544.driverlicense.databinding.ItemFragmentStudyLayoutBinding
import com.ngotrieuvi544.driverlicense.utils.OnClickItem
import com.ngotrieuvi544.driverlicense.utils.base.BaseRecyclerViewAdapter
import com.ngotrieuvi544.driverlicense.utils.base.BaseViewHolder

class StudyAdapter
    : BaseRecyclerViewAdapter<StudyCategory, ItemFragmentStudyLayoutBinding,
        StudyAdapter.ViewHolder>(StudyCategory.getDiffUtilCallBack()) {

    private var onClickPosition: ((Int) -> Unit)? = null

    fun registerOnClickPositionEvent(onClickItemPosition: (Int) -> Unit) {
        this.onClickPosition = onClickItemPosition
    }

    override fun registerOnClickItemEvent(onClickItem: OnClickItem<StudyCategory>) {
        this.clickItemInterface = onClickItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemFragmentStudyLayoutBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class ViewHolder(
        override val binding: ItemFragmentStudyLayoutBinding,
    ) : BaseViewHolder<StudyCategory, ItemFragmentStudyLayoutBinding>(binding) {
        override fun onBindData(data: StudyCategory) {
            binding.apply {
                imageQuestionsCategoryBanner.setImageResource(StudyViewModel.listDefaultStudyCategory[adapterPosition].iconResourceID)
                textQuestionCategory.text = data.title
                textNumbersOfQuestion.text = "${data.totalNumberOfQuestions} câu"
                textNumbersOfSelectedAnswerQuestion.text =
                    "${data.numbersOfSelectedQuestions}/${data.totalNumberOfQuestions}"
                progressBarNumbersOfSelectedAnswerQuestion.progress =
                    calculateProgress(data.numbersOfSelectedQuestions, data.totalNumberOfQuestions)

                root.setOnClickListener {
                    onClickPosition?.let { function -> function(adapterPosition) }
                }
            }
        }

        private fun calculateProgress(done: Int, total: Int) =
            (done.toFloat() / total.toFloat() * ONE_HUNDRED_PERCENT).toInt()
    }

    companion object {
        private const val ONE_HUNDRED_PERCENT = 100F
    }
}
