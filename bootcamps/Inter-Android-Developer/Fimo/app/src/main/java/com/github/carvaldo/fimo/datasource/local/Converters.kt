package com.github.carvaldo.fimo.datasource.local

import androidx.room.TypeConverter
import com.github.carvaldo.fimo.datasource.ResultType

class Converters {
    @TypeConverter
    fun fromResulType(value: String?): ResultType? = ResultType.fromValue(value)

    @TypeConverter
    fun ResultTypeToString(value: ResultType?): String? = value?.name
}