import java.util.Properties

plugins {
    alias(libs.plugins.baad.android.application)
    alias(libs.plugins.baad.android.compose)
    alias(libs.plugins.baad.android.hilt)
    alias(libs.plugins.google.services)
}

// Read secrets from local.properties (kept out of version control).
val localProps = Properties().apply {
    val f = rootProject.file("local.properties")
    if (f.exists()) f.inputStream().use { load(it) }
}
val mapsApiKeyDebug: String = localProps.getProperty("MAPS_API_KEY", "")
val mapsApiKeyRelease: String = localProps.getProperty("MAPS_API_KEY_RELEASE", mapsApiKeyDebug)

// Release signing config from keystore.properties (also out of version control).
val keystoreProps = Properties().apply {
    val f = rootProject.file("keystore.properties")
    if (f.exists()) f.inputStream().use { load(it) }
}
val hasReleaseSigning = keystoreProps.getProperty("storeFile") != null

android {
    namespace = "com.feragusper.buenosairesantesydespues"

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.feragusper.buenosairesantesydespues"
        versionCode = 300
        versionName = "2.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        manifestPlaceholders["MAPS_API_KEY"] = mapsApiKeyDebug
    }

    signingConfigs {
        getByName("debug") {
            storeFile = rootProject.file("buildsystem/debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
        if (hasReleaseSigning) {
            create("release") {
                storeFile = rootProject.file(keystoreProps.getProperty("storeFile"))
                storePassword = keystoreProps.getProperty("storePassword")
                keyAlias = keystoreProps.getProperty("keyAlias")
                keyPassword = keystoreProps.getProperty("keyPassword")
            }
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".dev"
            signingConfig = signingConfigs.getByName("debug")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            manifestPlaceholders["MAPS_API_KEY"] = mapsApiKeyRelease
            if (hasReleaseSigning) {
                signingConfig = signingConfigs.getByName("release")
            }
        }
    }
}

dependencies {
    implementation(projects.feature.records)
    implementation(projects.core.designsystem)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.messaging)
    implementation(libs.firebase.analytics)

    testImplementation(libs.junit)
}
