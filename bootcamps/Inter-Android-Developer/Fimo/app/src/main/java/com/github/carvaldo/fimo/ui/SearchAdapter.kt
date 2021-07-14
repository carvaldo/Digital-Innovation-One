package com.github.carvaldo.fimo.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.carvaldo.fimo.R
import com.github.carvaldo.fimo.databinding.ListItemBinding
import com.github.carvaldo.fimo.datasource.remote.Movie
import com.squareup.picasso.Picasso

class SearchAdapter(items: List<Movie>?): RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    var items: List<Movie>? = null
        set(value) {
            field = value
            this.notifyDataSetChanged()
        }

    init {
        this.items = items
    }

    inner class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun toBind(movie: Movie) {
            binding.titleText.text = movie.title
            binding.descriptionText.text = movie.description
            Picasso.get().load(movie.image).resizeDimen(R.dimen.thumb_width, R.dimen.thumb_height).centerInside().into(binding.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.toBind(items!![holder.adapterPosition])
    }

    override fun getItemCount() = items?.size ?: 0
}