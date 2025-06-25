plugins {
    id("java-library");
    id("maven-publish");
    id("signing");
    id("com.github.johnrengelman.shadow") version "8.1.1";
}

group "io.github.izycorp"
version "1.0.4"
val isSnapshot = false
val archivesBaseName = "JCodApi-$version" + if (isSnapshot) "-SNAPSHOT" else ""

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.compileJava {
    options.encoding = "UTF-8"
    sourceCompatibility = "8"
    targetCompatibility = "8"
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    archiveBaseName.set(archivesBaseName)
    from(sourceSets.main.get().allSource)
}

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
    archiveBaseName.set(archivesBaseName)
    from(tasks.javadoc)
}

artifacts {
    add("archives", sourcesJar)
    add("archives", javadocJar)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifact(sourcesJar.get())
            artifact(javadocJar.get())

            pom {
                groupId = project.group.toString()
                artifactId = "codapi"
                version = project.version.toString()
                name.set(project.name)
                description.set("An unofficial wrapper of the official Call Of Duty API")
                url.set("https://github.com/iZyCorp/Call-of-Duty-Java-API")

                scm {
                    connection.set("scm:git:git://github.com/iZyCorp/Call-of-Duty-Java-API.git")
                    developerConnection.set("scm:git:ssh://github.com:iZyCorp/Call-of-Duty-Java-API.git")
                    url.set("https://github.com/iZyCorp/Call-of-Duty-Java-API/tree/master")
                }

                licenses {
                    license {
                        name.set("GNU General Public License v3.0")
                        url.set("https://www.gnu.org/licenses/gpl-3.0.txt")
                    }
                }

                developers {
                    developer {
                        id.set("izycorp")
                        name.set("iZyy_")
                    }
                }
            }
        }
    }

    repositories {
        maven {
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = findProperty("OSSRH_USERNAME") as String?
                password = findProperty("OSSRH_PASSWORD") as String?
            }
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
    sign(configurations["archives"])
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.13.1")
    testImplementation("io.github.cdimascio:dotenv-java:3.2.0")

    api("com.squareup.okhttp3:okhttp:4.12.0")
    api("org.json:json:20250517")
}

tasks.test {
    useJUnitPlatform()
}