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

//    implementation (project (":core:designsystem"))
//    implementation (project (":feature:auth" ))
    implementation (project (":feature:adjustment" ))
    implementation (project (":feature:transfer" ))



    implementation(Kotlin.coreKtx)
    implementation(Others.appCompat)
    implementation(Others.material)
    implementation(project(mapOf("path" to ":feature:network")))
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

    implementation(Compose.normal_material)
//    implementation(Compose.compose_foundation)
    implementation(Compose.composeUi)
    implementation(Compose.compose_tooling_preview)
    debugImplementation(Compose.composeTooling)


    //Navigation
    implementation (Compose.composeNavigation)
    implementation(Others.sdp_compose)
    //Constraint Layout
    implementation (Compose.compose_constraints)

    implementation ("androidx.compose.foundation:foundation:1.5.0-alpha04")



}