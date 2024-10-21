plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.example.odcgithubrepoapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.odcgithubrepoapp"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    debugImplementation(libs.chucker)
    // coil
    implementation(libs.coil.compose)
    // navigation
    implementation(libs.androidx.navigation.compose)



    testImplementation(libs.mockk)
    testImplementation(libs.turbine)
    androidTestImplementation(libs.espresso)

    implementation(libs.lifecycle.runtime.ktx)

    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.runtime.ktx)

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    implementation(libs.coroutines)
    implementation(libs.retrofit)
    implementation(libs.okhttp3)
    implementation(libs.gson)
    implementation(libs.gson.converter)

    implementation(libs.room)
    ksp(libs.room.compiler)

    implementation(libs.datastore)
    implementation(libs.lottie.compose)

    // state bar handling
    // Accompanist for System UI Controller
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.28.0")

    // For Paging 3
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")
    implementation ("androidx.paging:paging-runtime:3.3.2")
    implementation ("androidx.paging:paging-compose:3.3.2")
    implementation ("androidx.room:room-paging:2.5.2")

    //icons-extended
    implementation ("androidx.compose.material:material-icons-extended:1.5.0")

    // module
    implementation(project(":domain"))

    // testing
    testImplementation ("io.mockk:mockk:1.13.7")
    testImplementation ("io.mockk:mockk-android:1.13.7")  // For Android-specific testing (optional)
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3" ) // For coroutines testing
    testImplementation ("junit:junit:4.13.2" ) // For JUnit
}