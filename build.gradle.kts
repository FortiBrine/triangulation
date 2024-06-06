

plugins {
    kotlin("jvm") version "1.9.23"
    application
    java
    id("org.openjfx.javafxplugin") version "0.0.13"
}

group = "me.fortibrine"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.openpnp:opencv:4.6.0-0")
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
    implementation("de.erichseifert.vectorgraphics2d:VectorGraphics2D:0.13")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
}

tasks {
    withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        sourceCompatibility = "16"
        targetCompatibility = "16"
    }

    javafx {
        version = "16"
        modules = listOf(
            "javafx.controls",
            "javafx.fxml"
        )
    }


    application {
        mainClass = "me.fortibrine.triangulate.MainKt"
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "16"
        }
    }

}
