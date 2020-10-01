import org.jetbrains.kotlin.contracts.model.structure.UNKNOWN_COMPUTATION.type
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
    application
}
group = "me.yolan"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies{
    implementation("org.litote.kmongo:kmongo:4.1.2")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}
sourceSets.main {
    java.srcDirs("src/main/kotlin")
}
application {
    mainClassName = "MainKt"
}
