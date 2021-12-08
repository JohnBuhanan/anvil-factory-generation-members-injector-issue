plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    maven(uri("https://oss.sonatype.org/content/repositories/snapshots/"))
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
