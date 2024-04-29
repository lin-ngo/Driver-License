package com.ngotrieuvi544.driverlicense.screen.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ngotrieuvi544.driverlicense.R
import com.ngotrieuvi544.driverlicense.data.model.NewQuestion
import com.ngotrieuvi544.driverlicense.data.model.TipsHighScore
import com.ngotrieuvi544.driverlicense.data.model.TrafficSigns
import com.ngotrieuvi544.driverlicense.databinding.ActivitySplashBinding
import com.ngotrieuvi544.driverlicense.screen.mainscreen.MainActivity
import com.ngotrieuvi544.driverlicense.utils.base.BaseActivity
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.java.KoinJavaComponent.inject

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {

    private val viewModel by viewModel<SplashViewModel>()

    override fun initData(savedInstanceState: Bundle?) {
        //Not-op
    }

    override fun handleEvent() {
        supportActionBar?.hide()
    }

    override fun bindData() {
        lifecycleScope.launch {
            viewModel.loadingText.observe(this@SplashActivity) {
                viewBinding.textLoading.text = it
            }

            viewModel.isLoadingDone.observe(this@SplashActivity) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }
        }

        /*lifecycleScope.launch {
            loadDataToFireBase()
        }*/
    }

    /*private fun loadDataToFireBase() {
        val fireStore = get<FirebaseFirestore>().collection("tipshighscore")

        val jsonString =
            resources.openRawResource(R.raw.tip_high_score).bufferedReader().use {
                it.readLines().joinToString(separator = "") { it.trim() }
            }

        val typeToken = object : TypeToken<MutableList<TipsHighScore>>() {}.type
        val list = Gson().fromJson<MutableList<TipsHighScore>>(jsonString, typeToken)

        list.forEach {
            fireStore.document(it.id.toString())
                .set(it)
        }
    }*/
}
