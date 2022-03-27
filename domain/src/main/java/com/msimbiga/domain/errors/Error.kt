package com.msimbiga.domain.errors

sealed class ProjectError : Throwable() {
    object Unknown : ProjectError()
}
