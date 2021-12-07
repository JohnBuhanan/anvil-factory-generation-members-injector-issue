import com.squareup.anvil.plugin.AnvilExtension
import example.gradle.libs
import example.gradle.resolveDependency

plugins {
    id("properties")
    id("com.squareup.anvil")
}

fun Project.isAndroidModule() = plugins.hasPlugin("com.android.base")
fun Project.isAndroidAppModule() = plugins.hasPlugin("com.android.application")

val runkapt: Boolean by project
val generateFactories: Boolean by project

fun isDaggerReflect() = !runkapt

fun isGenerateFactories() = generateFactories && runkapt && !isAndroidAppModule()

if (!isDaggerReflect() && !isGenerateFactories()) {
    plugins.apply("org.jetbrains.kotlin.kapt")
}


if (isGenerateFactories()) {
    configure<AnvilExtension>() {
        generateDaggerFactories.set(true)
    }
}


// define function in here to be able to get access to project object
fun DependencyHandlerScope.applyDaggerToAndroidModule() {
    if (isDaggerReflect()) {
        "debugImplementation"(libs.resolveDependency("dagger.reflect"))
    } else {
        "debugImplementation"(libs.resolveDependency("dagger.codegen"))
        "releaseImplementation"(libs.resolveDependency("dagger.codegen"))

        if (!isGenerateFactories()) {
            "kaptDebug"(libs.resolveDependency("dagger.compiler"))
            "kaptAndroidTestDebug"(libs.resolveDependency("dagger.compiler"))
            "kaptRelease"(libs.resolveDependency("dagger.compiler"))
            if (configurations.findByName("kaptAndroidTestRelease") != null) {
                "kaptAndroidTestRelease"(libs.resolveDependency("dagger.compiler"))
            }
        }
    }

    "implementation"(project(":common:dagger-support"))
}

// define inline so we can get access to isDaggerReflect
fun DependencyHandlerScope.applyDaggerToJvmModule() {
    if (isDaggerReflect()) {
        "implementation"(libs.resolveDependency("dagger.reflect"))
    } else {
        "implementation"(libs.resolveDependency("dagger.codegen"))
        if (!isGenerateFactories()) {
            "kapt"(libs.resolveDependency("dagger.compiler"))
        }
    }
}

dependencies {
    if (isAndroidModule()) {
        applyDaggerToAndroidModule()
    } else {
        applyDaggerToJvmModule()
    }
    "implementation"(libs.resolveDependency("dagger.lib"))
}
