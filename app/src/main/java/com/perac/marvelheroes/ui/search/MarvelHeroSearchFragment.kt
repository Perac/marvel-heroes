package com.perac.marvelheroes.ui.search

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.perac.marvelheroes.R
import com.perac.marvelheroes.databinding.FragmentHeroSearchBinding
import com.perac.marvelheroes.extensions.setToolbarIcon
import com.perac.marvelheroes.extensions.setToolbarTitle
import com.perac.marvelheroes.network.models.Character
import com.perac.marvelheroes.ui.herolist.MarvelHeroListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MarvelHeroSearchFragment : Fragment(R.layout.fragment_hero_search) {

    private val viewModel by viewModel<MarvelHeroSearchViewModel>()

    private lateinit var viewBinding: FragmentHeroSearchBinding

    private val listAdapter = MarvelHeroListAdapter {
        openHeroDetails(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarTitle(getString(R.string.app_name))
        setToolbarIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)

        viewBinding = FragmentHeroSearchBinding.bind(view)

        viewBinding.recyclerView.adapter = listAdapter
        viewModel.liveData.observe(viewLifecycleOwner) { onHeroesListUpdated(it) }

        viewBinding.searchEditText.doOnTextChanged { text, start, before, count ->
            viewModel.updateSearchTerm(text?.toString() ?: "")
        }
    }

    private fun onHeroesListUpdated(characterList: List<Character>) {
        listAdapter.submitList(characterList)
    }

    /**
     * Method used to navigate to hero details for given [heroId].
     */
    private fun openHeroDetails(heroId: String) {
        val action = MarvelHeroSearchFragmentDirections.openHeroDetails(heroId)
        findNavController().navigate(action)
    }
}