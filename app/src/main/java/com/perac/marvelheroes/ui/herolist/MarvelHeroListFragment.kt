package com.perac.marvelheroes.ui.herolist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.perac.marvelheroes.R
import com.perac.marvelheroes.databinding.FragmentHeroListBinding
import com.perac.marvelheroes.network.models.Character
import org.koin.androidx.viewmodel.ext.android.viewModel

class MarvelHeroListFragment : Fragment(R.layout.fragment_hero_list) {

    private val viewModel by viewModel<MarvelHeroListViewModel>()

    private lateinit var viewBinding: FragmentHeroListBinding

    private val listAdapter = MarvelHeroListAdapter {
        openHeroDetails(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentHeroListBinding.bind(view)

        viewBinding.recyclerView.adapter = listAdapter
        viewModel.liveData.observe(viewLifecycleOwner) { onHeroesListUpdated(it) }
    }

    private fun onHeroesListUpdated(characterList: List<Character>) {
        listAdapter.submitList(characterList)
    }

    private fun openHeroDetails(heroId: String) {
        val action = MarvelHeroListFragmentDirections.openHeroDetails(heroId)
        findNavController().navigate(action)
    }
}