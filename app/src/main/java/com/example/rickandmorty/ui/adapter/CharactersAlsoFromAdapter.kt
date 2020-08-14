package com.example.rickandmorty.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmorty.BaseAdapter
import com.example.rickandmorty.R
import com.example.rickandmorty.data.model.Results
import com.example.rickandmorty.databinding.ItemCharacterBinding
import com.example.rickandmorty.utils.GlideHelper
import kotlinx.android.synthetic.main.item_single_character.view.*

class CharactersAlsoFromAdapter(
    val singleCharacterData: Map<String, String>
) : BaseAdapter<Results, BaseAdapter.BaseViewHolder>(CharacterDiffCallback()) {

    private val HEADER = 0
    private val OTHER = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseAdapter.BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType != HEADER) {
            val binding = ItemCharacterBinding.inflate(layoutInflater, parent, false)
            return CharacterViewHolder(binding)
        } else {
            val view = layoutInflater.inflate(R.layout.item_single_character, parent, false)
            HeaderViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            HEADER
        } else {
            OTHER
        }
    }

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) : BaseAdapter.BaseViewHolder(binding.root) {

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

    inner class HeaderViewHolder(itemView: View) : BaseAdapter.BaseViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        override fun onBind(item: Any) {
            itemView.tvCharacterName.text = singleCharacterData["characterName"]
            itemView.tvLastLocation.text = singleCharacterData["location"]
            itemView.tvFirstSeen.text = singleCharacterData["origin"]
            itemView.tvStatus.text = singleCharacterData["status"]
            val location = singleCharacterData["location"]
            itemView.tvAlsoFrom.text = "Also from $location"
            GlideHelper.loadImageSingleCharacter(itemView.context, singleCharacterData["photo"], itemView.ivCharacterPhoto)
            if (singleCharacterData["status"] == "Alive") {
                itemView.ivStatus.setImageDrawable(itemView.resources.getDrawable(R.drawable.status_alive, null))
            } else {
                itemView.ivStatus.setImageDrawable(itemView.resources.getDrawable(R.drawable.status_dead, null))
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