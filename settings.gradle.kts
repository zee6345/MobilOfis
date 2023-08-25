pluginManagement {
    repositories {
        google()
        mavenCentral()
        jcenter()
        gradlePluginPortal()

    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven { url =uri("https://jitpack.io") }
        google()
        jcenter()
        mavenCentral()
    }
}
rootProject.name = "Mobil Office"
include(":app")
include(":feature:auth")
include(":uikit")
include(":feature:home")
include(":feature:adjustment")
include(":feature:transfer")
include(":data")
