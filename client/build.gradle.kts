plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.20-RC"
    id("com.squareup.sqldelight")
}

version = "1.0"
val ktorVersion = "2.0.0-beta-1"
val mockKVersion = "1.12.2"
kotlin {
    android()
//    iosX64()
//    iosArm64()
//    iosSimulatorArm64()

    cocoapods {
        summary = "Ktor simple client"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "client"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.squareup.sqldelight:runtime:1.5.3")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")
                implementation("io.ktor:ktor-client-cio:$ktorVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
//                implementation("io.mockk:mockk-common:$mockKVersion")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation ("com.squareup.sqldelight:android-driver:1.5.3")
                implementation("io.ktor:ktor-client-android:$ktorVersion")
//                implementation("io.mockk:mockk:$mockKVersion")
//                implementation("io.mockk:mockk-agent-jvm:$mockKVersion")
            }
        }
        val androidTest by getting
//        val iosX64Main by getting
//        val iosArm64Main by getting
//        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
//            iosX64Main.dependsOn(this)
//            iosArm64Main.dependsOn(this)
//            iosSimulatorArm64Main.dependsOn(this)
            dependencies {

                implementation ("com.squareup.sqldelight:native-driver:1.5.3")
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
            }
        }
//        val iosX64Test by getting
//        val iosArm64Test by getting
//        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
//            iosX64Test.dependsOn(this)
//            iosArm64Test.dependsOn(this)
//            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 23
        targetSdk = 31
    }
}

sqldelight {
    database("MovieDatabase") { // This will be the name of the generated database class.
        packageName = "com.lucas.schiavini.client.db"
        sourceFolders = listOf("sqldelight")
    }
}


//dependencies {
//    testImplementation("org.junit.jupiter:junit-jupiter")
//}