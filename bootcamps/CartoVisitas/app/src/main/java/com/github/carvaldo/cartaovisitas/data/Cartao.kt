package com.github.carvaldo.cartaovisitas.data

import android.graphics.drawable.Drawable
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.IgnoredOnParcel
import java.io.Serializable
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Cartao(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var nome: String? = null,
    var telefone: String? = null,
    var email: String? = null,
    var empresa: String? = null,
    var foto: String? = null,
): Parcelable {
    @Ignore
    @IgnoredOnParcel
    var fotoDrawable: Drawable? = null
}