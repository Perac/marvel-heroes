package com.perac.marvelheroes.ui.herolist

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.perac.marvelheroes.R
import com.perac.marvelheroes.databinding.FragmentHeroListBinding
import com.perac.marvelheroes.extensions.setToolbarIcon
import com.perac.marvelheroes.extensions.setToolbarTitle
import com.perac.marvelheroes.network.models.Character
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Fragment used to display Marvel heroes list.
 */
class MarvelHeroListFragment : Fragment(R.layout.fragment_hero_list) {

    private val viewModel by viewModel<MarvelHeroListViewModel>()

    private lateinit var viewBinding: FragmentHeroListBinding

    private val listAdapter = MarvelHeroListAdapter {
        openHeroDetails(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setToolbarTitle("")
        setToolbarIcon(com.google.android.material.R.drawable.ic_m3_chip_close)

        viewBinding = FragmentHeroListBinding.bind(view)

        viewBinding.recyclerView.adapter = listAdapter
        viewModel.liveData.observe(viewLifecycleOwner) { onHeroesListUpdated(it) }
    }

    private fun onHeroesListUpdated(characterList: List<Character>) {
        listAdapter.submitList(characterList)
    }

    /**
     * Method used to navigate to hero details for given [heroId].
     */
    private fun openHeroDetails(heroId: String) {
        val action = MarvelHeroListFragmentDirections.openHeroDetails(heroId)
        findNavController().navigate(action)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            activity?.finish()
            return true
        }
        return false
    }
}