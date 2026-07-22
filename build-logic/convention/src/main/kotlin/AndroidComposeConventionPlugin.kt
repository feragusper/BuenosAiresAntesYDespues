import com.android.build.api.dsl.CommonExtension
import com.feragusper.buildlogic.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * Applies the Compose compiler plugin and Compose dependencies. Apply on top of a module
 * that already applies `baad.android.library` or `baad.android.application`.
 */
class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

        val extension = extensions.getByType(CommonExtension::class.java)
        configureAndroidCompose(extension)
    }
}
