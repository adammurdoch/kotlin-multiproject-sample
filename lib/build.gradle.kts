plugins {
    id("org.jetbrains.kotlin.multiplatform").version("1.8.0")
}

repositories {
    mavenCentral()
}

group = "test"

kotlin {
    macosX64()
    macosArm64()
    linuxX64()

    val unixMain = sourceSets.create("unixMain") {
        dependsOn(sourceSets.getByName("commonMain"))
    }
    sourceSets.getByName("macosX64Main") {
        dependsOn(unixMain)
    }
    sourceSets.getByName("macosArm64Main") {
        dependsOn(unixMain)
    }
    sourceSets.getByName("linuxX64Main") {
        dependsOn(unixMain)
    }
}