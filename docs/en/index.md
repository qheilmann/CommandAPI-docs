---
# https://vitepress.dev/reference/default-theme-home-page
layout: home

hero:
    name: "CommandAPI"
    tagline: An API for the command UI introduced in Minecraft 1.13
    actions:
        -   theme: brand
            text: Documentation
            link: /intro
        -   theme: alt
            text: Download
            link: https://github.com/CommandAPI/CommandAPI/releases/latest

features:
    -   title: Safer commands with better arguments
        details: Prevent players running invalid commands by picking from over 50 specialized arguments with built-in error checking, suggestions, tooltips, and permission checks.
    -   title: Supports vanilla datapacks
        details: Let your command to be executed by the built-in <code>/execute</code> command, command blocks, and even datapack functions and tags.
    -   title: Write code how <i>you</i> want to
        details: Want a Brigadier tree-like structure? Want to use Kotlin? Want an annotation-based command system? We've got you covered.
---

<!--suppress HtmlUnknownAttribute, ES6UnusedImports -->
<script setup>
import ProjectTeam from '../.vitepress/theme/author/ProjectTeam.vue';
</script>

<ProjectTeam></ProjectTeam>