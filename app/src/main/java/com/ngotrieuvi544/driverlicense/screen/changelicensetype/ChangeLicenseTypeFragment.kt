package com.ngotrieuvi544.driverlicense.screen.changelicensetype

import com.ngotrieuvi544.driverlicense.data.model.LicenseType
import com.ngotrieuvi544.driverlicense.data.model.LicenseTypeData
import com.ngotrieuvi544.driverlicense.databinding.FragmentChangeLicenseTypeScreenBinding
import com.ngotrieuvi544.driverlicense.utils.base.BaseFragment
import com.ngotrieuvi544.driverlicense.utils.base.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangeLicenseTypeFragment :
    BaseFragment<FragmentChangeLicenseTypeScreenBinding>(FragmentChangeLicenseTypeScreenBinding::inflate) {

    override val viewModel by viewModel<ChangeLicenseTypeViewModel>()

    private val changeLicenseTypeAdapter by lazy { ChangeLicenseTypeAdapter() }

    override fun initData() {
        viewBinding.recyclerViewLicenseType.apply {
            setHasFixedSize(true)
            itemAnimator = null
            adapter = changeLicenseTypeAdapter
        }

        changeLicenseTypeAdapter.submitList(
            enumValues<LicenseType>().map { LicenseTypeData(it) }.toList())
    }

    override fun handleEvent() {
        changeLicenseTypeAdapter.registerOnClickItemEvent {
            viewModel.onChangingToNewLicenseType(it.licenseType)
        }
    }

    override fun bindData() {
        viewModel.currentLicenseType.observe(viewLifecycleOwner) {
            changeLicenseTypeAdapter.setCurrentLicenseType(it) { selectedPosition ->
                viewBinding.recyclerViewLicenseType.scrollToPosition(selectedPosition)
            }
        }
    }
}