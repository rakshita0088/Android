import org.gradle.kotlin.dsl.implementation

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose") // ✅ Required for Kotlin 2.0 + Compose
    id("com.google.gms.google-services")      // ✅ Firebase plugin
}

android {
    namespace = "com.example.autovault"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.autovault"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13" // ✅ Compatible with Kotlin 2.0
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.9.0")

    // Compose
    implementation("androidx.compose.ui:ui:1.6.7")
    implementation("androidx.compose.material3:material3:1.2.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.7")
    implementation(libs.androidx.navigation.compose.jvmstubs)
    debugImplementation("androidx.compose.ui:ui-tooling:1.6.7")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.7")

    // Location Services
    implementation("com.google.android.gms:play-services-location:21.1.0")

    // Firebase Firestore (you can add auth/storage/messaging as needed)
    implementation("com.google.firebase:firebase-firestore-ktx:25.0.0")

    // Firestore
    implementation ("com.google.firebase:firebase-firestore-ktx:24.10.3")

    // Location Services
    implementation ("com.google.android.gms:play-services-location:21.0.1")

    // Firebase BOM (optional but useful for versioning)
    implementation ("com.google.firebase:firebase-bom:32.7.3")
}
