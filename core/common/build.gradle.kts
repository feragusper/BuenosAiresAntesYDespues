plugins {
    alias(libs.plugins.baad.android.library)
    alias(libs.plugins.baad.android.hilt)
}

android {
    namespace = "com.feragusper.buenosairesantesydespues.core.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
}
