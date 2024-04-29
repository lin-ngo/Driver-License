package com.ngotrieuvi544.driverlicense.screen.changelicensetype

import android.util.TypedValue
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.ngotrieuvi544.driverlicense.R
import com.ngotrieuvi544.driverlicense.data.model.LicenseType
import com.ngotrieuvi544.driverlicense.data.model.LicenseTypeData
import com.ngotrieuvi544.driverlicense.databinding.ItemLicenseTypeScreenBinding
import com.ngotrieuvi544.driverlicense.utils.OnClickItem
import com.ngotrieuvi544.driverlicense.utils.base.BaseRecyclerViewAdapter
import com.ngotrieuvi544.driverlicense.utils.base.BaseViewHolder
import com.ngotrieuvi544.driverlicense.utils.constant.AppConstant
import com.ngotrieuvi544.driverlicense.utils.extensions.getCurrentThemeCardColor
import com.ngotrieuvi544.driverlicense.utils.extensions.getCurrentThemeTextColor
import com.ngotrieuvi544.driverlicense.utils.extensions.getResourceColor
import com.ngotrieuvi544.driverlicense.utils.extensions.getSelectedColor


class ChangeLicenseTypeAdapter
    : BaseRecyclerViewAdapter<LicenseTypeData, ItemLicenseTypeScreenBinding,
        ChangeLicenseTypeAdapter.ViewHolder>(getDiffUtilCallback()) {


    override fun registerOnClickItemEvent(onClickItem: OnClickItem<LicenseTypeData>) {
        this.clickItemInterface = onClickItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(inflateViewBinding(ItemLicenseTypeScreenBinding::inflate, parent))

    fun setCurrentLicenseType(licenseType: LicenseType, notifySelectedPosition: (Int) -> Unit) {
        val oldSelectedPosition = currentList.indexOfFirst { it.isSelected }
        val newSelectedPosition = currentList.indexOfFirst { it.licenseType == licenseType }

        val newStateList = currentList.toMutableList()

        if (oldSelectedPosition != AppConstant.NONE_POSITION
            && oldSelectedPosition != newSelectedPosition) {

            newStateList[oldSelectedPosition] =
                newStateList[oldSelectedPosition].copy(isSelected = false)
            submitList(newStateList)
            notifyItemChanged(oldSelectedPosition)
        }

        newStateList[newSelectedPosition] =
            newStateList[newSelectedPosition].copy(isSelected = true)
        submitList(newStateList)
        notifyItemChanged(newSelectedPosition)
        notifySelectedPosition(newSelectedPosition)
    }

    companion object {
        fun getDiffUtilCallback() = object : DiffUtil.ItemCallback<LicenseTypeData>() {
            override fun areItemsTheSame(oldItem: LicenseTypeData, newItem: LicenseTypeData) =
                oldItem.licenseType.type == newItem.licenseType.type

            override fun areContentsTheSame(oldItem: LicenseTypeData, newItem: LicenseTypeData) =
                oldItem == newItem
        }
    }

    inner class ViewHolder(
        override val binding: ItemLicenseTypeScreenBinding,
    ) : BaseViewHolder<LicenseTypeData, ItemLicenseTypeScreenBinding>(binding) {

        override fun onBindData(data: LicenseTypeData) = with(binding) {
            textLicenseType.text = "Hạng ${data.licenseType.type}"
            textDescriptionLicenseType.text = data.licenseType.description

            if (data.isSelected) {
                textLicenseType.setTextColor(getSelectedColor(R.color.white))
                textDescriptionLicenseType.setTextColor(getSelectedColor(R.color.white))
                root.setCardBackgroundColor(getSelectedColor(R.color.primary_color))
            } else {
                val currentTextColor = getCurrentThemeTextColor()
                val currentBackgroundColor = getCurrentThemeCardColor()
                textLicenseType.setTextColor(currentTextColor)
                textDescriptionLicenseType.setTextColor(currentTextColor)
                root.setCardBackgroundColor(currentBackgroundColor)
            }

            root.setOnClickListener {
                clickItemInterface?.invoke(data)
            }
        }
    }
}