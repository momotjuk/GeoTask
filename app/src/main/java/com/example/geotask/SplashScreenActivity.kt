package com.example.geotask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.geotask.databinding.ActivitySplashScreenBinding

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
            startActivity(Intent(this,MainActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }
    }
}