import com.android.build.gradle.BaseExtension
import example.gradle.libs
import example.gradle.resolveDependency

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("example.gradle.dagger")
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

    defaultConfig {
        testInstrumentationRunnerArguments["clearPackageData"] = "true"
    }

    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
    }
}

dependencies {
    "implementation"(project(":common:feature-runner-support"))
    "implementation"(project(":common:dagger-viewmodel-support"))

    "androidTestImplementation"(project(":common:feature-runner-test-support"))
    "androidTestImplementation"(libs.resolveDependency("androidx.test.runner"))

    "androidTestUtil"(libs.resolveDependency("androidx.test.orchestrator"))
}
