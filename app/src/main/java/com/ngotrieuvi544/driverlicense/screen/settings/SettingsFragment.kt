package com.ngotrieuvi544.driverlicense.screen.settings

import android.util.Log
import com.ngotrieuvi544.driverlicense.databinding.FragmentSettingsBinding
import com.ngotrieuvi544.driverlicense.utils.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    override val viewModel by sharedViewModel<SettingsViewModel>()

    override fun initData() {
        viewBinding.switchDarkMode.isChecked = viewModel.isDarkModeOn.value ?: false
    }

    override fun handleEvent() {
        viewBinding.switchDarkMode.setOnCheckedChangeListener { _, _ ->
            if (viewBinding.switchDarkMode.isChecked.not()) {
                viewModel.turnOffDarkMode()
            } else {
                viewModel.turnOnDarkMode()
            }
        }
    }

    override fun bindData() {
        //Not-op
    }
}
