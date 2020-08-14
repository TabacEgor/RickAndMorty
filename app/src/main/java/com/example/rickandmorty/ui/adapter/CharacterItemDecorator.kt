package com.example.rickandmorty.ui.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CharacterItemDecorator(
    private val defaultSpace: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = defaultSpace
        outRect.left = defaultSpace
        outRect.right = defaultSpace
    }
}