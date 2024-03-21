plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.googleHiltAndroid)
    alias(libs.plugins.googleKsp)
}

android {
    namespace = "com.maxkor.cryptocoins"
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":core:base"))
    implementation(project(":core:theme"))
    implementation(project(":core:ui"))

    implementation(project(":feature:main-activity:api"))
    implementation(project(":feature:main-activity:impl"))
    implementation(project(":feature:coins:api"))
    implementation(project(":feature:coins:impl"))
    implementation(project(":feature:favorites:api"))
    implementation(project(":feature:favorites:impl"))
    implementation(project(":feature:detail:api"))
    implementation(project(":feature:detail:impl"))

    implementation(project(":database:local:host"))
    implementation(project(":database:local:intermediary-coins"))
    implementation(project(":database:local:intermediary-favorites"))

    implementation(libs.androidx.room.ktx)
    api(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.google.hilt)
    ksp(libs.google.hilt.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.material)

//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
//    androidTestImplementation(platform(libs.androidx.compose.bom))
//    androidTestImplementation(libs.androidx.ui.test.junit4)
//    debugImplementation(libs.androidx.ui.tooling)
//    debugImplementation(libs.androidx.ui.test.manifest)
}