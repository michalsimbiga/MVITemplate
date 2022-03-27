package com.msimbiga.domain.repositories

import com.msimbiga.domain.models.Character

interface CharacterRepository {
    fun getCharacters(): List<Character>
    fun getCharacterById(id: String): Character
}
