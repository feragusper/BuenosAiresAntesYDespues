plugins {
    alias(libs.plugins.baad.android.library)
    alias(libs.plugins.baad.android.hilt)
}

android {
    namespace = "com.feragusper.buenosairesantesydespues.core.data"
}

dependencies {
    api(projects.core.model)
    implementation(projects.core.common)
    implementation(projects.core.network)

    implementation(libs.kotlinx.coroutines.android)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.turbine)
}
