package com.perac.marvelheroes.ui.herolist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.perac.marvelheroes.R
import com.perac.marvelheroes.databinding.FragmentHeroListBinding
import com.perac.marvelheroes.extensions.setToolbarIcon
import com.perac.marvelheroes.extensions.setToolbarTitle
import com.perac.marvelheroes.network.models.Character
import com.perac.marvelheroes.util.onScrolledToEnd
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
        setToolbarTitle(getString(R.string.app_name))
        setToolbarIcon(R.drawable.ic_action_cancel)

        viewBinding = FragmentHeroListBinding.bind(view)

        viewBinding.recyclerView.apply {
            adapter = listAdapter
            onScrolledToEnd {
                viewModel.loadMore()
            }
        }
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_list_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            activity?.finish()
            return true
        } else if (item.itemId == R.id.menu_item_search) {
            openSearchFragment()
        }
        return false
    }

    private fun openSearchFragment() {
        val action = MarvelHeroListFragmentDirections.openSearch()
        findNavController().navigate(action)
    }
}