<script setup lang="ts">

import {onMounted, onUnmounted, ref, watch} from "vue";
import {getChanges, getVersionsAfter, getVersionsBefore, keyVersions, nowChanges, nowUpgradeInfo} from "./upgrade";

const fromOptions = ref([]);
const toOptions = ref([]);
const nowFrom = ref(nowUpgradeInfo.value.from);
const nowTo = ref(nowUpgradeInfo.value.to);
const isFromDropdownOpen = ref(false);
const isToDropdownOpen = ref(false);

onMounted(() => {
    reload();
    window.addEventListener('hashchange', reload);
});

onUnmounted(() => {
    window.removeEventListener('hashchange', reload);
});

watch(nowFrom, () => {
    update();
});

watch(nowTo, () => {
    update();
});

function openFromDropdown() {
    isFromDropdownOpen.value = true;
}

function closeFromDropdown() {
    isFromDropdownOpen.value = false;
}

function selectFrom(version: string) {
    nowFrom.value = version;
}

function openToDropdown() {
    isToDropdownOpen.value = true;
}

function closeToDropdown() {
    isToDropdownOpen.value = false;
}

function selectTo(version: string) {
    nowTo.value = version;
}

function update() {
    nowUpgradeInfo.value = {
        from: nowFrom.value,
        to: nowTo.value
    };
    window.location.hash = `#${nowUpgradeInfo.value.from}-${nowUpgradeInfo.value.to}`;
    fromOptions.value = getVersionsBefore(nowUpgradeInfo.value.to);
    toOptions.value = getVersionsAfter(nowUpgradeInfo.value.from);
    nowChanges.value = getChanges(nowUpgradeInfo.value);
    const changes = nowChanges.value.map(change => `${change.from}-to-${change.to}`);
    document.querySelectorAll('.upgrade-parts-container div').forEach(ele => {
        if (changes.includes(ele.id)) {
            ele.classList.add('show');
        } else {
            ele.classList.remove('show');
        }
    });
}

function reload() {
    const anchor = window.location.hash;
    const [from, to] = anchor.slice(1).split('-');
    if (keyVersions.slice(0, -1).includes(from) && getVersionsAfter(from).includes(to)) {
        nowFrom.value = from;
        nowTo.value = to;
    }
    update()
}
</script>

<template>
    <div class="card">
        I'm upgrading from
        <div class="dropdown" @mouseover="openFromDropdown" @mouseleave="closeFromDropdown">
            <button class="dropdown-button">{{ nowFrom }}</button>
            <div class="dropdown-content" :class="{ 'show-content': isFromDropdownOpen }">
                <a v-for="ver in fromOptions" @click="selectFrom(ver)">{{ ver }}</a>
            </div>
        </div>
        to
        <div class="dropdown" @mouseover="openToDropdown" @mouseleave="closeToDropdown">
            <button class="dropdown-button">{{ nowTo }}</button>
            <div class="dropdown-content" :class="{ 'show-content': isToDropdownOpen }">
                <a v-for="ver in toOptions" @click="selectTo(ver)">{{ ver }}</a>
            </div>
        </div>
    </div>
</template>

<style scoped>
.card {
    font-family: 'Helvetica Neue', sans-serif;
    width: fit-content;
    padding: 16px;
    margin-top: 20px;
    margin-bottom: 8px;
    border-radius: 4px;
    background-color: var(--vt-c-bg-soft);
}

.dropdown {
    width: fit-content;
    position: relative;
    display: inline-block;
}

.dropdown-button {
    border: 1px solid var(--vt-c-divider-light);
    border-radius: 4px;
    background-color: var(--vt-c-bg);
    color: var(--vt-c-text-1);
    padding: 10px;
    font-size: 16px;
    cursor: pointer;
}

.dropdown-content {
    opacity: 0;
    overflow: hidden;
    display: block;
    position: absolute;
    background-color: var(--vp-c-bg-elv);
    border-radius: 4px;
    border: 1px solid var(--vp-c-divider);
    box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
    z-index: 1;
    transition: opacity 0.25s;
}

.dropdown-content a {
    font-size: 16px;
    color: var(--vp-c-text-1);
    padding: 10px;
    text-decoration: none;
    display: block;
}

.dropdown-content a:hover {
    transition: color 0.2s;
    color: var(--vp-c-brand-1);
}

.show-content {
    opacity: 1 !important;
}
</style>

<style>
.show {
    display: initial !important;
}

.default-hide {
    display: none;
}
</style>