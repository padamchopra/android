plugins {
    id("android.compose")
}

android {
    namespace = "me.padamchopra.android.composables"
}

dependencies {
    implementation(project(":design"))
}
