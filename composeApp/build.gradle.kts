import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    id("com.codingfeline.buildkonfig")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
        }
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)

                api(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(libs.koin.compose.viewmodel)
                implementation(libs.lifecycle.viewmodel)
                implementation(libs.navigation.compose)

                // MQTT
                implementation("io.github.davidepianca98:kmqtt-common:0.4.5")
                implementation("io.github.davidepianca98:kmqtt-client:0.4.5")

                // BLE
                implementation("dev.bluefalcon:blue-falcon:1.0.0")
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting {
            dependencies {
                api(files("src/nativeInterop/openssl-ios-arm64.klib"))
            }
        }
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
        }
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
            dependencies {
                api(files("src/nativeInterop/openssl-ios-simulator-arm64.klib"))
            }
        }
    }
}

buildkonfig {
    packageName = "com.ndipatri.robogaggia"
    defaultConfigs {

        // after adding a new config field, run
        // 'generateBuildKonfig' task under 'Tasks->buildkonfig'

        buildConfigField(
            Type.STRING,
            "MQTT_SERVER_ADDRESS",
            gradleLocalProperties(rootDir).getProperty("mqtt.server.address")
        )

        buildConfigField(
            Type.STRING,
            "MQTT_SERVER_TELEMETRY_INTERVAL_MILLIS",
            gradleLocalProperties(rootDir).getProperty("mqtt.server.telemetry.interval.millis")
        )

        buildConfigField(
            com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
            "USE_SIMULATOR",
            gradleLocalProperties(rootDir).getProperty("use.simulator")
        )
    }
}

android {
    namespace = "com.ndipatri.roboaggia"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.ndipatri.roboaggia"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
    }

    dependencies {
        debugImplementation(compose.uiTooling)
    }
}

