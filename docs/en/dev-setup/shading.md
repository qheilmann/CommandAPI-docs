---
order: 2
preferences: ["build-system", "mapping"]
authors:
  - JorelAli
  - DerEchtePilz
  - willkroboth
  - Strokkur424
---

# Shading the CommandAPI in your plugins

"Shading" is the process of including the CommandAPI inside your plugin, rather than requiring the CommandAPI as an external plugin. In other words, if you shade the CommandAPI into your plugin, you don't need to include the `CommandAPI.jar` in your server's plugins folder.

## Shading vs CommandAPI plugin

The CommandAPI plugin has a few slight differences with the shaded CommandAPI jar file. The CommandAPI plugin has the following extra features that aren’t present in the shaded version:

- Command conversion via a `config.yml` file

## Shading requirements

For the CommandAPI to function as normal, you **must** call the CommandAPI's initializers in the `onLoad()` and `onEnable()` methods of your plugin:

```java
CommandAPI.onLoad(CommandAPIConfig config);
CommandAPI.onEnable();
```

If you want to handle reloading, the CommandAPI has minimal support for it with the `onDisable()` method, which can go in your plugin. This is optional and is not required if you don't plan on reloading the server.

### Loading

The `onLoad(CommandAPIConfig)` method initializes the CommandAPI's loading sequence. This must be called _before_ you start to access the CommandAPI and must be placed in your plugin's `onLoad()` method. The argument `CommandAPIConfig` is used to configure how the CommandAPI works. The `CommandAPIConfig` class has the following parameters which let you set how the CommandAPI works similar to the `config.yml`, which is described [here](../user-setup/config).

```java
public class CommandAPIConfig {
    CommandAPIConfig verboseOutput(boolean value); // Enables verbose logging
    CommandAPIConfig silentLogs(boolean value);    // Disables ALL logging (except errors)
    CommandAPIConfig useLatestNMSVersion(boolean value); // Whether the latest NMS implementation should be used or not
    CommandAPIConfig beLenientForMinorVersions(boolean value); // Whether the CommandAPI should be more lenient with minor Minecraft versions
    CommandAPIConfig missingExecutorImplementationMessage(String value); // Set message to display when executor implementation is missing
    CommandAPIConfig dispatcherFile(File file); // If not null, the CommandAPI will create a JSON file with Brigadier's command tree
    CommandAPIConfig setNamespace(String namespace); // The namespace to use when the CommandAPI registers a command
    CommandAPIConfig usePluginNamespace(); // Whether the CommandAPI should use the name of the plugin passed into the CommandAPIConfig implementation as the default namespace for commands

    <T> CommandAPIConfig initializeNBTAPI(Class<T> nbtContainerClass, Function<Object, T> nbtContainerConstructor); // Initializes hooks with an NBT API. See NBT arguments documentation page for more info
}
```

The `CommandAPIConfig` class follows a typical builder pattern (without you having to run `.build()` at the end), which lets you easily construct configuration instances.

However, the `CommandAPIConfig` class is abstract and can’t be used to configure the CommandAPI directly. Instead, you must use a subclass of `CommandAPIConfig` that corresponds to the platform you’re developing for. For example, when developing for a Bukkit-based server, you should use the `CommandAPIPaperConfig` or the `CommandAPISpigotConfig` class.

<!-- TODO: Add tabs and explanations for other platforms -->

:::tabs
===Bukkit
```java
public abstract class CommandAPIBukkitConfig extends CommandAPIConfig {
    CommandAPIBukkitConfig(JavaPlugin plugin);

    CommandAPIBukkitConfig skipReloadDatapacks(boolean skip); // Whether the CommandAPI should reload datapacks on server load
}
```
===Paper
```java
public class CommandAPIPaperConfig extends CommandAPIBukkitConfig {
    CommandAPIPaperConfig(JavaPlugin plugin);
    
    CommandAPIPaperConfig shouldHookPaperReload(boolean hooked); // Whether the CommandAPI should hook into the Paper-exclusive ServerResourcesReloadedEvent
}
```
===Spigot
```java
public class CommandAPISpigotConfig extends CommandAPIBukkitConfig {
    CommandAPISpigotConfig(JavaPlugin plugin);
}
```
:::

In order to create a `CommandAPIPaperConfig` or a `CommandAPISpigotConfig` object, you must give it a reference to your `JavaPlugin` instance. The CommandAPI always uses this to register events, so it is required when loading the CommandAPI on Bukkit. There are also platform-specific features, such as the `hook-paper-reload` configuration option on Paper, which may be configured using a `CommandAPIPaperConfig` instance.

For example, to load the CommandAPI on Bukkit with all logging disabled, you can use the following:

:::tabs
===Java
<<< @/../reference-code/src/main/java/devsetup/Shading.java#bukkitConfigExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/devsetup/Shading.kt#bukkitConfigExample
:::

### Enabling & Disabling

The `onEnable()` method initializes the CommandAPI's enabling sequence. Similar to the `onLoad(CommandAPIConfig)` method, this must be placed in your plugin's `onEnable()` method. This isn't as strict as the `onLoad(CommandAPIConfig)` method, and can be placed anywhere in your `onEnable()` method.

The `onDisable()` method disables the CommandAPI gracefully. This should be placed in your plugin's `onDisable()` method. This doesn't unregister commands, so commands may persist during reloads – this can be mitigated using the `CommandAPI.unregister()` method.

:::tip Example – Setting up the CommandAPI in your plugin

:::tabs
===Java
<<< @/../reference-code/src/main/java/devsetup/Shading.java#shadingExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/devsetup/Shading.kt#shadingExample
:::

## A note about relocating

