package com.msimbiga.domain

interface AppConfiguration {

    val httpConnectTimeout: Int
    val httpWriteTimeout: Int
    val httpReadTimeout: Int

    val apiUri: String
}
