package com.msimbiga.domain.models

data class Page(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?,
)
