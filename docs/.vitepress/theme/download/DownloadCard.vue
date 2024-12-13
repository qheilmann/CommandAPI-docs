<script setup lang="ts">
// noinspection JSUnusedGlobalSymbols
import {onMounted} from "vue";

const props = defineProps<{
    screenMenu?: boolean
}>();

const modrinthUrl = "https://modrinth.com/plugin/commandapi";
const spigotUrl = "https://www.spigotmc.org/resources/api-commandapi-1-16-5-1-21-4.62353/";
const hangarUrl = "https://hangar.papermc.io/Skepter/CommandAPI";

onMounted(() => {
    document.querySelectorAll(".download-link").forEach((element) => {
        const a = element as HTMLLinkElement;
        a.onclick = function (){
            window.open(a.href, "_blank");
            return false;
        }
    });
});

</script>

<template>
    <div class="container">
        <a class="hangar-link download-link" :href="hangarUrl"/>
        <a class="modrinth-link download-link" :href="modrinthUrl"/>
        <a class="spigot-link download-link" :href="spigotUrl"/>
    </div>
</template>

<style scoped>
.container {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    padding-left: 24px;
    padding-right: 24px;
    gap: 12px;
}

.spigot-link {
    content: url("/images/index/spigot.png");
}

.hangar-link {
    content: url("/images/index/hangar.svg");
}

.modrinth-link {
    content: url("/images/index/modrinth-light.svg");
}

.download-link {
    width: 24px;
}

.dark {
    .modrinth-link {
        content: url("/images/index/modrinth-dark.svg");
    }
}
</style>