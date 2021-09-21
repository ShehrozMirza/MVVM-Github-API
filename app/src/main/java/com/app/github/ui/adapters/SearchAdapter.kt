package com.app.github.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.github.R
import com.app.github.databinding.ItemPhotoBinding
import com.app.github.models.GithubSearchModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class SearchAdapter(private val onItemClickListener: OnItemClickListener) : PagingDataAdapter<GithubSearchModel, SearchAdapter.PhotoViewHolder>(PHOTO_COMPARATOR) {

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    inner class PhotoViewHolder(private val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null)
                        onItemClickListener.onItemClick(item)
                }
            }
        }

        fun bind(item: GithubSearchModel) {

            with(binding)
            {
                apply {
                    Glide.with(binding.imgView)
                            .load(item.avatarUrl)
                            .centerCrop()
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .error(R.drawable.ic_error)
                            .into(binding.imgView)
                }
                binding.txtTags.text = item.login
                binding.txtType.text = item.type
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(photo: GithubSearchModel)
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<GithubSearchModel>() {
            override fun areItemsTheSame(oldItem: GithubSearchModel, newItem: GithubSearchModel): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: GithubSearchModel, newItem: GithubSearchModel) = oldItem == newItem
        }
    }
}