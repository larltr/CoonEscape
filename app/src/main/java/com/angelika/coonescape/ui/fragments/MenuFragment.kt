package com.angelika.coonescape.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.angelika.coonescape.R
import com.angelika.coonescape.databinding.FragmentMenuBinding

class MenuFragment : Fragment(R.layout.fragment_menu) {

    private val binding by viewBinding(FragmentMenuBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transition()
    }

    private fun transition() = with(binding) {
        btnPlay.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_descriptionFragment2)
        }
        btnChooseTheme.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_themeFragment)
        }
    }
}