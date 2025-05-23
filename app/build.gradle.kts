plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.two.coders.moviemood"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.two.coders.moviemood"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true // Enable code shrinking
            isShrinkResources = true // Enable resource shrinking
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), // Default ProGuard rules
                "proguard-rules.pro" // Custom ProGuard rules
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
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.hilt)
    implementation(libs.hiltNavigationCompose)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    implementation(libs.logging.interceptor)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    kapt(libs.hiltCompiler)
    testImplementation(kotlin("test"))
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.junit)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.core.testing)

}