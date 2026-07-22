package com.feragusper.buildlogic

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * Configures the common Android + Kotlin options shared by every Android module
 * (application and library) so each module's build script stays a thin plugins {} block.
 *
 * Uses property-access style (not `{}` action blocks) because AGP 9 removed the
 * Action-accepting overloads from the raw DSL interfaces.
 */
internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension,
) {
    val javaTarget = libs.findVersion("javaTarget").get().requiredVersion

    commonExtension.compileSdk = libs.findVersion("compileSdk").get().requiredVersion.toInt()
    commonExtension.defaultConfig.minSdk = libs.findVersion("minSdk").get().requiredVersion.toInt()
    commonExtension.compileOptions.sourceCompatibility = JavaVersion.toVersion(javaTarget)
    commonExtension.compileOptions.targetCompatibility = JavaVersion.toVersion(javaTarget)

    tasks.withType(KotlinCompile::class.java).configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.fromTarget(javaTarget))
        }
    }
}

/**
 * Configures a plain Kotlin/JVM (non-Android) module — used by the domain layer.
 */
internal fun Project.configureKotlinJvm() {
    val javaTarget = libs.findVersion("javaTarget").get().requiredVersion

    extensions.getByType<JavaPluginExtension>().apply {
        sourceCompatibility = JavaVersion.toVersion(javaTarget)
        targetCompatibility = JavaVersion.toVersion(javaTarget)
    }

    tasks.withType(KotlinCompile::class.java).configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.fromTarget(javaTarget))
        }
    }
}
