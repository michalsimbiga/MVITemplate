package com.msimbiga.data.models

import com.msimbiga.domain.models.Character
import com.msimbiga.domain.models.Page
import com.msimbiga.domain.models.Planet
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterListResponse<T>(
    @Json(name = "info") val info: PageResponse,
    @Json(name = "results") val results: T
)

@JsonClass(generateAdapter = true)
data class PageResponse(
    @Json(name = "count") val count: Int,
    @Json(name = "pages") val pages: Int,
    @Json(name = "next") val next: String?,
    @Json(name = "prev") val prev: String?,
)

@JsonClass(generateAdapter = true)
data class PlanetResponse(
    @Json(name = "name") val name: String,
    @Json(name = "url") val url: String,
)

@JsonClass(generateAdapter = true)
data class CharacterResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "status") val status: String,
    @Json(name = "species") val species: String,
    @Json(name = "type") val type: String,
    @Json(name = "gender") val gender: String,
    @Json(name = "origin") val origin: PlanetResponse,
    @Json(name = "location") val location: PlanetResponse,
    @Json(name = "image") val image: String,
    @Json(name = "episode") val episode: List<String>,
    @Json(name = "created") val created: String,
    @Json(name = "url") val url: String,
)


fun PageResponse.toDomain() = Page(
    count = count,
    pages = pages,
    next = next,
    prev = prev
)

fun Page.toResponse() = PageResponse(
    count = count,
    pages = pages,
    next = next,
    prev = prev
)

fun PlanetResponse.toDomain() = Planet(
    name = name,
    url = url,
)

fun Planet.toResponse() = PlanetResponse(
    name = name,
    url = url,
)

fun CharacterResponse.toDomain() = Character(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    origin = origin.toDomain(),
    location = location.toDomain(),
    image = image,
    episode = episode,
    created = created,
    url = url,
)

fun Character.toResponse() = CharacterResponse(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    origin = origin.toResponse(),
    location = location.toResponse(),
    image = image,
    episode = episode,
    created = created,
    url = url,
)
