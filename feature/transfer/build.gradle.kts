plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.app.transfer"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = Android.testRunner
        consumerProguardFiles("consumer-rules.pro")
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
<<<<<<< HEAD

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Compose
    val composeBom = platform("androidx.compose:compose-bom:2023.05.01")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    // Choose one of the following:
    // Material Design 3
    implementation("androidx.compose.material3:material3")
    // or Material Design 2
    implementation("androidx.compose.material:material")
    // or skip Material Design and build directly on top of foundational components
    implementation("androidx.compose.foundation:foundation")
    // or only import the main APIs for the underlying toolkit systems,
    // such as input and measurement/layout
    implementation("androidx.compose.ui:ui")

    // Android Studio Preview support
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")


    //Navigation
    implementation ("androidx.navigation:navigation-compose:2.6.0")
    implementation(Others.sdp_compose)
    //Constraint Layout
    implementation ("androidx.constraintlayout:constraintlayout-compose:1.0.1")




=======
    implementation(Kotlin.coreKtx)
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
>>>>>>> sprint_02
}