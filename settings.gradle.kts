// This file is typically located in your project's root directory and is crucial for plugin resolution.

rootProject.name = "Clubs"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                // FIX: Explicitly include 'com.google.devtools' to allow KSP resolution.
                includeGroupAndSubgroups("com.google.devtools")
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    // PREFER_PROJECT means modules can override but usually inherit from here
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        google()
        mavenCentral()
    }
}

include(":composeApp")
