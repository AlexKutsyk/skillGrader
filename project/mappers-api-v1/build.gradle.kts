plugins {
    id("build-jvm")
}

group = rootProject.group
version = rootProject.version

dependencies {
    implementation(kotlin("stdlib"))
    implementation(projects.jacksonApiV1)
    implementation(projects.common)

    testImplementation(kotlin("test-junit"))
    testImplementation(projects.stubs)
}