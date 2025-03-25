---
order: 3
preferences: ["build-system"]
authors:
  - DerEchtePilz
  - willkroboth
  - JorelAli
---

# Using the annotation system

The annotation system is a separate part of the CommandAPI, and as a result it needs to be included as an additional dependency to your project.

The annotation system effectively needs to be added twice: Once for compilation and again to invoke the annotation processor itself.

<div class="maven">

- Add the annotation dependency to your `pom.xml`:

  ```xml
  <dependencies>
      <dependency>
          <groupId>dev.jorel</groupId>
          <artifactId>commandapi-annotations</artifactId>
          <version>10.0.0</version>
          <scope>provided</scope>
      </dependency>
  </dependencies>
  ```

- Add the annotation processor as an annotation process to the compile task in the `pom.xml`:

  ```xml
  <build>
      <plugins>
          <plugin>
              <groupId>org.apache.maven.plugins</groupId>
              <artifactId>maven-compiler-plugin</artifactId>
              <version>3.8.1</version>
              <configuration>
                  <annotationProcessorPaths>
                      <path>
                          <groupId>dev.jorel</groupId>
                          <artifactId>commandapi-annotations</artifactId>
                          <version>10.0.0</version>
                      </path>
                  </annotationProcessorPaths>
              </configuration>
        </plugin>
      </plugins>
  </build>
  ```

</div>
<div class="gradle">

- If you haven't already done so, add the maven central repository to your `build.gradle` file:

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
      maven(url = "https://repo.codemc.org/repository/maven-public/")
  }
  ```
  </div>

- Add the dependency and annotation processor to your list of dependencies in your `build.gradle` file:
  
  <div class="groovy">
  
  ```groovy
  dependencies {
      compileOnly "dev.jorel:commandapi-annotations:10.0.0"
      annotationProcessor "dev.jorel:commandapi-annotations:10.0.0"
  }
  ```
  </div>
  <div class="kts">
  
  ```kotlin
  dependencies {
      compileOnly("dev.jorel:commandapi-annotations:10.0.0")
      annotationProcessor("dev.jorel:commandapi-annotations:10.0.0")
  }
  ```
  </div>

</div>