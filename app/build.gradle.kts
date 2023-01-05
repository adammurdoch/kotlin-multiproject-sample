plugins {
    id("org.jetbrains.kotlin.multiplatform").version("1.8.0")
}

repositories {
    mavenCentral()
}

kotlin {
    macosX64 {
        binaries {
            executable {
            }
        }
    }
    macosArm64 {
        binaries {
            executable {
            }
        }
    }
    linuxX64 {
        binaries {
            executable {
            }
        }
    }

    sourceSets {
        val commonMain = getByName("commonMain") {
            dependencies {
                implementation("test:lib:1.0")
            }
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