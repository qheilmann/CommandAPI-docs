<!--modified from https://github.com/vuejs/docs , original license is CC BY 4.0-->

<script setup lang="ts">
import {VTIconChevronDown, VTSwitch} from '@vue/theme'
import {useData, useRoute} from 'vitepress'
import {onMounted, ref, Ref, watch} from 'vue'
import {openPreference, openPreferenceKey, preferKotlinDslInGradle, preferKotlinDslInGradleKey, preferMaven, preferMavenKey, preferMojmap, preferMojmapKey,} from "./prefer";

const {frontmatter} = useData();
let preferencesToDisplay: Ref<string[]> = ref();
const route = useRoute();

watch(
    () => route.path,
    () => refresh()
);

watch(
    openPreference,
    () => localStorage.setItem(openPreferenceKey, String(openPreference.value))
)

function refresh() {
    preferencesToDisplay.value = frontmatter.value["preferences"];
}

const toggleOpen = () => {
    openPreference.value = !openPreference.value;
};

const removeOutline = (e: Event) => {
    (e.target as HTMLElement).classList.add('no-outline');
};

const restoreOutline = (e: Event) => {
    (e.target as HTMLElement).classList.remove('no-outline');
};

const toggleMaven = useToggleFn(
    preferMavenKey,
    preferMaven,
    'prefer-maven'
);

const toggleGradleDsl = useToggleFn(
    preferKotlinDslInGradleKey,
    preferKotlinDslInGradle,
    'prefer-kts'
);

const toggleMapping = useToggleFn(
    preferMojmapKey,
    preferMojmap,
    'prefer-mojmap'
)

function useToggleFn(
    storageKey: string,
    state: Ref<boolean>,
    className: string
) {
    if (typeof localStorage === 'undefined') {
        return () => {
        }
    }
    const classList = document.documentElement.classList;
    return (value = !state.value) => {
        if ((state.value = value)) {
            classList.add(className);
        } else {
            classList.remove(className);
        }
        localStorage.setItem(storageKey, String(state.value));
    }
}

refresh()

onMounted(() => {
    toggleMaven(preferMaven.value);
    toggleGradleDsl(preferKotlinDslInGradle.value);
    toggleMapping(preferMojmap.value);
});
</script>

<template>
    <div v-if="preferencesToDisplay !== undefined" class="preference-switch">
        <button
            class="toggle"
            aria-label="preference switches toggle"
            aria-controls="preference-switches"
            :aria-expanded="openPreference"
            @click="toggleOpen"
            @mousedown="removeOutline"
            @blur="restoreOutline"
        >
            <span>Preference</span>
            <VTIconChevronDown class="vt-link-icon" :class="{ open: openPreference }"/>
        </button>
        <div id="preference-switches"
             :hidden="!openPreference"
             :aria-hidden="!openPreference"
        >
            <div class="mobile-wrapper switches">
                <div v-if="preferencesToDisplay.includes('build-system')" class="switch-container">
                    <label class="gradle-label" @click="toggleMaven(false)">Gradle</label>
                    <VTSwitch
                        class="api-switch"
                        aria-label="prefer maven"
                        :aria-checked="preferMaven"
                        @click="toggleMaven()"
                    />
                    <label class="maven-label" @click="toggleMaven(true)">Maven</label>
                </div>
                <div v-if="preferencesToDisplay.includes('build-system') && !preferMaven" class="switch-container">
                    <label class="groovy-label" @click="toggleGradleDsl(false)">.gradle</label>
                    <VTSwitch
                        class="dsl-switch"
                        aria-label="prefer kts"
                        :aria-checked="preferKotlinDslInGradle"
                        @click="toggleGradleDsl()"
                    />
                    <label class="kts-label" @click="toggleGradleDsl(true)">.gradle.kts</label>
                </div>
                <div v-if="preferencesToDisplay.includes('mapping')" class="switch-container">
                    <label class="reobf-label" @click="toggleMapping(false)">Reobf</label>
                    <VTSwitch
                        class="mapping-switch"
                        aria-label="prefer mojmap"
                        :aria-checked="preferMojmap"
                        @click="toggleMapping()"
                    />
                    <label class="mojmap-label" @click="toggleMapping(true)">Mojmap</label>
                </div>
            </div>
        </div>
        <br class="hide-on-mobile" :hidden="openPreference"/>
    </div>
