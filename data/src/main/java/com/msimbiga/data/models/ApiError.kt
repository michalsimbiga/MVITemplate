package com.msimbiga.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiError(
    @Json(name = "status") val status: String?,
    @Json(name = "timestamp") val timestamp: String?,
    @Json(name = "message") val message: String?,
    @Json(name = "debugMessage") val debugMessage: String?,
    @Json(name = "errorCode") val errorCode: Int?,
)
