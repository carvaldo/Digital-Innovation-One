package com.github.carvaldo.cartaovisitas.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cartao(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var nome: String? = null,
    var telefone: String? = null,
    var email: String? = null,
    var empresa: String? = null
)
