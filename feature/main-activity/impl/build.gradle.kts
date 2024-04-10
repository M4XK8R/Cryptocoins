plugins {
    alias(libs.plugins.androidLib)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.maxkor.feature.mainactivity.impl"
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Config.kotlinCompilerExtensionVersion
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":feature:main-activity:api"))
    implementation(project(":feature:coins:api"))
    implementation(project(":feature:detail:api"))
    implementation(project(":feature:favorites:api"))

    implementation(libs.hilt)
    ksp(libs.hilt.compiller)
    implementation(libs.hilt.navigation)

    implementation(libs.compose.activity)
    implementation(libs.compose.navigation)
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
}
