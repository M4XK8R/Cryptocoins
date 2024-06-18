plugins {
    alias(libs.plugins.androidLib)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}
android {
    namespace = "com.maxkor.feature.coins.impl"
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
    implementation(project(":feature:coins:api"))
    implementation(project(":core:ui"))

    implementation(libs.moshi)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    implementation(libs.okhttp.interceptor)

    implementation(libs.room)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)

    implementation(libs.hilt)
    ksp(libs.hilt.compiller)
    implementation(libs.hilt.navigation)

    implementation(libs.compose.constraint)
    implementation(libs.compose.lifecycle)
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)

    testImplementation(libs.test.unit.junit)
//    testImplementation("org.mockito:mockito-core:5.8.0")
    testImplementation(libs.test.unit.mockk)
    testImplementation(libs.test.unit.coroutines)

    androidTestImplementation(libs.test.android.espresso.core)
    androidTestImplementation(libs.test.android.junit)
    androidTestImplementation(libs.test.android.mockito)
}

