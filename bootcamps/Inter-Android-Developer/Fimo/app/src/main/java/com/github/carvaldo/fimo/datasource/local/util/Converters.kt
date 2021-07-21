package com.github.carvaldo.fimo.datasource.local.util

import androidx.room.TypeConverter
import com.github.carvaldo.fimo.datasource.ResultType
import java.util.*

class Converters {
    @TypeConverter
    fun fromResulType(value: String?): ResultType? = ResultType.fromValue(value)

    @TypeConverter
    fun resultTypeToString(value: ResultType?): String? = value?.name

    @TypeConverter
    fun longToDate(value: Long?): Date? = if (value != null) Date(value) else null

    @TypeConverter
    fun dateToLong(value: Date?): Long? = value?.time
}