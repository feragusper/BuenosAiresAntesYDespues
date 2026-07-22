plugins {
    alias(libs.plugins.baad.android.feature)
}

android {
    namespace = "com.feragusper.buenosairesantesydespues.feature.records"
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.core.data)
    implementation(projects.core.model)
    implementation(projects.core.common)

    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    implementation(libs.maps.compose)
    implementation(libs.play.services.maps)
    implementation(libs.androidx.compose.material.icons.extended)
}
