package com.famgy.famgyjetpack.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.famgy.famgyjetpack.data.db.tb.Song
import com.famgy.famgyjetpack.databinding.ListItemMusicBinding

class SongAdapter : ListAdapter<Song, RecyclerView.ViewHolder>(SongDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MusicViewHolder(ListItemMusicBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val music = getItem(position)
        (holder as MusicViewHolder).bind(music)
    }

    class MusicViewHolder(
        private val binding: ListItemMusicBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
        }

        fun bind(item: Song) {
            binding.apply {
                music = item
                executePendingBindings()
            }
        }
    }
}

private class SongDiffCallback : DiffUtil.ItemCallback<Song>() {

    override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
        return oldItem == newItem
    }
}