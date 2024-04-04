package com.angelika.coonescape.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.angelika.coonescape.R
import com.angelika.coonescape.databinding.FragmentPauseBinding

class PauseFragment : Fragment(R.layout.fragment_pause) {

    private val binding by viewBinding(FragmentPauseBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transition()
    }

    private fun transition() = with(binding) {
        btnBack.setOnClickListener {
            requireActivity().finish()
        }
        btnMenu.setOnClickListener {
            findNavController().navigateUp()
        }
        btnAgain.setOnClickListener {
            findNavController().navigate(R.id.action_pauseFragment_to_gameFragment)
        }
    }
}