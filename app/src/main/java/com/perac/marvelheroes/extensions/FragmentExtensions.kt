package com.perac.marvelheroes.extensions

import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * This method sets the toolbar title.
 */
fun Fragment.setToolbarTitle(title: String) {
    (activity as AppCompatActivity?)?.apply {
        supportActionBar?.title = title
        invalidateOptionsMenu()
    }
}

/**
 * This method sets the toolbar icon.
 */
fun Fragment.setToolbarIcon(@DrawableRes resId: Int) {
    (activity as AppCompatActivity?)?.apply {
        supportActionBar?.setHomeAsUpIndicator(resId)
        invalidateOptionsMenu()
    }
}