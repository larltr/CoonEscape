plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false

    // navigation safe args
    id ("androidx.navigation.safeargs") version ("2.5.3") apply false
}