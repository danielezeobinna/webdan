plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

android {
    namespace = "com.danielezeobinna.webdav"
    compileSdk = 35
    defaultConfig { minSdk = 24 }
    publishing {
        singleVariant("release") { withSourcesJar() }
    }
}

dependencies {
    api("org.nanohttpd:nanohttpd:2.3.1")   // api, because FastHTTPD exposes NanoHTTPD types
    implementation("androidx.core:core-ktx:1.13.1")
}

afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.danielezeobinna"
                artifactId = "webdav"
                version = "0.9.0"
            }
        }
    }
}