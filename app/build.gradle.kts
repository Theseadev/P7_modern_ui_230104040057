plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "id.antasari.p7_modern_ui_230104040057"
    compileSdk = 36   // PENTING !!!

    defaultConfig {
        applicationId = "id.antasari.p7_modern_ui_230104040057"
        minSdk = 24
        targetSdk = 36      // Samakan dengan compileSdk modern
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
}

dependencies {

    // Core
    implementation("androidx.core:core-ktx:1.17.0")
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Compose BOM
    implementation(platform(libs.androidx.compose.bom))

    // Compose core UI
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)

    // Material3 resmi dan terbaru
    implementation("androidx.compose.material3:material3:1.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

    // Material2 (opsional, hanya jika kamu pakai TopAppBar lama)
    implementation("androidx.compose.material:material:1.6.8")

    // Navigation Jetpack Compose
    implementation("androidx.navigation:navigation-compose:2.7.5")

    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
