pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/sqldelight/maven") // ✅ Needed
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        // ✅ Required for resolving SQLDelight runtime + drivers
        maven("https://maven.pkg.jetbrains.space/public/p/sqldelight/maven")
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "I_Weather"
include(":androidApp")
include(":shared")
