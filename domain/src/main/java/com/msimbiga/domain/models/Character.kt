package com.msimbiga.domain.models

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Planet,
    val location: Planet,
    val image: String,
    val episode: List<String>,
    val created: String,
    val url: String,
)
