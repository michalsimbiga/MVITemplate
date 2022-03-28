package com.msimbiga.application

import com.msimbiga.domain.AppConfiguration
import javax.inject.Inject

class AppConfig @Inject constructor() : AppConfiguration {
    override val httpConnectTimeout: Int = 60
    override val httpWriteTimeout: Int = 60
    override val httpReadTimeout: Int = 60

    override val apiUri: String = "https://rickandmortyapi.com"
}
