import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    id("com.codingfeline.buildkonfig")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
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
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
        }
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.ui)
                @OptIn(ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                // MQTT
                implementation("io.github.davidepianca98:kmqtt-common:0.4.5")
                implementation("io.github.davidepianca98:kmqtt-client:0.4.5")
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
        // 'generateBuildKong' task under 'Tasks->buildkonfig'

        buildConfigField(
            Type.STRING,
            "AIO_USERNAME",
            gradleLocalProperties(rootDir).getProperty("aio.username")
        )

        buildConfigField(
            Type.STRING,
            "AIO_PASSWORD",
            gradleLocalProperties(rootDir).getProperty("aio.password")
        )

        buildConfigField(
            Type.STRING,
            "USE_GAGGIA_SIMULATOR",
            gradleLocalProperties(rootDir).getProperty("use.gaggia.simulator")
        )

        buildConfigField(
            Type.STRING,
            "MQTT_SERVER_ADDRESS",
            gradleLocalProperties(rootDir).getProperty("mqtt.server.address")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

