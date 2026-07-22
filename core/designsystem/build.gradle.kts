plugins {
    alias(libs.plugins.baad.android.library)
    alias(libs.plugins.baad.android.compose)
}

android {
    namespace = "com.feragusper.buenosairesantesydespues.core.designsystem"
}

dependencies {
    implementation(libs.androidx.compose.material.icons.extended)
}
