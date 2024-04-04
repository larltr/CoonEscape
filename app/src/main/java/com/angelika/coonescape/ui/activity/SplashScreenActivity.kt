package com.angelika.coonescape.ui.activity

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.angelika.coonescape.R
import com.angelika.coonescape.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivitySplashScreenBinding::bind)

    private val progressMax = 100
    private val progressStep = 10
    private val progressDelay = 1000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)

        colorStatusBar()
        startProgressAnimation()
    }

    private fun colorStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        }
    }

    private fun startProgressAnimation() {
        CoroutineScope(Dispatchers.Main).launch {
            for (progress in 0..progressMax step progressStep) {
                binding.progressBar.progress = progress
                delay(progressDelay)
            }
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            finish()
        }
    }
}