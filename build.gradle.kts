plugins {
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
}
buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://maven.google.com") }
    }

    dependencies {
        classpath("com.android.tools.build:gradle:8.1.0")
        classpath("com.google.gms:google-services:4.4.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.1.0")
        classpath("com.android.tools:r8:8.1.56")
    }
}

// This block should be outside buildscript
allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        jcenter()
    }
}
