package com.example.geotask.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.geotask.databinding.ActivitySplashScreenBinding
import com.example.geotask.ui.main.MainActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    private fun initBinding() {
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        setContentView(binding.root)
        addSplashAnimation()
    }

    private fun addSplashAnimation() {
        binding.splashLogo.alpha = 0f
        binding.splashLogo.animate().setDuration(1500).alpha(1f).withEndAction {
            startActivity(Intent(this, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            })
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }
}