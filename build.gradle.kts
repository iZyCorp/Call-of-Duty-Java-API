plugins {
    id("java-library");
    id("com.vanniktech.maven.publish") version "0.33.0"
    id("com.github.johnrengelman.shadow") version "8.1.1";
    id("signing")
}

group = "io.github.izycorp"
version = "1.0.4"
val isSnapshot = false
val archivesBaseName = "Call-of-Duty-Java-API-$version" + if (isSnapshot) "-SNAPSHOT" else ""

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

tasks.test {
    useJUnitPlatform()
}

mavenPublishing {
    coordinates("io.github.izycorp", "codapi", "1.0.4")
    publishToMavenCentral()
    signAllPublications()

    pom {
        name.set(rootProject.name)
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

signing {
    val keyId = findProperty("signing.keyId") as String
    val password = findProperty("signing.password") as String
    val keyFilePath = findProperty("signing.secretKeyRingFile") as String

    val key = file(keyFilePath).readText(Charsets.UTF_8).trim()

    useInMemoryPgpKeys(keyId, key, password)
    sign(publishing.publications)
}

repositories {
    mavenCentral()
}

dependencies {

    testImplementation(platform("org.junit:junit-bom:5.13.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.platform:junit-platform-suite-engine")
    testImplementation("io.github.cdimascio:dotenv-java:3.2.0")

    api("com.squareup.okhttp3:okhttp:4.12.0")
    api("org.json:json:20250517")
}

tasks.test {
    useJUnitPlatform()
}