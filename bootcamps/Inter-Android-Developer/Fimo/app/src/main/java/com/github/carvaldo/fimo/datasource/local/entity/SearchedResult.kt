package com.github.carvaldo.fimo.datasource.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


/**
 * Searched result in historic.
 *
 * @property id
 * @property searchId Foreign key for [Searched]
 * @property resultId Foreign key for [ResultMovie]
 */
@Entity(tableName = "searched_result",
    // TODO: Está quebrando com foreign key.
//    foreignKeys = [
//        ForeignKey(entity = Searched::class, parentColumns = ["id"], childColumns = ["resultId"],
//            onDelete = CASCADE, onUpdate = CASCADE)],
    indices = [
        Index(value = ["resultId"], unique = false)]
)
data class SearchedResult(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val searchId: Long,
    val resultId: Long)
