package com.msimbiga.domain.repositories

import com.msimbiga.domain.models.Character

interface CharacterRepository {
    suspend fun getCharacters(): List<Character>
    suspend fun getCharacterById(id: String): Character
}
