package com.github.carvaldo.cartaovisitas.ui

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import com.github.carvaldo.cartaovisitas.R
import com.github.carvaldo.cartaovisitas.data.Cartao
import com.github.carvaldo.cartaovisitas.databinding.CardCartaoVisitasBinding
import kotlinx.coroutines.*

/**
 * Adapter para listagem de cartões.
 *
 * @property scope Escopo para execução de corrotina.
 * @property cartoes Itens a ser listados
 */
class CartaoAdapter(private val scope: LifecycleCoroutineScope, private val cartoes: List<Cartao>): RecyclerView.Adapter<CartaoAdapter.ViewHolder>() {
    var onItemClickListener: ((position: Int, data: Cartao) -> Unit)? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(CardCartaoVisitasBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.toBind(cartoes[holder.adapterPosition])
    }

    override fun getItemCount() = cartoes.size

    inner class ViewHolder(private val binding: CardCartaoVisitasBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val res by lazy { binding.root.resources }

        fun toBind(cartao: Cartao) {
            binding.emailText.text = cartao.email
            binding.empresaText.text = cartao.empresa
            binding.nomeText.text = cartao.nome
            binding.telefoneText.text = cartao.telefone
            /**
             * Para não travar a thread principal, foi utilizado uma corrotina.
             * Para poupar a carga de processamento repetindo a operação de criação da thumb,
             * a thumb gerada é armazenada no POJO durante seu ciclo de vida.
             */
            if (cartao.foto != null) {
                scope.launch(Dispatchers.Main) {
                    if (cartao.fotoDrawable == null) {
                        val drawable = withContext(Dispatchers.IO) {
                            makeDrawableThumb(res, cartao.foto!!)
                        }
                        cartao.fotoDrawable = drawable
                    }
                    binding.fotoView.setImageDrawable(cartao.fotoDrawable)
                }
            }
            binding.root.setOnClickListener {
                onItemClickListener?.invoke(adapterPosition, cartao)
            }
        }
    }

    private companion object {
        /**
         * Gerar [RoundedBitmapDrawableFactory] para foto do cartão.
         *
         * @param res Recursos do sistema.
         * @param path Caminho da imagem.
         * @return [Drawable] Drawable gerado.
         */
        @JvmStatic private fun makeDrawableThumb(res: Resources, path: String): Drawable
        {
            val bitmap =
                Bitmap.createScaledBitmap(BitmapFactory.decodeFile(path), res.getDimensionPixelSize(R.dimen.car_list_item_photo_width), res.getDimensionPixelSize(R.dimen.car_list_item_photo_height), false)
            return RoundedBitmapDrawableFactory.create(res, bitmap).apply {
                setAntiAlias(true)
                isCircular = true
                setTargetDensity(res.displayMetrics)
            }
        }
    }
}