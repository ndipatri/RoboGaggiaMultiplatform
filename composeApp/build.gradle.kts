import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    id("org.jetbrains.kotlin.plugin.serialization") version libs.versions.kotlin.get()
    id("com.squareup.wire") version "5.0.0-alpha03"
    id("com.codingfeline.buildkonfig")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    wire {
        kotlin {}
        sourcePath {
            srcDir("src/commonMain/kotlin/persistence/proto")
        }
    }
    
    sourceSets {
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
            implementation(libs.rive.android)
        }
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(compose.materialIconsExtended)

                api(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(libs.koin.compose.viewmodel)
                implementation(libs.lifecycle.viewmodel)
                implementation(libs.navigation.compose)

                implementation(projects.particle.openapi.client)

                // MQTT
                implementation("io.github.davidepianca98:kmqtt-common:0.4.5")
                implementation("io.github.davidepianca98:kmqtt-client:0.4.5")

                // BLE
                implementation("dev.bluefalcon:blue-falcon:1.0.0")

                // Storage
                api(libs.datastore.core)
                implementation(libs.okio)
                implementation(libs.anthropic.sdk)
                implementation(libs.mcp.sdk)
                implementation("org.jetbrains.kotlinx:kotlinx-io-core:0.8.0")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${libs.versions.kotlinx.serialization.get()}")
            }
        }

        val iosArm64Main by getting {
            dependencies {
                api(files("src/nativeInterop/openssl-ios-arm64.klib"))
            }
        }
        val iosMain by creating {
            dependsOn(commonMain)
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
            gradleLocalProperties(rootDir, providers).getProperty("mqtt.server.address")
        )

        buildConfigField(
            Type.STRING,
            "MQTT_SERVER_TELEMETRY_INTERVAL_MILLIS",
            gradleLocalProperties(rootDir, providers).getProperty("mqtt.server.telemetry.interval.millis")
        )

        buildConfigField(
            com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
            "USE_SIMULATOR",
            gradleLocalProperties(rootDir, providers).getProperty("use.simulator")
        )

        buildConfigField(
            com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
            "PARTICLE_ACCESS_TOKEN",
            gradleLocalProperties(rootDir, providers).getProperty("particle.access.token")
        )

        buildConfigField(
            com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
            "ANTHROPIC_API_KEY",
            gradleLocalProperties(rootDir, providers).getProperty("anthropic.api.key")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }

    dependencies {
        debugImplementation(compose.uiTooling)
    }
}

