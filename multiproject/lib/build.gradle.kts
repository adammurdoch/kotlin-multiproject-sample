plugins {
    id("org.jetbrains.kotlin.multiplatform")
}

repositories {
    mavenCentral()
}

kotlin {
    macosX64()
    macosArm64()
    linuxX64()
    mingwX64()

    sourceSets {
        val commonMain = getByName("commonMain") {
        }
        val unixMain = create("unixMain") {
            dependsOn(commonMain)
        }
        getByName("macosX64Main") {
            dependsOn(unixMain)
        }
        getByName("macosArm64Main") {
            dependsOn(unixMain)
        }
        getByName("linuxX64Main") {
            dependsOn(unixMain)
        }
    }
}