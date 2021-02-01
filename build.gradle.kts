// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    //ext.kotlin_version = "1.4.21"
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.2")
        classpath ("com.android.tools.build:gradle:4.1.1")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}
/*
task clean(type: Delete) {
    delete rootProject.buildDir
}
 */
//
//tasks.register("clean", Delete::class){
//    delete(rootProject.buildDir)
//}