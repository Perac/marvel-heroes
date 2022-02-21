package com.perac.marvelheroes.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PaginationScrollListener(private val onScrolledToEndListener: OnScrolledToEndListener? = null) :
    RecyclerView.OnScrollListener() {

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        if (onScrolledToEndListener == null) {
            return
        }
        val visibleThreshold = 5
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val itemCount = layoutManager.itemCount
        val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
        if (itemCount == 0 || lastVisibleItem == -1) {
            return
        }
        if (itemCount <= (lastVisibleItem + visibleThreshold)) {
            onScrolledToEndListener.onScrolledToEnd()
        }
    }

}

fun RecyclerView.onScrolledToEnd(action: () -> Unit) {
    addOnScrollListener(PaginationScrollListener(object : OnScrolledToEndListener {
        override fun onScrolledToEnd() {
            action()
        }
    }))
}