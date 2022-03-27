plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(Libs.Core.KOTLIN_STDLIB)

    implementation(Libs.DI.HILT_ANDROID)
    kapt(Libs.DI.HILT_COMPILER)

    implementation(Libs.Utils.TIMBER)

    implementation(Libs.Networking.OKHTTP)
    implementation(Libs.Networking.OKHTTP_LOGGING_INTERCEPTOR)
    implementation(Libs.Networking.RETROFIT)
    implementation(Libs.Networking.MOSHI)
    kapt(Libs.Networking.MOSHI_CODEGEN)
    implementation(Libs.Networking.MOSHI_RETROFIT)
    implementation(Libs.Networking.MOSHI_COROUTINES_ADAPTER)

    testImplementation(Libs.Test.JUNIT)
    testImplementation(Libs.Test.TRUTH)
    testImplementation(Libs.Test.MOCKITO_KOTLIN)
    testImplementation(Libs.Test.ARCH_CORE)
    testImplementation(Libs.Test.COROUTINES_TEST)
    testImplementation(Libs.Test.MOCKITO_KOTLIN)

    androidTestImplementation(Libs.DI.HILT_ANDROID_TESTING)
    kaptAndroidTest(Libs.Test.HILT_COMPILER)
}