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
    "469512345",
    "Sytm",
    "MC-XiaoHei",
    "Timongcraft",
    "Strokkur424",
    "AkaGiant",
    "Osiris-Team",
    "Combustible",
    "SB2DD",
    "Euphillya",
    "CJCrafter",
    "Gregzeee",
    "powercasgamer",
    "agonkolgeci",
    "Xemii16",
    "Abelkrijgtalles",
    "dkim19375",
    "misode",
    "booky10",
    "NextdoorPsycho",
    "RedstoneWizard08",
    "Kadeluxe",
    "EnragedRabisu",
    "MatrixTunnel",
    "HielkeMinecraft",
    "Draycia",
    "Minenash",
    "Michael-Ziluck",
    "portlek"
]

const earlyContributors = [
    "Combustible",
    "Draycia",
    "HielkeMinecraft",
    "Minenash",
    "Michael-Ziluck",
    "portlek",
    "469512345"
]

const otherContributors = otherContributorsList.map(name => {
    if (earlyContributors.includes(name)) {
        return buildContributor(name, "Early Contributor")
    } else {
        return buildContributor(name)
    }
})

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
        <h3>Other Contributors</h3>
        <VPTeamMembers size="small" :members="otherContributors"/>
    </div>
</template>

<style scoped>

</style>