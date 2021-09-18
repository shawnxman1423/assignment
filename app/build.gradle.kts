plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlinx-serialization")
    id("kotlin-parcelize")
    kotlin("kapt")
}

android {
    compileSdk = AndroidSdk.compile

    defaultConfig {
        applicationId = "io.liba.assignment"
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.target

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
    packagingOptions {
        resources {
            exclude("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    // Core
    implementation("androidx.core:core-ktx:1.7.0-beta01")

    // Kotlin Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}")

    // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}")

    // Compose
    implementation("androidx.compose.ui:ui:${Versions.compose}")
    implementation("androidx.compose.material:material:${Versions.compose}")

    // Compose Android
    implementation("androidx.activity:activity-compose:1.4.0-alpha02")
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha09")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.0-beta02")

    // Compose Accompanist
    implementation("com.google.accompanist:accompanist-flowlayout:${Versions.accompanist}")
    implementation("com.google.accompanist:accompanist-insets:${Versions.accompanist}")
    implementation("com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}")
    implementation("com.google.accompanist:accompanist-swiperefresh:${Versions.accompanist}")
    implementation("com.google.accompanist:accompanist-placeholder-material:${Versions.accompanist}")
    implementation("com.google.accompanist:accompanist-navigation-animation:${Versions.accompanist}")
    implementation("com.google.accompanist:accompanist-navigation-material:${Versions.accompanist}")

    // DI Hilt
    implementation("com.google.dagger:hilt-android:${Versions.hilt}")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0-alpha03")
    implementation("androidx.hilt:hilt-work:1.0.0")
    kapt("com.google.dagger:hilt-android-compiler:${Versions.hilt}")
    kapt("androidx.hilt:hilt-compiler:1.0.0")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0-beta01")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0-beta01")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0-beta01")

    // Room
    implementation("androidx.room:room-runtime:2.4.0-alpha04")
    implementation("androidx.room:room-ktx:2.4.0-alpha04")
    kapt("androidx.room:room-compiler:2.4.0-alpha04")

    // Datastore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Logs
    implementation("com.jakewharton.timber:timber:${Versions.timber}")

    // WorkManager
    implementation("androidx.work:work-runtime-ktx:2.6.0")

    // Splash Screen API
    implementation ("androidx.core:core-splashscreen:1.0.0-alpha01")

    // Tests
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.1.0-alpha04")
    debugImplementation("androidx.compose.ui:ui-tooling:1.1.0-alpha04")
}
