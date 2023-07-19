plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
//    id ("kotlin-kapt")
    id ("com.google.dagger.hilt.android")
    id ("com.google.devtools.ksp")
    id ("kotlin-kapt")
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

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        jvmTarget = "17"
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

    implementation (project (":core:designsystem"))
    implementation (project (":feature:auth" ))
    implementation (project (":feature:adjustment" ))
    implementation (project (":feature:home" ))
    implementation (project (":feature:transfer" ))


    implementation (Libs.core)
    implementation (Lifecycle.runtime)
    implementation (Compose.activity_compose)
    implementation (platform(Compose.composeBom))
    implementation (Compose.composeUi)
    implementation (Compose.graphUi)
    implementation (Compose.compose_tooling_preview)
    implementation (Compose.material3)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation (platform(Compose.composeBom))
    androidTestImplementation (Compose.junit4compose)
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation (Compose.composeTooling)
    debugImplementation (Compose.composeTest)
    implementation(Others.sdp_compose)
    //Navigation
    //def nav_version = "2.6.0"
    implementation ("androidx.navigation:navigation-compose:2.6.0")

    //Hilt
    implementation (Hilt.android)
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    kapt (Hilt.compiler)
}