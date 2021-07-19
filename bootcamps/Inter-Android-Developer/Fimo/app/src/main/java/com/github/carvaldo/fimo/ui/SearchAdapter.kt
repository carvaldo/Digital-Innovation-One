package com.github.carvaldo.fimo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.carvaldo.fimo.R
import com.github.carvaldo.fimo.databinding.ListItemBinding
import com.github.carvaldo.fimo.datasource.local.ResultMovie
import com.squareup.picasso.Picasso
// TODO: Utiliizar DiffUtil
class SearchAdapter(items: List<ResultMovie>? = null): RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
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
            Picasso.get().load(movie.image).resizeDimen(R.dimen.thumb_width, R.dimen.thumb_height).centerInside().into(binding.imageView)
            binding.root.setOnClickListener { onItemClickListener?.invoke(adapterPosition, movie) }
        }
    }
}