plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("com.google.gms.google-services")
    id ("kotlin-android")

}

android {
    namespace = "com.example.ghithubrepo"
    compileSdk = 34

    dataBinding{
        isEnabled = true
    }

    lint {

        // If set to true, turns off analysis progress reporting by lint.
        quiet = true

        // Stops the build on lint errors.
        abortOnError = false

        // If true, ignores warnings and only reports errors.
        ignoreWarnings = true
    }

    defaultConfig {
        applicationId = "com.example.ghithubrepo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        multiDexEnabled= true
        resConfigs ("en")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

    }
    compileOptions {
        sourceCompatibility =JavaVersion.VERSION_1_8
        targetCompatibility =JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding= true
    }
}

dependencies {
    implementation ("androidx.core:core-ktx:1.7.0")
    implementation ("androidx.core:core:1.13.0")
    implementation ("androidx.activity:activity:1.9.3")
    implementation ("androidx.annotation:annotation-experimental:1.4.0")

    implementation ("androidx.appcompat:appcompat:1.5.1")
    implementation ("com.google.android.material:material:1.6.1")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(libs.androidx.activity)
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")

    //Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.7.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.2.2")
    implementation("com.squareup.okhttp3:okhttp:4.2.2")
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("com.squareup.retrofit2:retrofit:2.6.2")
    implementation("com.squareup.retrofit2:converter-gson:2.6.2")
    implementation("com.squareup.retrofit2:converter-scalars:2.6.2")

    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.12.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation ("com.google.firebase:firebase-auth:22.3.0")
    implementation ("com.google.android.gms:play-services-auth:21.1.0") // Google Sign-In


    // Core AndroidX lifecycle and ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // Room
    implementation ("androidx.room:room-runtime:2.4.0")
    kapt ("androidx.room:room-compiler:2.4.0")
    implementation ("androidx.room:room-ktx:2.4.0")

    // Image
    implementation("de.hdodenhof:circleimageview:3.0.1")
    implementation ("com.squareup.picasso:picasso:2.8")
    implementation ("com.github.bumptech.glide:glide:4.9.0")                                        //Glide

    implementation ("com.github.ybq:Android-SpinKit:1.4.0")
    implementation ("com.facebook.shimmer:shimmer:0.5.0")
    implementation ("com.github.Elders:EldersSearchView:v1.4.1")


}