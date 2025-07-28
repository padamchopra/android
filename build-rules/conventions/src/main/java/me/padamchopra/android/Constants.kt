package me.padamchopra.android

object Constants {
    // environment variables
    const val BUILD_CODE = "BUILD_CODE"
    const val FALLBACK_BUILD_CODE = 1
    const val APP_VERSION_NAME = "APP_VERSION_NAME"
    const val FALLBACK_APP_VERSION_NAME = "0.0.0-debug"

    // build types
    const val BUILD_TYPE_DEBUG = "debug"

    // source sets
    const val TYPE_DEV_BASE_DIR = "src/dev"
    const val TYPE_DEV_SRC_DIR = "$TYPE_DEV_BASE_DIR/java"
    const val TYPE_DEV_RES_DIR = "$TYPE_DEV_BASE_DIR/res"

    const val TYPE_RELEASE_BASE_DIR = "src/release"
    const val TYPE_RELEASE_SRC_DIR = "$TYPE_RELEASE_BASE_DIR/java"
    const val TYPE_RELEASE_RES_DIR = "$TYPE_RELEASE_BASE_DIR/res"
}
