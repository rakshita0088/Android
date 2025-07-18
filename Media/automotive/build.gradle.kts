plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.example.automediaplayer"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.automediaplayer"
        minSdk = 28
        targetSdk = 33

        versionCode = 1
        versionName = "1.0"

        manifestPlaceholders["appLabel"] = "Auto Media Player"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {

}
