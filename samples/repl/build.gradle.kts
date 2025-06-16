import com.dokar.quickjs.disableUnsupportedPlatformTasks

plugins {
    id("application")
    alias(libs.plugins.kotlinMultiplatform)
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

kotlin {
    jvm {
        withJava()
    }

    mingwX64 {
        binaries {
            executable()
        }
    }
    linuxX64 {
        binaries {
            executable()
        }
    }

    applyDefaultHierarchyTemplate()

    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.quickjs)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.clikt)
            }
        }
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

application {
    mainClass.set("ReplMainKt")
}

disableUnsupportedPlatformTasks()
