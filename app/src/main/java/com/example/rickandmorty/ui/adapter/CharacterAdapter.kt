package com.example.rickandmorty.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmorty.BaseAdapter
import com.example.rickandmorty.R
import com.example.rickandmorty.data.model.Results
import com.example.rickandmorty.databinding.ItemCharacterBinding


class CharacterAdapter : BaseAdapter<Results, CharacterAdapter.CharacterViewHolder>(CharacterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterAdapter.CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemCharacterBinding.inflate(layoutInflater, parent, false)
            return CharacterViewHolder(binding)
    }

    class CharacterViewHolder(private val binding: ItemCharacterBinding) : BaseAdapter.BaseViewHolder(binding.root) {

        init {
            binding.characterContainer.setOnClickListener {
                onItemClick?.onClick(item, it)
            }
        }

        override fun onBind(item: Any) {
            (item as? Results)?.let {
                binding.character = it
            }
        }
    }

    class CharacterDiffCallback : DiffUtil.ItemCallback<Results>() {
        override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem == newItem
        }
    }
}