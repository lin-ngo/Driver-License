package com.ngotrieuvi544.driverlicense.screen.trafficsign

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.ngotrieuvi544.driverlicense.data.model.TrafficSigns
import com.ngotrieuvi544.driverlicense.databinding.FragmentTrafficSignCategoryBinding
import com.ngotrieuvi544.driverlicense.utils.OnClickItem
import com.ngotrieuvi544.driverlicense.utils.base.BaseRecyclerViewAdapter
import com.ngotrieuvi544.driverlicense.utils.base.BaseViewHolder

class TrafficSignScreenAdapter :
    BaseRecyclerViewAdapter<MutableList<TrafficSigns>, FragmentTrafficSignCategoryBinding, TrafficSignScreenAdapter.ViewHolder>(
        TrafficSignCategoryScreenDiffUtil
    ) {

    private val stateScrollList = MutableList(5) { 0 }

    private var itemClickEvent: OnClickItem<TrafficSigns>? = null

    fun setItemClickEvent(itemClickEvent: OnClickItem<TrafficSigns>) {
        this.itemClickEvent = itemClickEvent
    }

    companion object {
        val TrafficSignCategoryScreenDiffUtil =
            object : DiffUtil.ItemCallback<MutableList<TrafficSigns>>() {
                override fun areItemsTheSame(
                    oldItem: MutableList<TrafficSigns>,
                    newItem: MutableList<TrafficSigns>,
                ) = oldItem == newItem

                override fun areContentsTheSame(
                    oldItem: MutableList<TrafficSigns>,
                    newItem: MutableList<TrafficSigns>,
                ) = oldItem == newItem
            }
    }

    inner class ViewHolder(
        override val binding: FragmentTrafficSignCategoryBinding,
    ) :
        BaseViewHolder<MutableList<TrafficSigns>, FragmentTrafficSignCategoryBinding>(binding) {
        override fun onBindData(data: MutableList<TrafficSigns>) {
            val trafficSignAdapter = TrafficSignAdapter()
            binding.recyclerViewTrafficSign.adapter = trafficSignAdapter
            trafficSignAdapter.submitList(data)

            binding.recyclerViewTrafficSign.scrollToPosition(stateScrollList[adapterPosition])

            trafficSignAdapter.registerOnClickItemEvent {
                stateScrollList[adapterPosition] =
                    (binding.recyclerViewTrafficSign.layoutManager as? LinearLayoutManager)
                        ?.findFirstCompletelyVisibleItemPosition() ?: 0
                notifyDataSetChanged()

                itemClickEvent?.invoke(it)
            }
        }
    }

    override fun registerOnClickItemEvent(onClickItem: OnClickItem<MutableList<TrafficSigns>>) {
        //Not op
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentTrafficSignCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


}