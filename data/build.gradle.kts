plugins {
    id("com.android.library")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id ("kotlin-kapt")
}

android {
    namespace = "com.app.data"
    compileSdk = Android.compileSdk

    defaultConfig {
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk
        testInstrumentationRunner = Android.testRunner
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
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
}

dependencies {

    implementation(Kotlin.coreKtx)
    implementation(Others.appCompat)
    implementation(Others.material)

    testImplementation(UnitTest.junit)
    androidTestImplementation(UnitTest.JUNIT_TEST)
    androidTestImplementation(UnitTest.Espresso)
    implementation(Lifecycle.runtime)
    implementation(Compose.activity_compose)
    implementation(platform(Compose.composeBom))
    implementation(Compose.composeUi)
    implementation(Compose.graphUi)
    implementation(Compose.compose_tooling_preview)
    implementation(Compose.material3)
    implementation(Lifecycle.runtime)

    implementation(Network.NETWORK_RETROFIT)
    implementation(Network.GSON_CONVERTER)
    implementation(Network.OKHTTP)
    implementation(Network.LIFECYCLEVIEWMODEL)
    implementation(Network.HTTP_LOGGIN)


    //Hilt
    implementation (Hilt.android)
    kapt (Hilt.compiler)
    implementation(Hilt.hilt_compose)

}