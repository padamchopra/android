import com.android.build.gradle.AppExtension
import me.padamchopra.android.androidDependencies
import me.padamchopra.android.configureCompose
import me.padamchopra.android.configureDefault
import me.padamchopra.android.configureKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import kotlin.apply

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            extensions.getByType<AppExtension>().apply {
                configureDefault()
                configureCompose()
            }

            configureKotlin()

            androidDependencies()
        }
    }
}
