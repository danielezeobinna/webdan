import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

android {
    namespace = "io.github.webdanlib.webdav"
    //noinspection GradleDependency
    compileSdk = 35
    defaultConfig { minSdk = 24 }
    publishing {
        singleVariant("release") { withSourcesJar() }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_11)
    }
}

dependencies {
    //noinspection UseTomlInstead
    api("org.nanohttpd:nanohttpd:2.3.1")   // api, because FastHTTPD exposes NanoHTTPD types
    //noinspection GradleDependency
    implementation("androidx.core:core-ktx:1.13.1")
}

afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("release") {
                from(components["release"])
                groupId = "io.github.webdanlib"
                artifactId = "webdav"
                version = "0.9.0"
            }
        }
    }
}