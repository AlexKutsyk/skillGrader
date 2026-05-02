plugins {
    id("build-jvm")
}

repositories {
    maven {
        name = "LocalRepo"
        url = uri("${rootProject.projectDir}/../core/build/repo")
    }
}

val resourcesFromLib by configurations.creating

dependencies {
    testImplementation(kotlin("test"))
    resourcesFromLib("com.otus.otuskotlin.marketplace:dcompose:1.0:resources@zip")
}

tasks.register<Copy>("extractLibResources") {
    from(zipTree(resourcesFromLib.singleFile))
    into(layout.buildDirectory.dir("dcompose"))
}

tasks["build"].dependsOn("extractLibResources")