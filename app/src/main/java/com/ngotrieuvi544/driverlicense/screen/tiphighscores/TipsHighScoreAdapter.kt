package com.ngotrieuvi544.driverlicense.screen.tiphighscores

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.ngotrieuvi544.driverlicense.data.model.TipsHighScore
import com.ngotrieuvi544.driverlicense.databinding.ItemTipsHighScoreLayoutBinding
import com.ngotrieuvi544.driverlicense.utils.OnClickItem
import com.ngotrieuvi544.driverlicense.utils.base.BaseRecyclerViewAdapter
import com.ngotrieuvi544.driverlicense.utils.base.BaseViewHolder
import com.ngotrieuvi544.driverlicense.utils.extensions.processEndline

class TipsHighScoreAdapter :
    BaseRecyclerViewAdapter<TipsHighScore, ItemTipsHighScoreLayoutBinding, TipsHighScoreAdapter.ViewHolder>(
        TipsHighScore.getDiffCallBack()) {

    override fun registerOnClickItemEvent(onClickItem: OnClickItem<TipsHighScore>) {
        this.clickItemInterface = onClickItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTipsHighScoreLayoutBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(
        override val binding: ItemTipsHighScoreLayoutBinding,
    ) : BaseViewHolder<TipsHighScore, ItemTipsHighScoreLayoutBinding>(binding) {
        override fun onBindData(data: TipsHighScore) {
            binding.apply {
                textTipsTitle.text = data.title
                textTipsContent.text = data.content.processEndline()

                if (data.isVisible) {
                    binding.buttonSpanDetailContent.rotation = DEGREE_0
                    binding.expandableView.isVisible = true
                } else {
                    binding.buttonSpanDetailContent.rotation = DEGREE_180
                    binding.expandableView.isVisible = false
                }

                root.setOnClickListener {
                    notifyItemChanged(adapterPosition)
                    data.isVisible = !data.isVisible
                    data.visibleWithAnimation()
                }

                buttonSpanDetailContent.setOnClickListener {
                    notifyItemChanged(adapterPosition)
                    data.isVisible = !data.isVisible
                    data.visibleWithAnimation()
                }
            }
        }

        private fun TipsHighScore.visibleWithAnimation() {
            if (this.isVisible) {
                binding.buttonSpanDetailContent.animate().rotation(DEGREE_0).start()
                binding.expandableView.isVisible = true
            } else {
                binding.buttonSpanDetailContent.animate().rotation(DEGREE_180).start()
                binding.expandableView.isVisible = false
            }
        }
    }

    companion object {
        const val DEGREE_0 = 0F
        const val DEGREE_180 = 180F
    }
}
