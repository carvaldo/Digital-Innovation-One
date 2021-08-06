package com.github.carvaldo.fimo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.github.carvaldo.fimo.databinding.ListItemBinding
import com.github.carvaldo.fimo.datasource.local.entity.ResultMovie
import javax.inject.Inject

// TODO: Utiliizar DiffUtil
class SearchAdapter @Inject constructor(): RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    var items: List<ResultMovie>? = null
        set(value) {
            field = value
            this.notifyDataSetChanged()
        }

    var onItemClickListener: ((position: Int, ResultMovie)->Unit)? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    init {
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.toBind(items!![holder.adapterPosition])
    }

    override fun getItemCount() = items?.size ?: 0

    inner class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun toBind(movie: ResultMovie) {
            binding.titleText.text = movie.title
            binding.descriptionText.text = movie.description
            binding.imageView.uri = movie.image?.toUri()
            binding.root.setOnClickListener { onItemClickListener?.invoke(adapterPosition, movie) }
        }
    }
}