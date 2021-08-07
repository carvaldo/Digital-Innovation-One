package com.github.carvaldo.fimo.api.datasource

// TODO: Analisar real uso do case NAME.

enum class ResultType(val value: String) {
    TITLE("Title"), NAME("Name");

    companion object {
        fun fromValue(value: String?) = when (value) {
            "Title" -> TITLE
            "Name" -> NAME
            else -> null
        }
    }
}