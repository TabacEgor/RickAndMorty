package com.example.rickandmorty

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.ui.adapter.OnItemClick

abstract class BaseAdapter<Item: Any, VH: BaseAdapter.BaseViewHolder>(
    diffUtil: DiffUtil.ItemCallback<Item>) : ListAdapter<Item, VH>(diffUtil)
{
    var onItemClick: OnItemClick? = null

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
        holder.onItemClick = this.onItemClick
    }

    fun setOnClick(click: (Any?, View) -> Unit) {
        onItemClick = object : OnItemClick {
            override fun onClick(item: Any?, view: View) {
                click(item, view)
            }
        }
    }

    abstract class BaseViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        var onItemClick: OnItemClick? = null
        var item: Any? = null

        init {
            view.setOnClickListener {
                onItemClick?.onClick(item, it)
            }
        }

        fun bind(item: Any) {
            this.item = item
            onBind(item)
        }

        abstract fun onBind(item: Any)
    }
}