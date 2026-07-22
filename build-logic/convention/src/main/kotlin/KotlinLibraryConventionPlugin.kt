import com.feragusper.buildlogic.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Plain Kotlin/JVM library convention (no Android) — used by the pure domain layer.
 */
class KotlinLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("org.jetbrains.kotlin.jvm")
        configureKotlinJvm()
    }
}
