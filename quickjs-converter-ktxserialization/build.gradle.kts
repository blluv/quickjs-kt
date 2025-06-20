import com.dokar.quickjs.disableUnsupportedPlatformTasks

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.mavenPublish)
    alias(libs.plugins.serialization)
}

kotlin {
    androidTarget {
        publishLibraryVariants("release")
    }
    jvm()

    macosArm64()

    applyDefaultHierarchyTemplate()

    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":quickjs"))
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.serialization.properties)
            }
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlinx.coroutines.test)
        }
    }
}

android {
    namespace = "com.dokar.quickjs.converter.ktxserialization"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

disableUnsupportedPlatformTasks()

afterEvaluate {
    // Disable Android tests
    tasks.named("testDebugUnitTest").configure {
        enabled = false
    }
    tasks.named("testReleaseUnitTest").configure {
        enabled = false
    }
}
