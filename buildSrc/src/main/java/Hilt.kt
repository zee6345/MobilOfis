object Hilt {
    const val version = "2.44.2"
const val version_compose = "1.1.0-alpha01"

    const val android = "com.google.dagger:hilt-android:$version"
    const val compiler = "com.google.dagger:hilt-compiler:$version"
    const val hilt_compose = "androidx.hilt:hilt-navigation-compose:$version_compose"
}

object HiltTest {
    const val hiltAndroidTesting = "com.google.dagger:hilt-android-testing:${Hilt.version}"
}