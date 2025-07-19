plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    id ("com.google.gms.google-services")
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
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
    dependencies {
        // Firebase Realtime Database
        implementation ("com.google.firebase:firebase-database-ktx")

        // Google Play Location Services
        implementation ("com.google.android.gms:play-services-location:21.0.1")
        implementation("com.google.firebase:firebase-database-ktx:20.3.0")
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
        implementation("androidx.activity:activity-compose:1.8.0") // or newer
        implementation("androidx.compose.material3:material3:1.2.0") // if using Material3
        implementation("com.google.android.gms:play-services-location:21.0.1") // for fusedLocationClient
        implementation("androidx.compose.material3:material3:<latest_version>")
    }



}