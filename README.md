# Kotlin multi-platform sample

This repository contains some sample Kotlin multi-platform applications that reproduce several issues related to
usage in a composite build.

The [sample app](app/build.gradle.kts) targets macOS, Linux and Windows and adds a custom `unixMain` source set inherited 
by the macOS and Linux targets.
The app uses a [simple library](lib/build.gradle.kts), with the same targets and source set configuration.

The app and library are in separate included builds.

## Issue 1: `commonizeNativeDistribution` fails with `java.nio.channels.OverlappingFileLockException`

It seems there is a `commonizeNativeDistribution` task injected into the root project of each build in the tree.
When there are multiple such builds in the tree, these tasks may run in parallel and seem to conflict with each other.

To see this, run:

```shell
# Individual binary can be linked successfully
> ./gradlew app:linkMacosArm64

# Full build of project usually fails with an overlapping lock exceptio, but not always
> ./gradlew app:build

# Force the tasks to run sequentially.
# Does not fail with an overlapping lock exception, fails with the next problem
> ./gradlew app:build --max-workers=1
```

## Issue 2: App `compileUnixMainKotlinMetadata` fails with unresolved reference to library

The app [uses a class](app/src/unixMain/kotlin/App.kt#L2) from the library.
When the same app and library are part of the same Gradle multi-project build, the app can be built correctly.
However, when the app and library are in separate included builds, building the app fails with an unresolved reference.

To see this, run:

```shell
# App and lib in same build -> succeeds
> ./gradlew multiproject:app:build

# App and lib in separate builds -> fails
> ./gradlew app:build --max-workers=1
```

## Issue 3: References to library are not resolved in IDEA

When the build is imported into IDEA, the references to the library in the app are usually not resolved.
Sometimes the references are correctly resolved, but I haven't figured out what triggers this.

When I manually add a dependency between the source sets in IDEA, then the references are correctly resolved.
