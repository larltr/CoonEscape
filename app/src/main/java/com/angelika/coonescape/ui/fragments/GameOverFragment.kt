package com.angelika.coonescape.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.angelika.coonescape.R
import com.angelika.coonescape.databinding.FragmentGameOverBinding

class GameOverFragment : Fragment(R.layout.fragment_game_over) {

    private val binding by viewBinding(FragmentGameOverBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickTryAgainAndMenu()
    }

    private fun clickTryAgainAndMenu() = with(binding) {
        btnTyAgain.setOnClickListener {
            findNavController().navigate(R.id.action_gameOverFragment_to_gameFragment)
        }
        btnMenu.setOnClickListener {
            findNavController().navigate(R.id.action_gameOverFragment_to_menuFragment)
        }
    }
}