package me.padamchopra.android

import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun BaseExtension.configureDefault() {
    setCompileSdkVersion(ProjectConfig.compileSdkVersion)

    with(defaultConfig) {
        minSdk = ProjectConfig.minSdkVersion
        targetSdk = ProjectConfig.targetSdkVersion
        vectorDrawables.useSupportLibrary = true

        versionCode = System.getenv(Constants.BUILD_CODE)?.toInt() ?: Constants.FALLBACK_BUILD_CODE
        versionName = System.getenv(Constants.APP_VERSION_NAME) ?: Constants.FALLBACK_APP_VERSION_NAME
    }

    with(compileOptions) {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
        isCoreLibraryDesugaringEnabled = true
    }
}

internal fun BaseExtension.configureBuildTypes() {
    sourceSets {
        getByName(Constants.BUILD_TYPE_DEBUG) {
            manifest.srcFile("${Constants.TYPE_DEV_BASE_DIR}/AndroidManifest.xml")
            res.srcDirs(Constants.TYPE_DEV_RES_DIR)
            java.srcDirs(Constants.TYPE_DEV_SRC_DIR)
        }
    }
}

internal fun BaseExtension.configureCompose() {
    with(buildFeatures) {
        compose = true
    }
}

internal fun BaseExtension.disableBuildConfigGeneration() {
    with(buildFeatures) {
        buildConfig = false
    }
}

internal fun Project.configureKotlin() {
    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions.jvmTarget.set(JvmTarget.JVM_11)
    }
}

internal fun Project.androidDependencies() {
    dependencies {
        add("coreLibraryDesugaring", libs.findLibrary("desugarJdkLibs").get())
        add("implementation", libs.findLibrary("timber").get())
    }
}
