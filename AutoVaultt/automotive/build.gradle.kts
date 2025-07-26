plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose") // ✅ required for Kotlin 2.0
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.autovaultt"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.autovaultt"
        minSdk = 28
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13" // or latest version matching your Compose
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }


}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.runtime.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation ("com.google.firebase:firebase-database-ktx")
    implementation ("com.google.android.gms:play-services-location:21.0.1")

        // Firebase Realtime Database
        implementation ("com.google.firebase:firebase-database-ktx")

        // Google Play Location Services
        implementation ("com.google.android.gms:play-services-location:21.0.1")
        implementation("com.google.firebase:firebase-database-ktx:20.3.0")
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
        implementation("androidx.activity:activity-compose:1.8.0") // or newer
        implementation("androidx.compose.material3:material3:1.2.0") // if using Material3
        implementation("com.google.android.gms:play-services-location:21.0.1") // for fusedLocationClient
    implementation("androidx.compose.material3:material3:1.2.1")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:2.0.0"))
    }



