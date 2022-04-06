package com.msimbiga.application.di

import com.msimbiga.domain.AppConfiguration

object TestConfig : AppConfiguration {

    override val httpConnectTimeout: Int = 60
    override val httpWriteTimeout: Int = 60
    override val httpReadTimeout: Int = 60
    override val apiUri: String = "https://rickandmortyapi.com"
}