package com.perac.marvelheroes.ui.herodetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.perac.marvelheroes.R
import com.perac.marvelheroes.databinding.FragmentHeroDetailsBinding
import com.perac.marvelheroes.extensions.fetchImage
import com.perac.marvelheroes.network.models.Character
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * Fragment used for displaying hero details.
 */
class MarvelHeroDetailsFragment : Fragment(R.layout.fragment_hero_details) {

    private val navArgs: MarvelHeroDetailsFragmentArgs by navArgs()

    private val viewModel by viewModel<MarvelHeroDetailsViewModel> {
        parametersOf(navArgs)
    }

    private lateinit var viewBinding: FragmentHeroDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentHeroDetailsBinding.bind(view)

        viewModel.liveData.observe(viewLifecycleOwner) { onHeroDetailsReceived(it) }
    }

    private fun onHeroDetailsReceived(character: Character) {
        with(viewBinding) {
            heroImage.fetchImage(character.getImageUrl())
            heroName.text = character.name
            description.text = character.description
            associatedComics.text = character.comics.items.map {
                it.name
            }.joinToString(separator = "\n")
        }
    }
}