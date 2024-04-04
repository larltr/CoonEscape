package com.angelika.coonescape.ui.utils

import android.content.Context

class MusicManager {

    private val PREF_NAME = "music_prefs"
    private val KEY_SELECTED_MUSIC = "selected_music"

    fun saveSelectedMusic(context: Context, musicResId: Int) {
        val prefs = context.applicationContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putInt(KEY_SELECTED_MUSIC, musicResId).apply()
    }

    fun getSelectedMusic(context: Context): Int {
        val prefs = context.applicationContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getInt(KEY_SELECTED_MUSIC, -1)
    }
}