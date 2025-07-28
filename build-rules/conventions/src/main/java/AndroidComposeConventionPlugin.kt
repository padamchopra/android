import com.android.build.gradle.LibraryExtension
import me.padamchopra.android.androidDependencies
import me.padamchopra.android.configureBuildTypes
import me.padamchopra.android.configureCompose
import me.padamchopra.android.configureDefault
import me.padamchopra.android.configureKotlin
import me.padamchopra.android.disableBuildConfigGeneration
import me.padamchopra.android.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            extensions.getByType<LibraryExtension>().apply {
                configureDefault()
                configureBuildTypes()
                configureCompose()
                disableBuildConfigGeneration()
            }

            configureKotlin()

            androidDependencies()

            dependencies {
                add("implementation", platform(libs.findLibrary("androidx-compose-bom").get()))
                add("implementation", libs.findBundle("compose").get())
            }
        }
    }
}
