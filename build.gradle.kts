plugins {
    kotlin("jvm") version "1.3.61"
    application
}

group = "kr.heartpattern"
version = "1.0.0-SNAPSHOT"

repositories {
    maven("https://maven.heartpattern.io/repository/maven-public/")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx","kotlinx-coroutines-core","1.3.3")
    implementation("kr.heartpattern", "spigotversions", "1.0-SNAPSHOT")
    implementation("org.eclipse.jgit", "org.eclipse.jgit", "5.6.0.201912101111-r")
    implementation("org.apache.maven", "maven-core", "3.6.3")
    implementation("com.github.ajalt", "clikt", "2.1.0")
}

application {
    mainClassName = "kr.heartpattern.spigotinstaller.AppKt"
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
