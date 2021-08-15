package com.github.carvaldo.fimo.datasource.remote.response

data class ResponseApi<T>(val data: T, val error: String?)