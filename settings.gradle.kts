pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

gradle.startParameter.excludedTaskNames.addAll(listOf(":buildSrc:testClasses"))

rootProject.name = "Cryptocoins"
include(":app")

include(":core")
include(":core:base")
include(":core:theme")

include(":feature")
include(":feature:main-activity")
include(":feature:main-activity:api")
include(":feature:main-activity:impl")

include(":feature:coins")
include(":feature:coins:api")
include(":feature:coins:impl")

include(":feature:favorites")
include(":feature:favorites:api")
include(":feature:favorites:impl")

include(":feature:detail")
include(":feature:detail:api")
include(":feature:detail:impl")
