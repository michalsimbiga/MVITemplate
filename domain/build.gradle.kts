plugins {
    id("java-library")
    id("kotlin")
    kotlin("kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(Libs.Core.KOTLIN_STDLIB)

    // DI
    implementation(Libs.DI.JAVA_INJECT)

    testImplementation(Libs.Test.JUNIT)
    testImplementation(Libs.Test.TRUTH)
    testImplementation(Libs.Test.MOCKITO_KOTLIN)
    testImplementation(Libs.Test.ARCH_CORE)
    testImplementation(Libs.Test.COROUTINES_TEST)
    testImplementation(Libs.Test.MOCKITO_KOTLIN)
}