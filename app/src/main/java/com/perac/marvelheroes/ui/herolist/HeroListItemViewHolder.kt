package com.perac.marvelheroes.ui.herolist

import android.view.ViewGroup
import com.perac.marvelheroes.R
import com.perac.marvelheroes.base.BaseViewHolder
import com.perac.marvelheroes.databinding.ListItemMarvelHeroBinding
import com.perac.marvelheroes.extensions.fetchImageWithRoundedCorners
import com.perac.marvelheroes.network.models.Character

/**
 * ViewHolder for displaying info on one hero in a list.
 */
class HeroListItemViewHolder(
    parent: ViewGroup,
    private val onItemClickListener: (heroId: String) -> Unit
) : BaseViewHolder(parent, layoutId = R.layout.list_item_marvel_hero) {

    private val binding = ListItemMarvelHeroBinding.bind(itemView)

    fun bind(character: Character) {
        with(binding) {
            root.setOnClickListener {
                onItemClickListener.invoke(character.id.toString())
            }
            heroImage.fetchImageWithRoundedCorners(character.getImageUrl())
            heroName.text = character.name
        }
    }
}