---
order: 10
authors:
    - JorelAli
    - MC-XiaoHei
    - misode
    - DerEchtePilz
    - willkroboth
---

<!--suppress HtmlUnknownAttribute, ES6UnusedImports -->
<script setup>
import MCVersionSwitch from '../../../../../.vitepress/theme/components/MCVersionSwitch.vue';
</script>

# Particle arguments

![A particle argument suggesting a list of Minecraft particle effects](/images/arguments/particle.png)

The `ParticleArgument` class represents Minecraft particles. This is cast to the CommandAPI's `ParticleData` class.

## The `ParticleData` class

The `ParticleData` class is a record that contains two values:

- `Particle particle`, which is the Bukkit enum `Particle` representation of what particle was provided
- `T data`, which represents any additional particle data which was provided.

```java
public record ParticleData<T>(Particle particle, T data);
```

The `T data` can be used in Bukkit's `World.spawnParticle(Particle particle, Location location, int count, T data)` method.

## Particle data

:::info

Particle data depends on your version of Minecraft. In 1.20.5, Minecraft and Spigot updated their particle API, and they are no longer compatible with each other. Please choose which version you use below:

<MCVersionSwitch></MCVersionSwitch>

:::

The particle argument requires additional data for a particle depending on what the particle is. <span class="before-1204">Information about this can be found [on the Argument types page on the MinecraftWiki](https://minecraft.wiki/w/Argument_types#particle). </span>The following particles have additional data required to display them:

<div class="before-1204">

| Bukkit Particle         | Minecraft particle      | Arguments                                                                 |
|-------------------------|-------------------------|---------------------------------------------------------------------------|
| `BLOCK_CRACK`           | `block`                 | `block block_id`<br><br>`block block_id[block_state=value]`               |
| `BLOCK_MARKER`          | `block_marker`          | `block_marker block_id`<br><br>`block_marker block_id[block_state=value]` |
| `REDSTONE`              | `dust`                  | `dust red green blue size`                                                |
| `DUST_COLOR_TRANSITION` | `dust_color_transition` | `dust_color_transition red1 green1 blue1 size red2 green2 blue2`          |
| `FALLING_DUST`          | `falling_dust`          | `falling_dust block_id`<br><br>`falling_dust block_id[block_state=value]` |
| `ITEM_CRACK`            | `item`                  | `item item_id`<br><br>`item item_id{NBT}`                                 |
| `SCULK_CHARGE`          | `sculk_charge`          | `sculk_charge angle`                                                      |
| `SHRIEK`                | `shriek`                | `shriek delay`                                                            |
| `VIBRATION`             | `vibration`             | `vibration x y z ticks`                                                   |

</div>
<div class="after-1205">

<!-- To whoever has to maintain this block, I am sorry! - Skepter -->

<table>
    <thead>
        <tr>
            <th>Bukkit Particle</th>
            <th>Argument syntax</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td><code>BLOCK</code></td>
            <td>
                <pre>block{block_state:{Name:<b>"block_name"</b>}}</pre>
                <ul style="padding-left: 1.5em;">
                    <li><b><code>block_name</code></b> - name of a block, such as <code>diamond_block</code></li>
                </ul>
            </td>
        </tr>
        <tr>
            <td><code>BLOCK_MARKER</code></td>
            <td>
                <pre>block_marker{block_state:{Name:<b>"block_name"</b>}}</pre>
                <ul style="padding-left: 1.5em;">
                    <li><b><code>block_name</code></b> - name of a block, such as <code>diamond_block</code></li>
                </ul>
            </td>
        </tr>
        <tr>
            <td><code>DUST</code></td>
            <td>
                <pre>dust{color:[<b>red</b>,<b>green</b>,<b>blue</b>],scale:<b>scale</b>}</pre>
                <ul style="padding-left: 1.5em;">
                    <li><b><code>red</code></b> - number for red, between 0.0 and 1.0</li>
                    <li><b><code>green</code></b> - number for green, between 0.0 and 1.0</li>
                    <li><b><code>blue</code></b> - number for blue, between 0.0 and 1.0</li>
                    <li><b><code>scale</code></b> - number for the size of the particle</li>
                </ul>
            </td>
        </tr>
        <tr>
            <td><code>DUST_COLOR_TRANSITION</code></td>
            <td>
                <pre>dust_color_transition{from_color:[<b>red</b>,<b>green</b>,<b>blue</b>],<br>scale:<b>scale</b>,to_color:[<b>red</b>,<b>green</b>,<b>blue</b>]}</pre>
                <ul style="padding-left: 1.5em;">
                    <li><b><code>red</code></b> - number for red, between 0.0 and 1.0</li>
                    <li><b><code>green</code></b> - number for green, between 0.0 and 1.0</li>
                    <li><b><code>blue</code></b> - number for blue, between 0.0 and 1.0</li>
                    <li><b><code>scale</code></b> - number for the size of the particle</li>
                </ul>
            </td>
        </tr>
        <tr>
            <td><code>DUST_PILLAR</code></td>
            <td>
                <pre>dust_pillar{block_state:{Name:<b>"block_name"</b>}}</pre>
                <ul style="padding-left: 1.5em;">
                    <li><b><code>block_name</code></b> - name of a block, such as <code>diamond_block</code></li>
                </ul>
            </td>
        </tr>
        <tr>
            <td><code>ENTITY_EFFECT</code></td>
            <td>
                <pre>entity_effect{color:[<b>red</b>,<b>green</b>,<b>blue</b>,<b>alpha</b>]}</pre>
                <ul style="padding-left: 1.5em;">
                    <li><b><code>red</code></b> - number for red, between 0.0 and 1.0</li>
                    <li><b><code>green</code></b> - number for green, between 0.0 and 1.0</li>
                    <li><b><code>blue</code></b> - number for blue, between 0.0 and 1.0</li>
                    <li><b><code>alpha</code></b> - number for transparency, between 0.0 and 1.0</li>
                </ul>
            </td>
        </tr>
        <tr>
            <td><code>FALLING_DUST</code></td>
            <td>
                <pre>falling_dust{block_state:{Name:<b>"block_name"</b>}}</pre>
                <ul style="padding-left: 1.5em;">
                    <li><b><code>block_name</code></b> - name of a block, such as <code>diamond_block</code></li>
                </ul>
            </td>
        </tr>
        <tr>
            <td><code>ITEM</code></td>
            <td>
                <pre>item{item:"<b>item</b>"}</pre>
                <ul style="padding-left: 1.5em;">
                    <li><b><code>item</code></b> - name of an item, such as <code>apple</code></li>
                </ul>
            </td>
        </tr>
        <tr>
            <td><code>SCULK_CHARGE</code></td>
            <td>
                <pre>sculk_charge{roll:<b>angle</b>}</pre>
                <ul style="padding-left: 1.5em;">
                    <li><b><code>angle</code></b> - decimal angle the particle displays at in radians</li>
                </ul>
            </td>
        </tr>
        <tr>
            <td><code>SHRIEK</code></td>
            <td>
                <pre>shriek{delay:<b>delay</b>}</pre>
                <ul style="padding-left: 1.5em;">
                    <li><b><code>delay</code></b> - delay in ticks for when the shriek particle should appear</li>
                </ul>
            </td>
        </tr>
        <tr>
            <td><code>TRAIL</code></td>
            <td>
                <pre>trail{color:[<b>red</b>,<b>green</b>,<b>blue</b>],target:[<b>x</b>,<b>y</b>,<b>z</b>],duration:<b>duration</b>}</pre>
                <ul style="padding-left: 1.5em;">
                    <li><b><code>red</code></b> - number for red, between 0.0 and 1.0</li>
                    <li><b><code>green</code></b> - number for green, between 0.0 and 1.0</li>
                    <li><b><code>blue</code></b> - number for blue, between 0.0 and 1.0</li>
                    <li><b><code>x</code></b> - decimal x-coordinate to move the particle to</li>
                    <li><b><code>y</code></b> - decimal y-coordinate to move the particle to</li>
                    <li><b><code>z</code></b> - decimal z-coordinate to move the particle to</li>
                    <li><b><code>duration</code></b> - ime in ticks to take to move towards its destination</li>
                </ul>
            </td>
        </tr>
        <tr>
            <td><code>VIBRATION</code></td>
            <td>
                <pre>vibration{destination:{type:"block",pos:[<b>x</b>,<b>y</b>,<b>z</b>]},<br>arrival_in_ticks:<b>ticks</b>}</pre>
                <ul style="padding-left: 1.5em;">
                    <li><b><code>x</code></b> - decimal x-coordinate to move towards</li>
                    <li><b><code>y</code></b> - decimal y-coordinate to move towards</li>
                    <li><b><code>z</code></b> - decimal z-coordinate to move towards</li>
                    <li><b><code>ticks</code></b> - time in ticks to take to move towards its destination</li>
                </ul>
            </td>
        </tr>
    </tbody>
</table>

</div>


## ParticleArgument examples

Because certain particles (in the table above) require additional data, it is not recommended to spawn a particle without its corresponding data. This can result in particles not showing due to missing requirements.

::::danger Example - Show particles at a player's location (without data)

Say we wanted to have a command that displayed particles at a player's location. We will use the following command syntax:

```mccmd
/showparticle <particle>
```

With this, we can simply spawn the particle using the `World.spawnParticle(Particle, Location, int)` method:

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/misc/ParticleArguments.java#withoutParticleDataExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/misc/ParticleArguments.kt#withoutParticleDataExample
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/misc/ParticleArguments.kt#withoutParticleDataExampleDSL
:::

Running this can result in errors due to missing requirements. If you provide a particle that has additional requirements, Bukkit will throw an error and the particle will not be displayed. Instead, the example below should be used.

::::

::::tip Example - Show particles  at a player's location (with data)

We can fix the issues with the example above by providing the data of the argument using the `ParticleData` record:

```mccmd
/showparticle <particle>
```

In this case, we'll use the `World.spawnParticle(Particle particle, Location location, int count, T data)` method which accepts some particle data:

:::tabs
===Java
<<< @/../reference-code/src/main/java/createcommands/arguments/types/misc/ParticleArguments.java#withParticleDataExample
===Kotlin
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/misc/ParticleArguments.kt#withParticleDataExample
===Kotlin DSL
<<< @/../reference-code/src/main/kotlin/createcommands/arguments/types/misc/ParticleArguments.kt#withParticleDataExampleDSL
:::

This can be used with commands such as:

<div class="before-1204">

```mccmd
/showparticle minecraft:dust_color_transition 0 0 0 20 1 0 0
/showparticle minecraft:block_marker diamond_block
```

</div>
<div class="after-1205">

```mccmd
/showparticle minecraft:dust_color_transition{from_color:[0.0,0.0,0.0],scale:20.0,to_color:[1.0,0.0,0.0]}
/showparticle minecraft:block_marker{block_state:{Name:"diamond_block"}}
```

</div>

::::

## Particle data implementation notes

The `vibration` particle will return a particle data of the Bukkit `Vibration` class. In the `Vibration` class, you can access the destination location using the `Vibration.getDestination()` method, which returns a `Vibration.Destination` instance. The CommandAPI will **always** return a `Vibration.Destination.BlockDestination` instance, and will never return a `Vibration.Destination.EntityDestination` instance. An example of accessing the location can be found below:

```java
ParticleData<Vibration> particleData; // The particle data you get from your argument
Location destination = ((BlockDestination) particleData.data().getDestination()).getLocation();
```