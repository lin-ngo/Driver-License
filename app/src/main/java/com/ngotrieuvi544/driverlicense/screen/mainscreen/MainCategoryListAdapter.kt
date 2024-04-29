package com.ngotrieuvi544.driverlicense.screen.mainscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ngotrieuvi544.driverlicense.data.model.MainCategoryModel
import com.ngotrieuvi544.driverlicense.databinding.ItemMainScreenCategoryLayoutBinding
import com.ngotrieuvi544.driverlicense.utils.OnClickItem
import com.ngotrieuvi544.driverlicense.utils.base.BaseRecyclerViewAdapter
import com.ngotrieuvi544.driverlicense.utils.base.BaseViewHolder

class MainCategoryListAdapter :
    BaseRecyclerViewAdapter<MainCategoryModel, ItemMainScreenCategoryLayoutBinding,
            MainCategoryListAdapter.ViewHolder>(MainCategoryModel.getDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMainScreenCategoryLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(
        override val binding: ItemMainScreenCategoryLayoutBinding,
    ) : BaseViewHolder<MainCategoryModel, ItemMainScreenCategoryLayoutBinding>(binding) {
        override fun onBindData(data: MainCategoryModel) {
            binding.apply {
                imageCategory.setImageResource(data.resourceID)
                textCategory.text = data.title

                itemCategory.setOnClickListener {
                    clickItemInterface?.let { function -> function(data) }
                }
            }
        }
    }

    override fun registerOnClickItemEvent(onClickItem: OnClickItem<MainCategoryModel>) {
        this.clickItemInterface = onClickItem
    }
}
