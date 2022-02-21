package com.perac.marvelheroes.ui.herolist

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.perac.marvelheroes.network.models.Character

/**
 * RecyclerView adapter for Marvel heroes list.
 */
class MarvelHeroListAdapter(
    private val onItemClickListener: (String) -> Unit
) : ListAdapter<Character, RecyclerView.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HeroListItemViewHolder(parent, onItemClickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HeroListItemViewHolder).bind(getItem(position))
    }

    companion object {

        // diff util used to update UI changes with performance boost
        private val diffUtil = object : DiffUtil.ItemCallback<Character>() {

            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem == newItem
            }
        }
    }


}