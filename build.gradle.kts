plugins {
    id("jacoco")
    id("org.jetbrains.kotlin.jvm") version "2.2.0"
}


group = "ru.netology"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

//dependencies {
//    testImplementation(kotlin("test"))
//}
dependencies {
    implementation ("org.jetbrains.kotlin:kotlin-test")
    testImplementation ("junit:junit:4.13.2")
}

//tasks.test {
//    useJUnitPlatform()
//}
//kotlin {
//    jvmToolchain(23)
//}