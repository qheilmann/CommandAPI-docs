---
order: 1
preferences: ["build-system"]
authors:
  - JorelAli
  - willkroboth
  - DerEchtePilz
---

# Kotlin-based commands

The CommandAPI also provides an alternative way of making commands when using Kotlin to develop your plugins: A DSL!

This DSL provides many methods to easily add arguments to your command structure. Examples of the DSL can be found [here](./usage.md).

## Installing the DSL

To install the DSL, you need to add the `commandapi-bukkit-kotlin` dependency into your `pom.xml` or your `build.gradle`, making sure to specify the server flavor you are developing for:

### Adding the dependency

<div class="maven">

```xml
<dependencies>
    <dependency>
        <groupId>dev.jorel</groupId>
        <artifactId>commandapi-bukkit-kotlin</artifactId>
        <version>9.7.1-SNAPSHOT</version>
    </dependency>
</dependencies>
```

Next, you need to add Kotlin to your project. For this, you first need to add the dependency:

```xml
<dependencies>
    <dependency>
        <groupId>org.jetbrains.kotlin</groupId>
        <artifactId>kotlin-stdlib</artifactId>
        <version>1.9.0</version>
    </dependency>
</dependencies>
```

Finally, you need to add the `kotlin-maven-plugin`:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.jetbrains.kotlin</groupId>
            <artifactId>kotlin-maven-plugin</artifactId>
            <version>1.9.0</version>
            <executions>
                <execution>
                    <id>compile</id>
                    <phase>compile</phase>
                    <goals>
                        <goal>compile</goal>
                    </goals>
                </execution>
                <execution>
                    <id>test-compile</id>
                    <phase>test-compile</phase>
                    <goals>
                        <goal>test-compile</goal>
                    </goals>
                </execution>
            </executions>
            <configuration>
                <jvmTarget>16</jvmTarget>
            </configuration>
        </plugin>
    </plugins>
</build>
```

</div>
<div class="gradle">

First, you need to add the repository:

<div class="groovy">

```groovy
repositories {
    mavenCentral()
}
```

</div>
<div class="kts">

```kotlin
repositories {
    mavenCentral()
}
```

</div>

Next, you need to add the dependency:

<div class="groovy">

```groovy
dependencies {
    implementation "dev.jorel:commandapi-bukkit-kotlin:9.7.1-SNAPSHOT"
}
```

</div>
<div class="kts">

```kotlin
dependencies {
    implementation("dev.jorel:commandapi-bukkit-kotlin:9.7.1-SNAPSHOT")
}
```

</div>

You also need to add Kotlin to your project. For this, you first need to add the Kotlin plugin:

<div class="groovy">

```groovy
plugins {
    id "org.jetbrains.kotlin.jvm" version "1.9.0"
}
```

</div>
<div class="kts">

```kotlin
plugins {
    kotlin("jvm") version "1.9.0"
}
```

</div>

Next, you need to add the dependency (you should already have added the `mavenCentral()` repository to your project):

<div class="groovy">

```groovy
dependencies {
    implementation "org.jetbrains.kotlin:kotlin-stdlib"
}
```

</div>
<div class="kts">

```kotlin
dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
}
```

</div>

Then, you need to configure the Java version to build against:

<div class="groovy">

```groovy
kotlin {
    jvmToolchain(16)
}
```

</div>
<div class="kts">

```kotlin
kotlin {
    jvmToolchain(16)
}
```

</div>

</div>