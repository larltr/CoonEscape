package com.angelika.coonescape.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.angelika.coonescape.R
import com.angelika.coonescape.databinding.FragmentDescriptionBinding

class DescriptionFragment : Fragment(R.layout.fragment_description) {

    private val binding by viewBinding(FragmentDescriptionBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transition()
    }

    private fun transition() {
        binding.ivButtonPlay.setOnClickListener {
            findNavController().navigate(R.id.action_descriptionFragment_to_gameFragment)
        }
    }
}