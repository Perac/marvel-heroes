package com.perac.marvelheroes.ui.herolist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.perac.marvelheroes.R
import com.perac.marvelheroes.databinding.FragmentHeroListBinding
import com.perac.marvelheroes.network.models.CharacterDataWrapper
import org.koin.androidx.viewmodel.ext.android.viewModel

class MarvelHeroListFragment : Fragment(R.layout.fragment_hero_list) {

    private val viewModel by viewModel<MarvelHeroesListViewModel>()

    private lateinit var viewBinding: FragmentHeroListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentHeroListBinding.bind(view)

        viewBinding.button.setOnClickListener {
            val action = MarvelHeroListFragmentDirections.openHeroDetails("1011334")
            findNavController().navigate(action)
        }
        viewModel.liveData.observe(viewLifecycleOwner) { onHeroesListUpdated(it) }
    }

    private fun onHeroesListUpdated(characterDataWrapper: CharacterDataWrapper) {
        println(characterDataWrapper.toString())
    }
}