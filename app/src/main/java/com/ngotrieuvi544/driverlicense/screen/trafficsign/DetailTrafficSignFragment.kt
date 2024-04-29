package com.ngotrieuvi544.driverlicense.screen.trafficsign

import com.ngotrieuvi544.driverlicense.data.model.TrafficSigns
import com.ngotrieuvi544.driverlicense.databinding.FragmentDetailTrafficSignBinding
import com.ngotrieuvi544.driverlicense.utils.base.BaseFragment
import com.ngotrieuvi544.driverlicense.utils.base.BaseViewModel
import com.ngotrieuvi544.driverlicense.utils.constant.AppConstant
import com.ngotrieuvi544.driverlicense.utils.extensions.loadGlideImageFromUrl
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailTrafficSignFragment
    : BaseFragment<FragmentDetailTrafficSignBinding>(FragmentDetailTrafficSignBinding::inflate){

    override val viewModel by viewModel<BaseViewModel>()

    override fun initData() {
        (arguments?.get(AppConstant.KEY_BUNDLE_TRAFFIC_SIGN) as? TrafficSigns)?.let {
            viewBinding.apply {
                imageTrafficSign.loadGlideImageFromUrl(
                    root.context,
                    it.imageUrl,
                )

                textIdTrafficSign.text = it.id
                textTitleTrafficSign.text = it.title
                textDescriptionTrafficSign.text = it.description
            }
        }
    }

    override fun handleEvent() {
        // Not op
    }

    override fun bindData() {

    }
}