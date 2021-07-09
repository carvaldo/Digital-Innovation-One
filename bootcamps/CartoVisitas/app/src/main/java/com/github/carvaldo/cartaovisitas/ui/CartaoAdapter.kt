package com.github.carvaldo.cartaovisitas.ui

import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.carvaldo.cartaovisitas.data.Cartao
import com.github.carvaldo.cartaovisitas.databinding.CardCartaoVisitasBinding

class CartaoAdapter(private val cartoes: List<Cartao>): RecyclerView.Adapter<CartaoAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(CardCartaoVisitasBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder.adapterPosition == cartoes.size) {
            holder.itemView.visibility = INVISIBLE
        } else {
            if (holder.itemView.visibility == INVISIBLE) {
                holder.itemView.visibility = VISIBLE
            }
            holder.toBind(cartoes[holder.adapterPosition])
        }
    }

    override fun getItemCount() = cartoes.size + 1

    inner class ViewHolder(private val binding: CardCartaoVisitasBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun toBind(cartao: Cartao) {
            binding.emailText.text = cartao.email
            binding.empresaText.text = cartao.empresa
            binding.nomeText.text = cartao.nome
            binding.telefoneText.text = cartao.telefone
        }
    }
}