<script setup lang="ts">
import {VPTeamMembers} from 'vitepress/theme';

const coreMembers = [
    {
        avatar: avatar('JorelAli'),
        name: 'JorelAli',
        title: 'Creator',
        links: [
            githubSocialLink('JorelAli')
        ]
    }, {
        avatar: avatar('willkroboth'),
        name: 'Will Kroboth',
        title: '✨ Special Contributor ✨',
        links: [
            githubSocialLink('willkroboth')
        ]
    }, {
        avatar: avatar('DerEchtePilz'),
        name: 'DerEchtePilz',
        title: '✨ Special Contributor ✨',
        links: [
            githubSocialLink('DerEchtePilz')
        ]
    }
]

const otherContributorsList = [
    "Abelkrijgtalles",
    "agonkolgeci",
    "AkaGiant",
    "booky10",
    "CJCrafter",
    "dkim19375",
    "EnragedRabisu",
    "Euphillya",
    "Gregzeee",
    "Kadeluxe",
    "MatrixTunnel",
    "MC-XiaoHei",
    "misode",
    "NextdoorPsycho",
    "Osiris-Team",
    "powercasgamer",
    "RedstoneWizard08",
    "SB2DD",
    "Strokkur424",
    "Sytm",
    "Timongcraft",
    "Xemii16",
]

const earlyContributorsList = [
    "469512345",
    "Combustible",
    "Draycia",
    "HielkeMinecraft",
    "Michael-Ziluck",
    "Minenash",
    "portlek",
]

const otherContributors = otherContributorsList
    .sort((a, b) => a.localeCompare(b, undefined, {sensitivity: 'base'})) // Case-insensitive sort
    .map(name => buildContributor(name));

const earlyContributors = earlyContributorsList
    .sort((a, b) => a.localeCompare(b, undefined, {sensitivity: 'base'})) // Case-insensitive sort
    .map(name => buildContributor(name, "Early Contributor"));

function avatar(name: string) {
    return `https://wsrv.nl/?url=https://www.github.com/${name}.png`
}

function githubSocialLink(name: string) {
    return {icon: 'github', link: `https://github.com/${name}`}
}

function buildContributor(name: string, title: string = "Contributor") {
    return {
        avatar: avatar(name),
        name: name,
        title: title,
        links: [
            githubSocialLink(name)
        ]
    }
}

</script>

<template>
    <div>
        <br/>
        <h1>Brought to you by</h1>
        <VPTeamMembers size="medium" :members="coreMembers"/>
        <h3>Early Contributors</h3>
        <VPTeamMembers size="small" :members="earlyContributors"/>
        <h3>Other Contributors</h3>
        <VPTeamMembers size="small" :members="otherContributors"/>
    </div>
</template>

<style scoped>

</style>