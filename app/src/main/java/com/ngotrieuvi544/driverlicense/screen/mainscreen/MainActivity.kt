package com.ngotrieuvi544.driverlicense.screen.mainscreen


import android.app.AlertDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ngotrieuvi544.driverlicense.R
import com.ngotrieuvi544.driverlicense.databinding.ActivityMainBinding
import com.ngotrieuvi544.driverlicense.screen.exam.ExamViewModel
import com.ngotrieuvi544.driverlicense.screen.instruction.InstructionViewModel
import com.ngotrieuvi544.driverlicense.screen.settings.SettingsViewModel
import com.ngotrieuvi544.driverlicense.screen.study.StudyViewModel
import com.ngotrieuvi544.driverlicense.utils.base.BaseActivity
import com.ngotrieuvi544.driverlicense.utils.base.BaseViewModel
import com.ngotrieuvi544.driverlicense.utils.constant.AppConstant
import com.ngotrieuvi544.driverlicense.utils.dialog.LoadingDialog
import com.ngotrieuvi544.driverlicense.utils.extensions.getCurrentLicenseType
import com.ngotrieuvi544.driverlicense.utils.extensions.showToast
import com.ngotrieuvi544.driverlicense.utils.network.ConnectivityObserver
import com.ngotrieuvi544.driverlicense.utils.network.InternetConnection
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    val baseViewModel by viewModel<BaseViewModel>()
    val studyViewModel by viewModel<StudyViewModel>()
    val examViewModel by viewModel<ExamViewModel>()
    val settingsViewModel by viewModel<SettingsViewModel>()
    val instructionViewModel by viewModel<InstructionViewModel>()

    private val internetConnectionObserver by inject<InternetConnection>()

    val currentLicenseType
        get() = inject<SharedPreferences>().value.getCurrentLicenseType()

    private val notConnectDialog by lazy {
        AlertDialog.Builder(this@MainActivity)
            .setTitle(DIALOG_TITLE)
            .setMessage(LOST_INTERNET_CONNECTION_DIALOG_MESSAGE)
            .setNegativeButton(LOST_INTERNET_CONNECTION_DIALOG_BUTTON) { _, _ -> }
            .create()
    }

    private var _hasShowNotConnectedDialogAtStart = false

    private val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(
                R.id.nav_main,
                R.id.nav_change_license_type,
                R.id.nav_exam,
                R.id.nav_study,
                R.id.nav_traffic_sign,
                R.id.nav_tips_high_score,
                R.id.nav_wrong_answer,
                R.id.nav_instruction,
                R.id.nav_settings,
            ),
            viewBinding.drawerLayout
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(
            AppConstant.HAS_SHOW_NOT_CONNECTED_DIALOG_AT_START,
            _hasShowNotConnectedDialogAtStart
        )
        super.onSaveInstanceState(outState)
    }

    private fun showNotConnnectedDialogAtStart() {
        if (internetConnectionObserver.isOnline().not()) {
            notConnectDialog.show()
            _hasShowNotConnectedDialogAtStart = true
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        // Làm như thế này để tránh việc luôn mở dialog mất mạng
        // mỗi khi đổi theme (dark mode/ light mode)

        if (savedInstanceState == null) {
            showNotConnnectedDialogAtStart()
        } else {
            val hasShowNotConnectedDialog =
                savedInstanceState.getBoolean(
                    AppConstant.HAS_SHOW_NOT_CONNECTED_DIALOG_AT_START,
                    false
                )
            _hasShowNotConnectedDialogAtStart = hasShowNotConnectedDialog
            if (hasShowNotConnectedDialog.not()) {
                showNotConnnectedDialogAtStart()
            }
        }
    }

    override fun showLoadingDialog() {
        super.showLoadingDialog()
        LoadingDialog.showLoadingDialog(this)
    }

    override fun hideLoadingDialog() {
        super.hideLoadingDialog()
        LoadingDialog.hideLoadingDialog()
    }

    fun updateTitleToolbar(title: String) {
        viewBinding.appBarMain.toolbar.title = title
    }

    override fun handleEvent() {
        viewBinding.apply {
            setSupportActionBar(appBarMain.toolbar)
            val navController = findNavController(R.id.nav_host_fragment_content_main)
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)

            navView.setNavigationItemSelectedListener { menuItem ->
                navController.popBackStack(R.id.nav_main, false)

                when (menuItem.itemId) {
                    R.id.nav_study -> navController.navigate(R.id.action_nav_main_to_nav_study)
                    R.id.nav_change_license_type -> navController.navigate(R.id.action_nav_main_to_nav_change_license_type)
                    R.id.nav_settings -> navController.navigate(R.id.action_nav_main_to_nav_settings)
                    R.id.nav_exam -> {
                        navController.navigate(
                            R.id.action_nav_main_to_nav_exam,
                            bundleOf(
                                AppConstant.KEY_BUNDLE_CURRENT_LICENSE_TYPE to currentLicenseType.type
                            )
                        )
                    }
                    R.id.nav_instruction ->
                        navController.navigate(R.id.action_nav_main_to_nav_instruction)
                    R.id.nav_tips_high_score ->
                        navController.navigate(R.id.action_nav_main_to_nav_tips_high_score)

                    R.id.nav_traffic_sign -> navController.navigate(R.id.action_nav_main_to_nav_traffic_sign)
                    R.id.nav_wrong_answer -> navController.navigate(R.id.action_nav_main_to_nav_wrong_answer)
                }

                drawerLayout.closeDrawers()

                return@setNavigationItemSelectedListener true
            }

            appBarMain.buttonFinishExam.setOnClickListener {
                val builder = AlertDialog.Builder(this@MainActivity)
                    .setTitle(DIALOG_TITLE)
                    .setMessage(FINISH_EXAM_DIALOG_MESSAGE)
                    .setCancelable(false)
                    .setPositiveButton(FINISH_EXAM_YES_BUTTON) { _, _ ->
                        examViewModel.processFinishExamEvent {
                            findNavController(R.id.nav_host_fragment_content_main).navigateUp()
                        }
                    }
                    .setNegativeButton(FINISH_EXAM_NO_BUTTON) { _, _ ->
                        //Not-op
                    }

                val dialog = builder.create()
                dialog.window?.attributes = WindowManager.LayoutParams().apply {
                    width = WindowManager.LayoutParams.MATCH_PARENT
                    height = WindowManager.LayoutParams.MATCH_PARENT
                }
                dialog.show()
            }

            appBarMain.buttonResetStudy.setOnClickListener {
                val builder = AlertDialog.Builder(this@MainActivity)
                    .setTitle(DIALOG_TITLE)
                    .setMessage("Bạn có muốn hủy bỏ toàn bộ trạng thái đã lựa chọn không?")
                    .setCancelable(false)
                    .setPositiveButton(FINISH_EXAM_YES_BUTTON) { _, _ ->
                        studyViewModel.resetAllStudyCategoryState()
                        showToast("Xóa trạng thái lựa chọn thành công")
                    }
                    .setNegativeButton(FINISH_EXAM_NO_BUTTON) { _, _ ->
                        //Not-op
                    }

                val dialog = builder.create()
                dialog.window?.attributes = WindowManager.LayoutParams().apply {
                    width = WindowManager.LayoutParams.MATCH_PARENT
                    height = WindowManager.LayoutParams.MATCH_PARENT
                }
                dialog.show()
            }
        }
    }

    fun addCallbackExamInfoButton(resetCallback: () -> Unit) {
        viewBinding.appBarMain.buttonInfoExamGuide.isVisible = true
        viewBinding.appBarMain.buttonInfoExamGuide.setOnClickListener {
            resetCallback()
        }
    }

    fun removeCallbackExamInfoButton() {
        viewBinding.appBarMain.buttonInfoExamGuide.isVisible = false
        viewBinding.appBarMain.buttonInfoExamGuide.setOnClickListener(null)
    }

    fun addCallbackResetExamButton(resetCallback: () -> Unit) {
        viewBinding.appBarMain.buttonResetExam.isVisible = true
        viewBinding.appBarMain.buttonResetExam.setOnClickListener {
            resetCallback()
        }
    }

    fun removeCallbackResetExamButton() {
        viewBinding.appBarMain.buttonResetExam.isVisible = false
        viewBinding.appBarMain.buttonResetExam.setOnClickListener(null)
    }

    fun addCallbackResetWrongAnswerButton(resetCallback: () -> Unit) {
        viewBinding.appBarMain.buttonResetWrongAnswer.isVisible = true
        viewBinding.appBarMain.buttonResetWrongAnswer.setOnClickListener {
            resetCallback()
        }
    }

    fun removeCallbackResetWrongAnswerButton() {
        viewBinding.appBarMain.buttonResetWrongAnswer.isVisible = false
        viewBinding.appBarMain.buttonResetWrongAnswer.setOnClickListener(null)
    }

    override fun bindData() {
        baseViewModel.isVisibleResetButton.observe(this) {
            viewBinding.appBarMain.buttonResetStudy.isVisible = it
        }

        examViewModel.isVisibleFinishExamButton.observe(this) {
            viewBinding.appBarMain.buttonFinishExam.isVisible = it
        }

        settingsViewModel.isDarkModeOn.observe(this) {
            if (it) {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
            }
        }

        lifecycleScope.launch {
            internetConnectionObserver.observe().collect { status ->
                when (status) {
                    ConnectivityObserver.Status.AVAILABLE -> {
                        if (notConnectDialog.isShowing) {
                            notConnectDialog.hide()
                        }
                    }

                    ConnectivityObserver.Status.LOST_CONNECTION -> {
                        notConnectDialog.show()
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        return if (navController.currentDestination?.id == R.id.nav_detail_exam) {
            onBackPressedDispatcher.onBackPressed()
            true
        } else navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onPause() {
        settingsViewModel.saveDarkModeState()
        super.onPause()
    }

    override fun onDestroy() {
        LoadingDialog.shutDownDialog()
        super.onDestroy()
    }

    companion object {
        private const val DIALOG_TITLE = "Thông báo"
        private const val LOST_INTERNET_CONNECTION_DIALOG_MESSAGE = "Mất kết nối mạng"
        private const val LOST_INTERNET_CONNECTION_DIALOG_BUTTON = "OK"
        private const val FINISH_EXAM_DIALOG_MESSAGE = "Bạn có muốn kết thúc bài thi này không?"
        private const val FINISH_EXAM_YES_BUTTON = "Có"
        private const val FINISH_EXAM_NO_BUTTON = "Không"
    }
}
