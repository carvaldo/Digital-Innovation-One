package com.github.carvaldo.cartaovisitas.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cartao(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var nome: String,
    var telefone: String? = null,
    var email: String? = null,
    var empresa: String? = null
)
