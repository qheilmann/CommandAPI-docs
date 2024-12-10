---
order: 1
authors:
  - JorelAli
  - willkroboth
  - DerEchtePilz
---

# Predicate tips

In our [example for creating a party system](../create-commands/requirements#example-a-party-creation-and-teleportation-system), we ended up having lots of code repetition. In our party creation command, we had the following code:

:::tabs
===Java
<<< @/../reference-code/src/main/java/tips/PredicateTips.java#exampleStep1
===Kotlin
<<< @/../reference-code/src/main/kotlin/tips/PredicateTips.kt#exampleStep1
:::

And for our party teleportation command, we had the following code:

:::tabs
===Java
<<< @/../reference-code/src/main/java/tips/PredicateTips.java#exampleStep2
===Kotlin
<<< @/../reference-code/src/main/kotlin/tips/PredicateTips.kt#exampleStep2
:::


We can simplify this code by declaring the predicate:

:::tabs
===Java
<<< @/../reference-code/src/main/java/tips/PredicateTips.java#exampleStep3
===Kotlin
<<< @/../reference-code/src/main/kotlin/tips/PredicateTips.kt#exampleStep3
:::


Now, we can use the predicate `testIfPlayerHasParty` in our code for creating a party. Since we want to apply the "not" (`!`) operator to this predicate, we can use `.negate()` to invert the result of our predicate:

:::tabs
===Java
<<< @/../reference-code/src/main/java/tips/PredicateTips.java#exampleStep4
===Kotlin
<<< @/../reference-code/src/main/kotlin/tips/PredicateTips.kt#exampleStep4
:::


And we can use it again for our code for teleporting to party members:

:::tabs
===Java
<<< @/../reference-code/src/main/java/tips/PredicateTips.java#exampleStep5
===Kotlin
<<< @/../reference-code/src/main/kotlin/tips/PredicateTips.kt#exampleStep5
:::
