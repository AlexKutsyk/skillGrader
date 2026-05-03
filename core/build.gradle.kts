plugins {
    id("build-jvm")
    id("maven-publish")
}

group = "com.otus.otuskotlin.skillGrader"
version = "0.0.1"

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    group = rootProject.group
    version = rootProject.version
}
