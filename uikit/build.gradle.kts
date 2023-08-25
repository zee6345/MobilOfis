plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id ("com.google.devtools.ksp")
    id("kotlin-kapt")
}

android {
    namespace = "com.app.uikit"
    compileSdk = Android.compileSdk

    defaultConfig {
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk

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
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        compose  = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion =  "1.4.3"
    }
}

dependencies {


//    implementation (project (":feature:adjustment"))
//    implementation (project (":feature:transfer"))

    implementation(Kotlin.coreKtx)
    implementation(Others.appCompat)
    implementation(Others.material)
    implementation(project(mapOf("path" to ":data")))
    testImplementation(UnitTest.junit)
    androidTestImplementation(UnitTest.JUNIT_TEST)
    androidTestImplementation(UnitTest.Espresso)

    implementation(Kotlin.coreKtx)
    implementation(Others.appCompat)
    implementation(Others.material)
//    testImplementation(UnitTest.junit)
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
    implementation(Compose.activity_compose)

    implementation(Compose.normal_material)
//    implementation(Compose.compose_foundation)
    implementation(Compose.composeUi)
    implementation(Compose.compose_tooling_preview)
    debugImplementation(Compose.composeTooling)


    //Navigation
    implementation(Compose.composeNavigation)
    implementation(Others.sdp_compose)
    //Constraint Layout
    implementation(Compose.compose_constraints)
    implementation(Compose.compose_foundation)

    implementation(Compose.progress_indicator)



    //Hilt
    implementation(Hilt.android)
    kapt(Hilt.compiler)
    implementation(Hilt.hilt_compose)

}