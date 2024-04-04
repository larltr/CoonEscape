package com.angelika.coonescape.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.angelika.coonescape.R
import com.angelika.coonescape.databinding.FragmentThemeBinding
import com.angelika.coonescape.ui.activity.MainActivity
import com.angelika.coonescape.ui.utils.ThemeManager
import com.angelika.coonescape.ui.utils.MusicManager

class ThemeFragment : Fragment(R.layout.fragment_theme) {

    private val binding by viewBinding(FragmentThemeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        choosingTheme()
        choosingMusic()
        clickBack()
    }

    private fun choosingTheme() = with(binding) {
        ivThemeFirst.setOnClickListener {
            onThemeSelected(ThemeManager.Theme.THEME_1)
            ivThemeFirst.alpha = 1f
            ivThemeSecond.alpha = 0.5f
            ivThemeThird.alpha = 0.5f
        }
        ivThemeSecond.setOnClickListener {
            onThemeSelected(ThemeManager.Theme.THEME_2)
            ivThemeFirst.alpha = 0.5f
            ivThemeSecond.alpha = 1f
            ivThemeThird.alpha = 0.5f
        }
        ivThemeThird.setOnClickListener {
            onThemeSelected(ThemeManager.Theme.THEME_3)
            ivThemeFirst.alpha = 0.5f
            ivThemeSecond.alpha = 0.5f
            ivThemeThird.alpha = 1f
        }
    }

    private fun onThemeSelected(theme: ThemeManager.Theme) {
        ThemeManager.saveSelectedTheme(requireContext(), theme)
        (requireActivity() as? MainActivity)?.applySavedTheme()
    }

    private fun choosingMusic() = with(binding) {
        ivMusicFirst.setOnClickListener {
            onMusicSelected(R.raw.first_sound)
            ivMusicFirst.alpha = 1f
            ivMusicSecond.alpha = 0.5f
            ivMusicThird.alpha = 0.5f
        }
        ivMusicSecond.setOnClickListener {
            onMusicSelected(R.raw.second_sound)
            ivMusicFirst.alpha = 0.5f
            ivMusicSecond.alpha = 1f
            ivMusicThird.alpha = 0.5f
        }
        ivMusicThird.setOnClickListener {
            onMusicSelected(R.raw.third_sound)
            ivMusicFirst.alpha = 0.5f
            ivMusicSecond.alpha = 0.5f
            ivMusicThird.alpha = 1f
        }
    }

    private fun onMusicSelected(musicResId: Int) {
        MusicManager().saveSelectedMusic(requireContext(), musicResId)
        val intent = Intent("music_selected")
        intent.putExtra("music_res_id", musicResId)
        LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
    }

    private fun clickBack() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}