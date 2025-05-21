plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.apache.velocity:velocity-engine-core:2.3")
    implementation("org.slf4j:slf4j-simple:2.0.13")
}

tasks.test {
    useJUnitPlatform()
}