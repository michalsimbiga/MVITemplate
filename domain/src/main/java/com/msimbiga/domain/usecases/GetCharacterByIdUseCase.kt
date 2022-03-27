package com.msimbiga.domain.usecases

import com.msimbiga.domain.models.Character
import com.msimbiga.domain.repositories.CharacterRepository
import com.msimbiga.domain.usecases.base.UseCase
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val repository: CharacterRepository,
) : UseCase<String, Character>() {
    override suspend fun invoke(params: String) = repository.getCharacterById(id = params)
}
