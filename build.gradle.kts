

plugins {
    kotlin("jvm") version "1.9.23"
    application
    java
    id("org.openjfx.javafxplugin") version "0.0.13"
//    id("com.gluonhq.gluonfx-gradle-plugin") version "1.0.22"
    id("edu.sc.seis.launch4j") version "3.0.5"
}

group = "me.fortibrine"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
}

tasks {

    application {
        mainClass = "me.fortibrine.triangulate.MainKt"
    }

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

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "16"
        }
    }

    launch4j {
        outfile = "triangulation.exe"
        mainClassName = "me.fortibrine.triangulate.MainKt"
        setJarTask(project.tasks.getByName("jar"))
    }

    jar {
        manifest {
            attributes["Main-Class"] = "me.fortibrine.triangulate.MainKt"
        }
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        from (
            configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
        )

    }

}
