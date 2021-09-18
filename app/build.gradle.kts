plugins {
    id("com.android.application")
    kotlin("android")
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
    implementation ("androidx.core:core-ktx:1.7.0-beta01")

    // Compose
    implementation ("androidx.compose.ui:ui:${Versions.compose}")
    implementation ("androidx.compose.material:material:${Versions.compose}")

    // Compose Android
    implementation ("androidx.activity:activity-compose:1.4.0-alpha02")

    // Lifecycle
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0-beta01")

    // Logs
    implementation ("com.jakewharton.timber:timber:${Versions.timber}")

    // Tests
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:1.1.0-alpha04")
    debugImplementation ("androidx.compose.ui:ui-tooling:1.1.0-alpha04")
}
