---
# https://vitepress.dev/reference/default-theme-home-page
layout: home

hero:
    name: "CommandAPI"
    tagline: An API for the command UI introduced in Minecraft 1.13
    actions:
        -   theme: brand
            text: Introduction
            link: /intro
        -   theme: alt
            text: Download
            link: https://github.com/CommandAPI/CommandAPI/releases/latest

features:
    -   title: Better Commands & Arguments
        details: Prevent invalid commands and support over 50 arguments with built-in error checking, suggestions, tooltips, and precise permission.
    -   title: Just Like Vanilla Commands
        details: Let your command to be executed by the built in <code>/execute</code> command, commandblocks, functions and tags.
    -   title: Easy to Register
        details: No need to edit <code>plugin.yml</code>, supports Kotlin DSL, and Brigadier-like <code>CommandTree</code>. Also provide detailed documentation.
---

<script setup>

import ProjectTeam from '../.vitepress/theme/author/ProjectTeam.vue';

</script>

<ProjectTeam></ProjectTeam>