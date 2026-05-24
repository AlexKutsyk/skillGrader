import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.repositories

group = "com.otus.otuskotlin.skillGrader"
version = "0.0.1"

allprojects {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

subprojects {
    group = rootProject.group
    version = rootProject.version
}

tasks {
    register("clean") {
        group = "build"
        gradle.includedBuilds.forEach {
            dependsOn(it.task(":clean"))
        }
    }
    register("buildInfra") { ->
        dependsOn(
            gradle.includedBuild("core").task(":buildInfra")
        )
    }

    register("buildImages") {
        dependsOn(gradle.includedBuild("project").task(":buildImages"))
    }

    register("e2eTests") { ->
        dependsOn(
            gradle.includedBuild("tests").task(":e2eTests")
        )
    }

    register("check") {
        group = "verification"
        dependsOn(gradle.includedBuild("project").task(":check"))
    }
}