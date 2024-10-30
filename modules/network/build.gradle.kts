plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ktorfit)
}

kotlin {

// Target declarations - add or remove as needed below. These define
// which platforms this KMP module supports.
// See: https://kotlinlang.org/docs/multiplatform-discover-project.html#targets
    androidLibrary {
        namespace = "net.fonix232.phoneme.network"
        compileSdk = 34
        minSdk = 24

        withAndroidTestOnJvmBuilder {
            compilationName = "unitTest"
            defaultSourceSetName = "androidUnitTest"
        }

        withAndroidTestOnDeviceBuilder {
            compilationName = "instrumentedTest"
            defaultSourceSetName = "androidInstrumentedTest"
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "network"
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(projects.shared)

                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.koin.core)
                api(libs.koin.annotations)

                implementation(libs.ktorfit.lib)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization.kotlinx.json)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.kotlinx.coroutines.android)
                implementation(libs.koin.android)

                implementation(libs.ktor.client.okhttp)
            }
        }

        androidUnitTest {
            dependencies {
                implementation(libs.kotlinx.coroutines.test)
            }
        }

        androidInstrumentedTest {
            dependencies {
                implementation(libs.kotlinx.coroutines.test)
                implementation(libs.androidx.test.junit)
                implementation(libs.androidx.espresso.core)
                implementation(libs.androidx.runner)
                implementation(libs.androidx.core)
            }
        }

        iosMain {
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
    }
}