plugins {
    alias(libs.plugins.baad.android.library)
    alias(libs.plugins.baad.android.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.feragusper.buenosairesantesydespues.core.network"

    buildFeatures {
        buildConfig = true
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    api(projects.core.model)
    implementation(projects.core.common)

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlinx.serialization)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
}
