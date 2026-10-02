plugins {
    kotlin("jvm") version "2.4.20"
    id("xyz.jpenilla.run-paper") version "3.1.0"
    id("com.gradleup.shadow") version "8.3.6"
    java
}

version = property("plugin_version") as String
val mcVersion = property("minecraft_version") as String

repositories {
    mavenCentral()
    maven(url = "https://repo.papermc.io/repository/maven-public/") {
        name = "papermc"
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:${property("paper_version")}")
    implementation(kotlin("stdlib"))
}

kotlin {
    jvmToolchain(25)
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(25))
}


tasks {
    val shadowJar = named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
        archiveClassifier.set("")
    }
    build {
        dependsOn(shadowJar)
    }
    runServer {
        minecraftVersion(mcVersion)
    }
}