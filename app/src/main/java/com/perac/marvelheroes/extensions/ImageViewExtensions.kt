package com.perac.marvelheroes.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.perac.marvelheroes.R

/**
 * Extension for image view which fetches an image from given URL and loads it into an image view.
 */
fun ImageView.fetchImage(imageUrl: String) =
    Glide.with(context)
        .setDefaultRequestOptions(RequestOptions())
        .load(imageUrl)
        .into(this)

/**
 * Extension for image view which fetches an image from given URL and loads it into an image view.
 * Applies transformation for rounded corners
 */
fun ImageView.fetchImageWithRoundedCorners(imageUrl: String) =
    Glide.with(context)
        .setDefaultRequestOptions(RequestOptions())
        .load(imageUrl)
        .transform(RoundedCorners(resources.getDimensionPixelOffset(R.dimen.small_corner_radius)))
        .into(this)