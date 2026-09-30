plugins {
    kotlin("jvm") version "2.4.20"
    id("xyz.jpenilla.run-paper") version "3.1.0"
    java
    id("com.gradleup.shadow") version "8.3.6"
}

group="org.example"
version="1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://repo.papermc.io/repository/maven-public/") {
        name = "papermc"
    }
    
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:26.3.build.+")
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
        archiveClassifier.set("") // Removes "-all" or "-shadow" from the output jar name
    }
    
    // Make the standard build task depend on shadowJar so it builds properly out of the box
    build {
        dependsOn(shadowJar)
    }
    
    runServer {
        minecraftVersion("26.2")
    }
}