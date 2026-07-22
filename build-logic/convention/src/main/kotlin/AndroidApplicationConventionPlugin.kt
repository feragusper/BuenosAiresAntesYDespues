import com.android.build.api.dsl.ApplicationExtension
import com.feragusper.buildlogic.configureKotlinAndroid
import com.feragusper.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        // AGP 9 ships built-in Kotlin support, so the kotlin-android plugin must not be applied.
        pluginManager.apply("com.android.application")

        extensions.configure<ApplicationExtension> {
            configureKotlinAndroid(this)
            defaultConfig.targetSdk =
                libs.findVersion("targetSdk").get().requiredVersion.toInt()
        }
    }
}
