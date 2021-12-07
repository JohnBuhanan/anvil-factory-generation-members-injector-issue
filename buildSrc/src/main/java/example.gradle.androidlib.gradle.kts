import com.android.build.gradle.BaseExtension

plugins {
    id("com.android.library")
    kotlin("android")
}

with(the<BaseExtension>()) {
    compileSdkVersion(30)

    defaultConfig {
        minSdk = 23
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"
    }

    packagingOptions {
        excludes += "META-INF/AL2.0"
        excludes += "META-INF/LGPL2.1"
    }
}

dependencies {

}
