package com.angelika.coonescape.ui.utils

import android.content.Context

object ThemeManager {

    enum class Theme {
        THEME_1,
        THEME_2,
        THEME_3
    }

    private const val THEME_PREFERENCE_KEY = "selected_theme"

    fun saveSelectedTheme(context: Context, selectedTheme: Theme) {
        val sharedPreferences = context.getSharedPreferences("theme_preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(THEME_PREFERENCE_KEY, selectedTheme.name)
        editor.apply()
    }

    fun getSavedTheme(context: Context): Theme {
        val sharedPreferences = context.getSharedPreferences("theme_preferences", Context.MODE_PRIVATE)
        val themeName = sharedPreferences.getString(THEME_PREFERENCE_KEY, Theme.THEME_1.name)
        return Theme.valueOf(themeName ?: Theme.THEME_1.name)
    }
}