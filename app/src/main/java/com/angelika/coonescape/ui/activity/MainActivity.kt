package com.angelika.coonescape.ui.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.angelika.coonescape.R
import com.angelika.coonescape.ui.utils.ThemeManager

class MainActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_main)

        playDefaultMusic()
        registerMusicReceiver()
        colorStatusBar()
        applySavedTheme()
    }

    private fun colorStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        }
    }

    private fun playDefaultMusic() {
        mediaPlayer = MediaPlayer.create(this, R.raw.first_sound)
        mediaPlayer?.isLooping = true
        mediaPlayer?.start()
    }

    fun applySavedTheme() {
        val savedTheme = ThemeManager.getSavedTheme(this)
        when (savedTheme) {
            ThemeManager.Theme.THEME_1 -> {
                findViewById<androidx.fragment.app.FragmentContainerView>(R.id.nav_host_fragment).setBackgroundResource(
                    R.drawable.image_theme_first
                )
            }

            ThemeManager.Theme.THEME_2 -> {
                findViewById<androidx.fragment.app.FragmentContainerView>(R.id.nav_host_fragment).setBackgroundResource(
                    R.drawable.image_theme_second
                )
            }

            ThemeManager.Theme.THEME_3 -> {
                findViewById<androidx.fragment.app.FragmentContainerView>(R.id.nav_host_fragment).setBackgroundResource(
                    R.drawable.image_theme_third
                )
            }
        }
    }

    private fun registerMusicReceiver() {
        val intentFilter = IntentFilter("music_selected")
        LocalBroadcastManager.getInstance(this).registerReceiver(musicReceiver, intentFilter)
    }

    private val musicReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            try {
                val musicResId = intent.getIntExtra("music_res_id", R.raw.first_sound)
                mediaPlayer?.stop()
                mediaPlayer?.release()
                mediaPlayer = MediaPlayer.create(context, musicResId)
                mediaPlayer?.start()
                mediaPlayer?.isLooping = true
            } catch (e: Exception) {
                Log.e("tag", e.message.toString())
            }
        }
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer?.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(musicReceiver)
    }
}