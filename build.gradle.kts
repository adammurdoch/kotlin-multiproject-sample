tasks.register("clean") {
    dependsOn(gradle.includedBuild("app").task(":clean"))
    dependsOn(gradle.includedBuild("lib").task(":clean"))
    dependsOn(gradle.includedBuild("multiproject").task(":app:clean"))
    dependsOn(gradle.includedBuild("multiproject").task(":lib:clean"))
}