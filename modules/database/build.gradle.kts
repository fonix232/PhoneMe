plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

kotlin {

    androidLibrary {
        namespace = "net.fonix232.phoneme.database"
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
            baseName = "database"
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

                implementation(libs.room.runtime)
            }

            room {
                schemaDirectory("$projectDir/schemas")
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

                implementation(libs.room.runtime)
                implementation(libs.room.ktx)
            }
        }

        getByName("androidInstrumentedTest") {
            dependencies {
                implementation(libs.androidx.runner)
                implementation(libs.androidx.core)
                implementation(libs.androidx.test.junit)
            }
        }

        iosMain {
            dependencies {
                implementation(libs.room.runtime)
            }
        }
    }

}

dependencies {
    ksp(libs.room.compiler)
}
