buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.ANDROID_GRADLE_PLUGIN}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT}")
//        classpath("org.jacoco:org.jacoco.core:${Versions.JACOCO}")
//        classpath("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:${Versions.SONARQUBE}")
//        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT}")
    }
}

plugins {
    id("com.android.application") version "7.1.2" apply false
    id("com.android.library") version "7.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.5.21" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

tasks {
    withType(org.jetbrains.kotlin.gradle.dsl.KotlinCompile::class).forEach {
        it.kotlinOptions {
            // Treat all Kotlin warnings as errors (disabled by default)
            allWarningsAsErrors =
                project.hasProperty("warningsAsErrors") ?: true

            freeCompilerArgs = freeCompilerArgs
                .plus("-Xopt-in=kotlin.RequiresOptIn")
                .plus("-Xopt-in=kotlin.Experimental")
                .plus("-opt-in=kotlin.RequiresOptIn")
        }
    }
}