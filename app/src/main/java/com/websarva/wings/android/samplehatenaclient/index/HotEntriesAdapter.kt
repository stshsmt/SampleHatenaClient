package com.websarva.wings.android.samplehatenaclient.index

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.websarva.wings.android.samplehatenaclient.databinding.HotEntryItemBinding
import com.websarva.wings.android.samplehatenaclient.model.HotEntry

class HotEntriesAdapter : ListAdapter<HotEntry, HotEntriesAdapter.ViewHolder>(HotEntryDiffCallback()){
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: HotEntryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HotEntry) {
            binding.hotEntry = item
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HotEntryItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class HotEntryDiffCallback : DiffUtil.ItemCallback<HotEntry>() {
    override fun areItemsTheSame(oldItem: HotEntry, newItem: HotEntry): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: HotEntry, newItem: HotEntry): Boolean {
        return oldItem == newItem
    }
}