plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "moe.das"
version = "ALPHA"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://libraries.minecraft.net")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.18.2-R0.1-SNAPSHOT")
    compileOnly("org.jetbrains:annotations:24.0.1")
    implementation("me.lucko:commodore:2.2")
}

tasks {
    jar {
        dependsOn("shadowJar")
        enabled = false
    }

    compileJava {
        options.encoding = "UTF-8"
        options.compilerArgs.add("-parameters")
    }

    processResources {
        filesMatching("**/plugin.yml") {
            expand(rootProject.project.properties)
        }

        // Always re-run this task
        outputs.upToDateWhen { false }
    }

    shadowJar {
      archiveClassifier.set("")

      dependencies {
        exclude(dependency("com.mojang:brigadier"))
      }

      relocate("me.lucko.commodore", "moe.das.dasshop.dependencies.commodore")
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}
