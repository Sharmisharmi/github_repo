buildscript {
//    ext = '1.6.21'
//    ext.lifecycle_version = '2.3.1'
//    ext("1.6.21")
//    plugins{
//        kotlin("jvm") version ("1.6.21")
//    }

    repositories {
        google()
        maven {
            url = uri("https://maven.google.com")
        }
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }

        dependencies {
            classpath("com.android.tools:r8:8.1.56")
            classpath ("com.android.tools.build:gradle:8.1.0")
            classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")

            // NOTE: Do not place your application dependencies here; they belong
            // in the individual module build.gradle files
        }
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")
    }

    allprojects {
        repositories {
            maven { url = uri("https://jitpack.io") }
            google()
            mavenCentral()
            jcenter()

        }

    }

}