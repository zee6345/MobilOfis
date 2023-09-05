plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("com.google.dagger.hilt.android")
    id ("com.google.devtools.ksp")
    id ("kotlin-kapt")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.app.mobiloffice"
    compileSdk = Android.compileSdk

    defaultConfig {
        applicationId = Android.appId
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk
        versionCode = Android.versionCode
        versionName = Android.versionName

        testInstrumentationRunner = Android.testRunner
        vectorDrawables {
            useSupportLibrary = true
        }
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
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

// Allow references to generated code
//ksp {
//    correctErrorTypes = true
//}

dependencies {

    constraints {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.0") {
            because("kotlin-stdlib-jdk7 is now a part of kotlin-stdlib")
        }
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0") {
            because("kotlin-stdlib-jdk8 is now a part of kotlin-stdlib")
        }
    }

    implementation (project (":uikit"))
    implementation (project (":feature:auth" ))
    implementation (project (":feature:adjustment" ))
    implementation (project (":feature:home" ))
    implementation (project (":feature:transfer" ))


    implementation (Libs.core)
    implementation (Lifecycle.runtime)
    implementation (Compose.activity_compose)
    implementation (platform(Compose.composeBom))
    implementation (Compose.composeUi)
    implementation (Compose.composeAnimNavigation)
    implementation (Compose.graphUi)
    implementation (Compose.compose_tooling_preview)
    implementation (Compose.material3)
    implementation(Lifecycle.runtime)
    implementation(Compose.activity_compose)
    implementation(project(mapOf("path" to ":data")))
    implementation(project(mapOf("path" to ":data")))
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    androidTestImplementation (UnitTest.JUNIT_TEST)
    androidTestImplementation (UnitTest.Espresso)
//    androidTestImplementation (Compose.junit4compose)
    debugImplementation (Compose.composeTooling)
    debugImplementation (Compose.composeTest)
    implementation(Others.sdp_compose)
    //Navigation
    implementation (Compose.composeNavigation)

    //Hilt
    implementation (Hilt.android)
    kapt (Hilt.compiler)
    implementation(Hilt.hilt_compose)

//    implementation ("com.bugfender.sdk:android:3.+")
    implementation ("androidx.localbroadcastmanager:localbroadcastmanager:1.1.0")

    implementation ("com.google.firebase:firebase-messaging:23.2.1")


}