---
order: 1
preferences: ["build-system"]
authors:
  - JorelAli
  - DerEchtePilz
  - willkroboth
---

# Setting up your development environment

To use the CommandAPI in your plugins, there are a few methods of adding it to your development environment. First things first, if you're using the CommandAPI plugin, you need to add the CommandAPI as a dependent in your `plugin.yml` or `paper-plugin.yml`:

:::tabs key:dev-setup
===plugin.yml (Bukkit/Spigot/Paper)
```yaml
name: MyPlugin
main: some.package.name.Main
version: 1.0
depend: [CommandAPI]
```
===paper-plugin.yml (Paper)
```yaml
name: MyPlugin
main: some.package.name.Main
version: 1.0
dependencies:
  server:
    CommandAPI:
      load: BEFORE
      required: true
      join-classpath: true
```

:::

## Using a build system (Recommended)

:::info **Developer's Note:**

If you've never used a build system before, I highly recommend it! It makes it easier to keep your code updated with the latest dependency updates. For information on how to set up a plugin using maven, you can read [Bukkit's plugin tutorial](https://bukkit.gamepedia.com/Plugin_Tutorial).

:::

<div class="maven">

- Add the dependency to your `pom.xml`:

  ```xml
  <dependencies>
      <dependency>
          <groupId>dev.jorel</groupId>
          <artifactId>commandapi-bukkit-core</artifactId>
          <version>9.7.1-SNAPSHOT</version>
          <scope>provided</scope>
      </dependency>
  </dependencies>
  ```

</div>
<div class="gradle">

- Add the repositories to your <span class="groovy">`build.gradle`</span><span class="kts">`build.gradle.kts`</span> (the second repository is required because the CommandAPI depends on the NBT-API):

  <div class="groovy">
  
  ```groovy
  repositories {
      mavenCentral()
      maven { url = "https://repo.codemc.org/repository/maven-public/" }
  }
  ```
  
  </div>
  <div class="kts">
  
  ```kotlin
  repositories {
      mavenCentral()
      maven("https://repo.codemc.org/repository/maven-public/")
  }
  ```

  </div>
  
  
- Add the dependency to your list of dependencies in your build script:

  <div class="groovy">

  ```groovy
  dependencies {
      compileOnly "dev.jorel:commandapi-bukkit-core:9.7.1-SNAPSHOT"
  }
  ```
  
  </div>
  <div class="kts">
  
  ```kotlin
  dependencies {
      compileOnly("dev.jorel:commandapi-bukkit-core:9.7.1-SNAPSHOT")
  }
  ```
  
  </div>

</div>

## Manually using the .jar

- Download the latest CommandAPI.jar from the download page [here](https://github.com/CommandAPI/CommandAPI/releases/latest)

- Add the CommandAPI.jar file to your project/environment's build path:

  - Adding the external .jar file in IntelliJ:

    - In your project, first click `File` -> `Project Structure` -> `Libraries`

    - Next, click the little `+` at the top:

      ![An image in IntelliJ showing the plus icon to add an external .jar.](/images/intellij.png)

    - After you clicked that, you need to select `Java`. A little pop-up will appear where you can choose the location of your external .jar file.

  - Adding the external .jar file in Eclipse:

    ![An image of some context menu entries in Eclipse after right clicking a project. Displays the highlighted options "Build Path", followed by "Add External Archives..."](/images/eclipse.jpg)