By default, the CommandAPI is written in the `dev.jorel.commandapi` package. It is **highly recommended** to "relocate" the shaded copy of the CommandAPI to your own package instead to prevent package clashes with other projects that shade the CommandAPI:


$$
\text{dev.jorel.commandapi} \xrightarrow{\text{relocate}} \text{my.custom.package.commandapi}
$$


## Shading with Build System

<div class="maven">

To shade the CommandAPI into a maven project, you'll need to use the `commandapi-bukkit-shade` dependency, which is optimized for shading and doesn't include plugin-specific files _(such as `plugin.yml`)_. Here you have a choice between the Spigot-mapped version and the Mojang-mapped version. **You do not need to use `commandapi-bukkit-core` if you are shading**:

Add the CommandAPI shade dependency:

<div class="reobf">

:::tabs
===Paper
```xml
<dependencies>
    <dependency>
        <groupId>dev.jorel</groupId>
        <artifactId>commandapi-paper-shade</artifactId>
        <version>9.7.0</version>
    </dependency>
</dependencies>
```
===Spigot
```xml
<dependencies>
    <dependency>
        <groupId>dev.jorel</groupId>
        <artifactId>commandapi-spigot-shade</artifactId>
        <version>9.7.0</version>
    </dependency>
</dependencies>
```
:::

</div>
<div class="mojmap">

:::tabs
===Paper
```xml
<dependencies>
    <dependency>
        <groupId>dev.jorel</groupId>
        <artifactId>commandapi-paper-shade-mojang-mapped</artifactId>
        <version>9.7.0</version>
    </dependency>
</dependencies>
```
===Spigot
```xml
<dependencies>
    <dependency>
        <groupId>dev.jorel</groupId>
        <artifactId>commandapi-spigot-shade-mojang-mapped</artifactId>
        <version>9.7.0</version>
    </dependency>
</dependencies>
```
:::

</div>

You can shade the CommandAPI easily by adding the `maven-shade-plugin` to your build sequence:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-shade-plugin</artifactId>
            <version>3.6.0</version>
            <executions>
                <execution>
                    <id>shade</id>
                    <phase>package</phase>
                    <goals>
                        <goal>shade</goal>
                    </goals>
                </execution>
            </executions>
            <configuration>
                <minimizeJar>true</minimizeJar>
                <relocations>
                    <relocation>
                        <pattern>dev.jorel.commandapi</pattern>
                        <!-- TODO: Change this to my own package name -->
                        <shadedPattern>my.custom.package.commandapi</shadedPattern>
                    </relocation>
                </relocations>
            </configuration>
        </plugin>
    </plugins>
</build>
```

</div>
<div class="gradle">

To shade the CommandAPI into a Gradle project, we'll use the [GradleUp Shadow Plugin](https://gradleup.com/shadow/). Add this to your list of plugins:

<div class="groovy">

```groovy
plugins {
    id 'java'
    id 'com.gradleup.shadow' version '8.3.3'
}
```
</div>
<div class="kts">

```kotlin
plugins {
    java
    id("com.gradleup.shadow") version "8.3.3"
}
```
</div>

Add our repositories:

<div class="groovy">

```groovy
repositories {
    mavenCentral()

    // If you want to shade the NBT API as well
    maven { url = "https://repo.codemc.org/repository/maven-public/" }
}
```
</div>
<div class="kts">

```kotlin
repositories {
    mavenCentral()

    // If you want to shade the NBT API as well
    maven(url = "https://repo.codemc.org/repository/maven-public/")
}
```

</div>

Next, we declare our dependencies:

<div class="groovy">
<div class="reobf">

:::tabs
===Paper
```groovy
dependencies {
    implementation "dev.jorel:commandapi-paper-shade:9.7.0"
}
```
===Spigot
```groovy
dependencies {
    implementation "dev.jorel:commandapi-spigot-shade:9.7.0"
}
```
:::

</div>
<div class="mojmap">

:::tabs
===Paper
```groovy
dependencies {
    implementation "dev.jorel:commandapi-paper-shade-mojang-mapped:9.7.0"
}
```
===Spigot
```groovy
dependencies {
    implementation "dev.jorel:commandapi-spigot-shade-mojang-mapped:9.7.0"
}
```
:::

</div>
</div>
<div class="kts">
<div class="reobf">

:::tabs
===Paper
```kotlin
dependencies {
    implementation("dev.jorel:commandapi-paper-shade:9.7.0")
}
```
===Spigot
```kotlin
dependencies {
    implementation("dev.jorel:commandapi-spigot-shade:9.7.0")
}
```
:::

</div>
<div class="mojmap">

:::tabs
===Paper
```kotlin
dependencies {
    implementation("dev.jorel:commandapi-paper-shade-mojang-mapped:9.7.0")
}
```
===Spigot
```kotlin
dependencies {
    implementation("dev.jorel:commandapi-spigot-shade-mojang-mapped:9.7.0")
}
```
:::

</div>
</div>

Then you just need to relocate the CommandAPI to your desired location in the `shadowJar` task configuration:

<div class="groovy">

```groovy
shadowJar {
    // TODO: Change this to my own package name
    relocate("dev.jorel.commandapi", "my.custom.package.commandapi")
}
```
</div>
<div class="kts">

```kotlin
tasks.withType<ShadowJar> {
    // TODO: Change this to my own package name
    relocate("dev.jorel.commandapi", "my.custom.package.commandapi")
}
```
</div>

Finally, we can build the shaded jar using the following command:

```bash
gradlew build shadowJar
```

</div>

As we're shading the CommandAPI into your plugin, we **don't** need to add `depend: [CommandAPI]` to your `plugin.yml` file.
