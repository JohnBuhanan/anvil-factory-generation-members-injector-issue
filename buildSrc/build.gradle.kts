plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(libs.agp)
    implementation(libs.kotlin.gradle)
    implementation(gradleApi())
    implementation(localGroovy())

    implementation(libs.anvil)

    testImplementation(libs.assertk)
    testImplementation(libs.junit4)
}
