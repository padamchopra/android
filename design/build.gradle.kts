plugins {
    id("android.compose")
}

android {
    namespace = "me.padamchopra.android.design"
}

dependencies {
    implementation(libs.material)
    implementation(libs.androidx.material3)
}
