plugins {
    kotlin("jvm") version "2.4.20"
    id("xyz.jpenilla.run-paper") version "3.1.0"
    java
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
}

kotlin {
    jvmToolchain(25)
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(25))
}


tasks {
    runServer {
        minecraftVersion("26.2")
    }
}