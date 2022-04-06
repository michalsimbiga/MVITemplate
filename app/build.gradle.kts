plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
}

android {
    compileSdk = Versions.COMPILE_SDK
    buildToolsVersion = Versions.BUILD_TOOLS

    defaultConfig {
        applicationId = Versions.appName
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK
        versionCode = Versions.versionCodeMobile
        versionName = Versions.versionName

        testInstrumentationRunner = Versions.androidTestInstrumentation
    }

    installation {
        installOptions("-g", "-r")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    testOptions {
        animationsDisabled = true
    }

    kotlin {
        sourceSets {
            test {
                kotlin.srcDir("build/generated/ksp/debug/kotlin")
            }
            debug {
                kotlin.srcDir("build/generated/ksp/debug/kotlin")
            }
            release {
                kotlin.srcDir("build/generated/ksp/release/kotlin")
            }
        }
    }

    testOptions {
        animationsDisabled = true
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE
    }

    applicationVariants.all {
        kotlin.sourceSets {
            getByName(name) {
                kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }
    }
}
dependencies {

    implementation(project(":domain"))
    implementation(project(":data"))

    // Core
    implementation(Libs.Core.APP_COMPAT)
    implementation(Libs.Core.CORE_KTX)
    implementation(Libs.Core.LIFECYCLE_RUNTIME_KTX)
    implementation(Libs.Core.COROUTINES_CORE)

    // DI
    implementation(Libs.DI.HILT_ANDROID)
    implementation(Libs.DI.HILT_NAVIGATION)
    kapt(Libs.DI.HILT_COMPILER)

    // Compose
    implementation(Libs.UI.COMPOSE_UI)
    implementation(Libs.UI.COMPOSE_UI_TOOLING)
    implementation(Libs.UI.COMPOSE_UI_GRAPHICS)
    implementation(Libs.UI.COMPOSE_FOUNDATION_LAYOUT)
    implementation(Libs.UI.COMPOSE_MATERIAL)
    implementation(Libs.UI.COMPOSE_RUNTIME)
    implementation(Libs.UI.COMPOSE_ACTIVITY)
    implementation(Libs.UI.COMPOSE_VIEW_MODEL)
    implementation(Libs.UI.COMPOSE_UI_UTIL)
    implementation(Libs.UI.COMPOSE_COIL)
    implementation(Libs.UI.COMPOSE_NAVIGATION)

    implementation("io.github.raamcosta.compose-destinations:animations-core:1.4.0-beta")
    implementation("io.github.raamcosta.compose-destinations:core:1.4.0-beta")
    ksp("io.github.raamcosta.compose-destinations:ksp:1.4.0-beta")
    testImplementation("io.github.raamcosta.compose-destinations:core:1.4.0-beta")

    // Logging
    implementation(Libs.Utils.TIMBER)


    // Tests
    testImplementation(Libs.Test.JUNIT)
    testImplementation(Libs.Test.TRUTH)
    testImplementation(Libs.Test.MOCKITO_KOTLIN)
    testImplementation(Libs.Test.ARCH_CORE)
    testImplementation(Libs.Test.COROUTINES_TEST)
    testImplementation(Libs.Test.MOCKITO_INLINE)

    // Android Tests
    androidTestImplementation(Libs.AndroidTest.TEST_CORE)
    androidTestImplementation(Libs.Test.COMPOSE_UI_TEST)
    androidTestImplementation(Libs.AndroidTest.TEST_RULES)
    androidTestImplementation(Libs.AndroidTest.TEST_RUNNER)
    androidTestImplementation(Libs.AndroidTest.COMPOSE_UI_TEST)
    androidTestImplementation(Libs.AndroidTest.COMPOSE_UI_TEST_JUNIT)
    androidTestImplementation(Libs.AndroidTest.MOCKK_UI)

    debugImplementation(Libs.AndroidTest.COMPOSE_UI_TEST_MANIFEST)

    // Robolectric dependencies
    testImplementation(Libs.AndroidTest.COMPOSE_UI_TEST_JUNIT)
    testImplementation(Libs.Test.ROBOLECTRIC)

    // For Robolectric tests.
    testImplementation(Libs.DI.HILT_ANDROID_TESTING)
    // ...with Kotlin.
    kaptTest(Libs.Test.HILT_COMPILER)

    // For instrumented tests.
    androidTestImplementation(Libs.DI.HILT_ANDROID_TESTING)
    // ...with Kotlin.
    kaptAndroidTest(Libs.Test.HILT_COMPILER)

testImplementation("app.cash.turbine:turbine:0.7.0")
testImplementation("com.google.truth:truth:1.1.3")
testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.1")
}