</template>

<style scoped>
.preference-switch {
    font-size: 12px;
    border-bottom: 1px solid var(--vt-c-divider-light);
    transition: border-color 0.5s, background-color 0.5s ease;
    margin-bottom: 20px;
    top: -0.5px;
    background-color: var(--vt-c-bg);
    padding-top: 10px;
    z-index: 5;
}

@media (max-width: 1279px) {
    .hide-on-mobile {
        display: none;
    }
}

@media (min-width: 1280px) {
    .content-container > .preference-switch {
        display: none;
    }
}

@media (max-width: 1279px) {
    .mobile-wrapper {
        display: flex;
        flex-direction: row;
        gap: 8px;
    }
}

.switches {
    display: flex;
    flex-direction: column;
    gap: 2px;
}

.toggle {
    color: var(--vt-c-text-2);
    transition: color 0.5s;
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding-bottom: 2px;
    width: 100%;
    font-weight: 600;
}

.toggle:hover {
    color: var(--vt-c-text-1);
}

.no-outline {
    outline: 0;
}

.vt-link-icon {
    position: relative;
    top: 1px;
}

.vt-link-icon.open {
    transform: rotate(180deg);
}

#preference-switches {
    padding: 12px 16px;
    background-color: var(--vt-c-bg-soft);
    transition: background-color 0.5s;
    margin: 6px 0 12px;
    border-radius: 8px;
    font-weight: 600;
}

.switch-container {
    display: flex;
    align-items: center;
}

@media (max-width: 959px) {
    .switch-container {
        padding: 0 1em;
    }
}

.switch-container:not(:first-child) {
    margin-top: 10px;
}

.vt-switch {
    margin-right: 5px;
    transform: scale(0.8);
}

.switch-container label {
    transition: color 0.5s;
    cursor: pointer;
}

.switch-container label:first-child {
    width: 50px;
}

.switch-link {
    margin-left: 8px;
    font-size: 11px;
    min-width: 14px;
    height: 14px;
    line-height: 13px;
    text-align: center;
    color: var(--vt-c-green);
    border: 1px solid var(--vt-c-green);
    border-radius: 50%;
}

@media (max-width: 1439px) {
    #preference-switches {
        font-size: 11px;
        padding: 8px 4px;
    }

    .vt-switch {
        margin: auto;
    }

    .switch-link {
        margin-left: auto;
    }

    .switch-container label:first-child {
        width: 46px;
    }
}
</style>

<style>
.maven,
.kts,
.mojmap {
    display: none;
}

.prefer-maven .gradle,
.prefer-kts .groovy,
.prefer-mojmap .reobf {
    display: none;
}

.prefer-maven .maven,
.prefer-kts .kts,
.prefer-mojmap .mojmap {
    display: initial;
}

.maven-label,
.kts-label,
.mojmap-label,
.prefer-maven .gradle-label,
.prefer-kts .groovy-label,
.prefer-mojmap .reobf-label {
    color: var(--vt-c-text-3);
}

.prefer-maven .maven-label,
.prefer-kts .kts-label,
.prefer-mojmap .mojmap-label {
    color: var(--vt-c-text-1);
}

.prefer-maven .api-switch .vt-switch-check {
    transform: translateX(18px);
}

.prefer-kts .dsl-switch .vt-switch-check {
    transform: translateX(18px);
}

.prefer-mojmap .mapping-switch .vt-switch-check {
    transform: translateX(18px);
}

.tip .gradle,
.tip .groovy,
.tip .kts,
.tip .maven,
.tip .mojmap,
.tip .reobf {
    color: var(--vt-c-text-code);
    /* transition: color 0.5s; */
    font-weight: 600;
}
</style>