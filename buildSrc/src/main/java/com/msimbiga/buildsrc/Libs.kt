object Libs {

    object Core {
        const val KOTLIN_STDLIB = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.KOTLIN}"
        const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect:${Versions.KOTLIN}"
        const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
        const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
        const val LIFECYCLE_RUNTIME_KTX =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE_RUNTIME_KTX}"
        const val COROUTINES_CORE =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES_CORE}"
    }

    object Test {
        const val HILT_TESTING = "com.google.dagger:hilt-android-testing:${Versions.HILT}"
        const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"
        const val JUNIT = "junit:junit:4.13"
        const val EXT_JUNIT = "androidx.test.ext:junit:1.1.3"
        const val TRUTH = "com.google.truth:truth:1.1.2"
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:3.4.0"
        const val COMPOSE_TEST = "androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE}"
        const val COMPOSE_UI_TEST = "androidx.compose.ui:ui-test:${Versions.COMPOSE}"
        const val COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0-RC"
        const val ARCH_CORE = "androidx.arch.core:core-testing:2.1.0"
        const val MOCKITO_KOTLIN = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.MOCKITO}"
        const val MOCKITO_INLINE = "org.mockito:mockito-inline:${Versions.MOCKITO_INLINE}"
        const val ROBOLECTRIC = "org.robolectric:robolectric:${Versions.ROBOLECTRIC}"
    }

    object AndroidTest {
        const val TEST_ORCHESTRATOR = "androidx.test:orchestrator:1.4.0"
        const val UI_AUTOMATOR = "androidx.test.uiautomator:uiautomator:2.2.0"
        const val TEST_CORE = "androidx.test:core:1.4.0"
        const val TEST_RUNNER = "androidx.test:runner:1.4.0"
        const val TEST_RULES = "androidx.test:rules:1.4.0"
        const val MOCKK_UI = "io.mockk:mockk-android:1.12.1"
        const val COMPOSE_UI_TEST = "androidx.compose.ui:ui-test:${Versions.COMPOSE}"
        const val COMPOSE_UI_TEST_JUNIT = "androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE}"
        const val COMPOSE_UI_TEST_MANIFEST =
            "androidx.compose.ui:ui-test-manifest:${Versions.COMPOSE}"
    }

    object DI {
        const val HILT_ANDROID = "com.google.dagger:hilt-android:${Versions.HILT}"
        const val HILT_ANDROID_TESTING = "com.google.dagger:hilt-android-testing:${Versions.HILT}"
        const val HILT_VIEWMODEL = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.HILT}"
        const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"
        const val HILT_NAVIGATION =
            "androidx.hilt:hilt-navigation-compose:${Versions.HILT_NAVIGATION}"
        const val JAVA_INJECT = "javax.inject:javax.inject:1"
    }

    object Networking {
        const val OKHTTP = "com.squareup.okhttp3:okhttp"
        const val OKHTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:4.9.1"
        const val OKHTTP_MOCK_SERVER = "com.squareup.okhttp3:mockwebserver"
        const val RETROFIT = "com.squareup.retrofit2:retrofit:2.9.0"
        const val MOSHI_KOTLIN = "com.squareup.moshi:moshi-kotlin::1.12.0"
        const val MOSHI_RETROFIT = "com.squareup.retrofit2:converter-moshi:2.5.0"
        const val MOSHI_CODEGEN = "com.squareup.moshi:moshi-kotlin-codegen:1.13.0"
        const val MOSHI_COROUTINES_ADAPTER =
            "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
        const val MOSHI = "com.squareup.moshi:moshi:1.13.0"
    }

    object Utils {
        const val TIMBER = "com.jakewharton.timber:timber:${Versions.TIMBER}"
    }

    object UI {
        const val COMPOSE_UI = "androidx.compose.ui:ui:${Versions.COMPOSE}"
        const val COMPOSE_UI_UTIL = "androidx.compose.ui:ui-util:${Versions.COMPOSE}"
        const val COMPOSE_UI_GRAPHICS = "androidx.compose.ui:ui-graphics:${Versions.COMPOSE}"
        const val COMPOSE_UI_TOOLING = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE}"
        const val COMPOSE_FOUNDATION_LAYOUT =
            "androidx.compose.foundation:foundation-layout:${Versions.COMPOSE}"
        const val COMPOSE_MATERIAL = "androidx.compose.material:material:${Versions.COMPOSE}"
        const val COMPOSE_RUNTIME = "androidx.compose.runtime:runtime:${Versions.COMPOSE}"
        const val COMPOSE_ANIMATION = "androidx.compose.animation:animation:${Versions.COMPOSE}"
        const val COMPOSE_ANIMATION_CORE =
            "androidx.compose.animation:animation-core:${Versions.COMPOSE}"
        const val COMPOSE_NAVIGATION =
            "androidx.navigation:navigation-compose:${Versions.COMPOSE_NAVIGATION}"
        const val COMPOSE_ACTIVITY = "androidx.activity:activity-compose:${Versions.COMPOSE}"
        const val COMPOSE_CONSTRAINT =
            "androidx.constraintlayout:constraintlayout-compose:${Versions.COMPOSE}"
        const val COMPOSE_VIEW_MODEL =
            "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.COMPOSE_VIEWMODEL}"
        const val COMPOSE_LOTTIE = "com.airbnb.android:lottie-compose:${Versions.COMPOSE_LOTTIE}"
        const val COMPOSE_COIL = "io.coil-kt:coil-compose:${Versions.COMPOSE_COIL}"
    }
}
