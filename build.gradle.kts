plugins {
    kotlin("jvm") version "1.9.23"
    id("jacoco")
}

group = "com.rohan.minesweeper"
version = "1.0-SNAPSHOT"

tasks.register<JavaExec>("run") {
    mainClass.set("com.rohan.minesweeper.MainKt")
    classpath = sourceSets["main"].runtimeClasspath
    standardInput = System.`in`  // Ensure standard input is connected
}

jacoco {
    toolVersion = "0.8.8"
}


java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<JavaCompile> {
    options.release.set(17)
}

tasks.processResources {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    testImplementation("org.mockito:mockito-core:5.3.1")
    testImplementation("org.mockito:mockito-inline:5.2.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.2.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.1.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    testImplementation("io.mockk:mockk:1.13.5")
}
sourceSets {
    main {
        resources {
            srcDirs("src/main/resources")
        }
    }
}


tasks.test {
    useJUnitPlatform()
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

kotlin {
    jvmToolchain(17)
}
