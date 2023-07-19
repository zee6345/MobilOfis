plugins {
    id("com.android.library")
    id ("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.app.home"
    compileSdk =  Android.compileSdk

    defaultConfig {
//        applicationId = "com.app.home"
//        versionCode = 1
//        versionName = "1.0"
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
        jvmTarget = "17"
    }
    buildFeatures {
        compose  = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion =  "1.4.3"
    }
}

dependencies {

    implementation(Kotlin.coreKtx)
    implementation(Others.appCompat)
    implementation(Others.material)
    testImplementation(UnitTest.junit)
    androidTestImplementation(UnitTest.JUNIT_TEST)
    androidTestImplementation(UnitTest.Espresso)
    implementation (Lifecycle.runtime)
    implementation (Compose.activity_compose)
    implementation (platform(Compose.composeBom))
    implementation (Compose.composeUi)
    implementation (Compose.graphUi)
    implementation (Compose.compose_tooling_preview)
    implementation (Compose.material3)
    implementation(Lifecycle.runtime)
    implementation(Compose.activity_compose)

    // Choose one of the following:
    // Material Design 3
//    implementation("androidx.compose.material3:material3")
    // or Material Design 2
    implementation(Compose.normal_material)
    // or skip Material Design and build directly on top of foundational components
    implementation(Compose.compose_foundation)
    // or only import the main APIs for the underlying toolkit systems,
    // such as input and measurement/layout
    implementation(Compose.composeUi)

    // Android Studio Preview support
    implementation(Compose.compose_tooling_preview)
    debugImplementation(Compose.composeTooling)

    //Navigation
    implementation (Compose.composeNavigation)
    implementation(Others.sdp_compose)
    //Constraint Layout
    implementation (Compose.compose_constraints)
}