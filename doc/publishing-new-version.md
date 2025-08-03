# Publishing a new version of the Liquibase Gradle Plugin

To publish a new version of the plugin to
the [Gradle Plugin Portal](https://plugins.gradle.org/plugin/org.liquibase.gradle), run:

```bash
$ ./gradlew publishPlugins \
    -Pgradle.publish.key=<key> \
    -Pgradle.publish.secret=<secret>
```

For more information, see
the [official Gradle publishing guide](https://docs.gradle.org/current/userguide/publishing_gradle_plugins.html).
