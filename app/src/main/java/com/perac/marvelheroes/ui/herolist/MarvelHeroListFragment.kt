package com.perac.marvelheroes.ui.herolist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.perac.marvelheroes.R
import com.perac.marvelheroes.databinding.FragmentHeroListBinding

class MarvelHeroListFragment : Fragment(R.layout.fragment_hero_list) {

    private lateinit var viewBinding: FragmentHeroListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentHeroListBinding.bind(view)

        viewBinding.button.setOnClickListener {
            findNavController().navigate(R.id.open_hero_details)
        }
    }
}