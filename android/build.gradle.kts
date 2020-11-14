plugins {
    id("org.jetbrains.compose") version "0.1.0-m1-build62"
    id("com.android.application")
    kotlin("android")
}

group = "ru.acousticksan"
version = "1.0"

repositories {
    google()
}

dependencies {
    implementation(project(":common"))
}

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "ru.acousticksan.android"
        minSdkVersion(24)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}