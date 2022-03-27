package com.msimbiga.domain.usecases.base

abstract class UseCase<in PARAMS, out TYPE> {
    abstract suspend fun invoke(params: PARAMS = Unit as PARAMS): TYPE
}
