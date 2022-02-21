package com.perac.marvelheroes.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

/**
 * Base ViewHolder class which inflates layout.
 */
open class BaseViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {

    constructor(parent: ViewGroup, @LayoutRes layoutId: Int, attachToRoot: Boolean = false) :
        this(LayoutInflater.from(parent.context).inflate(layoutId, parent, attachToRoot))
}