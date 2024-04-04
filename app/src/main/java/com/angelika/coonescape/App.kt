package com.angelika.coonescape

import android.app.Application
import android.content.Context
import com.angelika.rickmortyapi.utils.MusicManager

class App : Application() {

    private var context: Context? = null

    override fun onCreate() {
        super.onCreate()
        context = this
        initPreference()
    }

    private fun initPreference() {
        val preferenceHelper = MusicManager()
        preferenceHelper.getSelectedMusic(this)
    }
}