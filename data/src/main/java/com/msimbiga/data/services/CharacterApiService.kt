package com.msimbiga.data.services

import com.msimbiga.data.models.CharacterListResponse
import com.msimbiga.data.models.CharacterResponse
import com.payeye.eyepos.network.utils.ApiResult
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterApiService {

    @GET("/api/character")
    suspend fun getCharacters()
            : ApiResult<CharacterListResponse<List<CharacterResponse>>>

    @GET("/api/character/{id}")
    suspend fun getCharacterById(@Path("id") charId: String)
            : ApiResult<CharacterResponse>
}
