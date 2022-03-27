package com.msimbiga.data.repositories

import com.msimbiga.data.models.CharacterResponse
import com.msimbiga.data.models.toDomain
import com.msimbiga.data.services.CharacterApiService
import com.msimbiga.data.utils.NetworkHandler
import com.msimbiga.domain.models.Character
import com.msimbiga.domain.repositories.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterApiService: CharacterApiService,
    private val networkHandler: NetworkHandler
) : CharacterRepository {

    override suspend fun getCharacters(): List<Character> =
        networkHandler.safeNetworkCall { characterApiService.getCharacters() }
            .results
            .map(CharacterResponse::toDomain)

    override suspend fun getCharacterById(id: String): Character =
        networkHandler.safeNetworkCall { characterApiService.getCharacterById(id) }
            .toDomain()
}
