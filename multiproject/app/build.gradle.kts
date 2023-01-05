plugins {
    id("org.jetbrains.kotlin.multiplatform")
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
    mingwX64 {
        binaries {
            executable {
            }
        }
    }

    sourceSets {
        val commonMain = getByName("commonMain") {
            dependencies {
                implementation(project(":lib"))
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