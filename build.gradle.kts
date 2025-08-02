plugins {
    alias(libs.plugins.gradle.publish)
    groovy
    signing
}

repositories {
    mavenLocal()
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

val liquibaseGradlePluginVersion: String by project

group = "org.liquibase"
version = liquibaseGradlePluginVersion

val isPublishing = "publish" in gradle.startParameter.taskNames
val isReleaseVersion = !version.toString().endsWith("SNAPSHOT")

dependencies {
    compileOnly(localGroovy())
    compileOnly(gradleApi())
    compileOnly(libs.liquibase)

    testImplementation(libs.liquibase)
    testImplementation(libs.junit)
}

// Configure java-gradle-plugin plugin for publishing to Gradle Plugin Portal
gradlePlugin {
    website.set("https://github.com/liquibase/liquibase-gradle-plugin")
    vcsUrl.set("https://github.com/liquibase/liquibase-gradle-plugin.git")
    plugins {
        create("liquibasePlugin") {
            // Note that the ID must match its Gradle Plugin Portal id
            id = "org.liquibase.gradle"
            displayName = "Gradle Liquibase Plugin"
            description = "A Gradle plugin for running the Liquibase database upgrade tool."
            implementationClass = "org.liquibase.gradle.LiquibasePlugin"
            tags.set(listOf("liquibase", "database"))
        }
    }
}

// Disable signing tasks when not needed
tasks.withType<Sign>().configureEach {
    enabled = isReleaseVersion && isPublishing
}
