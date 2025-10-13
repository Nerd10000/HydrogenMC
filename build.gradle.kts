plugins {
    id("java")
    id("com.gradleup.shadow") version "9.0.0-beta9"
}

group = "dragon.me"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    implementation("com.typesafe:config:1.4.2")
    implementation("net.minestom:minestom:2025.09.13-1.21.8")
    implementation("org.slf4j:slf4j-api:2.0.7")
    implementation("ch.qos.logback:logback-classic:1.4.11")
    // https://mvnrepository.com/artifact/org.fusesource.jansi/jansi
    implementation("org.fusesource.jansi:jansi:2.4.2")
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.github.oshi:oshi-core:6.9.0")

}

tasks.test {
    useJUnitPlatform()
}
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21)) // or 24 if you use JDK 24
    }
}
tasks.register<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("fatJar") {
    archiveClassifier.set("all")
    mergeServiceFiles()
}

tasks.jar {
    manifest {
        attributes(
            "Main-Class" to "dragon.me.Main" // <-- Replace with your actual main class
        )
    }
}
