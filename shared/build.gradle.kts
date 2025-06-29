plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.androidLibrary)

}

sqldelight {
    databases {
        create("IWeatherDB") {
            packageName.set("com.prathameshkumbhar.iweather.shared.database")
        }
    }
}

kotlin {
    jvm()
    androidTarget {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {

        sourceSets.androidMain.dependencies {
            implementation(libs.android.driver)
        }

        sourceSets.nativeMain.dependencies {
            implementation(libs.native.driver)
        }

        sourceSets.jvmMain.dependencies {
            implementation(libs.sqlite.driver)
        }

        val commonMain by getting {
            dependencies {
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.client.json)
                implementation(libs.ktor.client.serialization)
                implementation(libs.serialization.json)
                implementation(libs.koin.core)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.client.okhttp)
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting

        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
    }
}

android {
    namespace = "com.prathameshkumbhar.iweather"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
