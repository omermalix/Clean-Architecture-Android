package com.elifox.legocatalog.domain

data class ResultsResponse<T>(
    val count: Int,
    val next: String? = null,
    val previous: String? = null,
    val results: List<T>
)