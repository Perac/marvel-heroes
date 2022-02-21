package com.perac.marvelheroes.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.perac.marvelheroes.databinding.ActivityMarvelHeroesBinding

class MarvelHeroesActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMarvelHeroesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMarvelHeroesBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)

        setUpToolbar()
    }

    private fun setUpToolbar() {
        setSupportActionBar(viewBinding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            viewBinding.navHostFragment.findNavController().navigateUp()
        }
        return false
    }
}