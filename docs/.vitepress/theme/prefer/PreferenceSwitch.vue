<!--modified from https://github.com/vuejs/docs , original license is CC BY 4.0-->

<script setup lang="ts">
import {VTIconChevronDown, VTSwitch} from '@vue/theme'
import {useData, useRoute} from 'vitepress'
import {onMounted, ref, Ref, watch} from 'vue'
import {openPreference, openPreferenceKey, preferGroovyInGradle, preferGroovyInGradleKey, preferMaven, preferMavenKey, preferReobf, preferReobfKey,} from "./prefer";

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
    preferGroovyInGradleKey,
    preferGroovyInGradle,
    'prefer-groovy'
);

const toggleMapping = useToggleFn(
    preferReobfKey,
    preferReobf,
    'prefer-reobf'
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
    toggleGradleDsl(preferGroovyInGradle.value);
    toggleMapping(preferReobf.value);
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
                    <label class="gradle-label prefer-label-left" @click="toggleMaven(false)">Gradle</label>
                    <VTSwitch
                        class="api-switch"
                        aria-label="prefer maven"
                        :aria-checked="preferMaven"
                        @click="toggleMaven()"
                    />
                    <label class="maven-label prefer-label-right" @click="toggleMaven(true)">Maven</label>
                </div>
                <div v-if="preferencesToDisplay.includes('build-system') && !preferMaven" class="switch-container">
                    <label class="kts-label prefer-label-left" style="width: fit-content" @click="toggleGradleDsl(false)">.gradle.kts</label>
                    <VTSwitch
                        class="dsl-switch"
                        aria-label="prefer groovy"
                        :aria-checked="preferGroovyInGradle"
                        @click="toggleGradleDsl()"
                    />
                    <label class="groovy-label prefer-label-right" @click="toggleGradleDsl(true)">.gradle</label>
                </div>
                <div v-if="preferencesToDisplay.includes('mapping')" class="switch-container">
                    <label class="mojmap-label prefer-label-left" @click="toggleMapping(false)">Mojmap</label>
                    <VTSwitch
                        class="mapping-switch"
                        aria-label="prefer reobf"
                        :aria-checked="preferReobf"
                        @click="toggleMapping()"
                    />
                    <label class="reobf-label prefer-label-right" @click="toggleMapping(true)">Reobf</label>
                </div>
            </div>
        </div>
        <br class="hide-on-mobile" :hidden="openPreference"/>
    </div>
</template>

<style scoped>
.prefer-label-left {
    flex: 1;
    text-align: left;
}

.prefer-label-right {
    flex: 1;
    text-align: right;
}

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

@media (min-width: 1280px) {
    .content-container > .preference-switch {
        display: none;
    }
}

@media (max-width: 1279px) {
    .hide-on-mobile {
        display: none;
    }

    .mobile-wrapper {
        display: flex;
        flex-direction: row;
        gap: 8px;
    }

    .switch-container {
        padding: 0 1em;
    }

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
    transition: transform 0.5s
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


</style>

<style>
.maven,
.groovy,
.reobf {
    display: none;
}

.prefer-maven .gradle,
.prefer-groovy .kts,
.prefer-reobf .mojmap {
    display: none;
}

.prefer-maven .maven,
.prefer-groovy .groovy,
.prefer-reobf .reobf {
    display: initial;
}

.maven-label,
.groovy-label,
.reobf-label,
.prefer-maven .gradle-label,
.prefer-groovy .kts-label,
.prefer-reobf .mojmap-label {
    color: var(--vt-c-text-3);
}

.prefer-maven .maven-label,
.prefer-groovy .groovy-label,
.prefer-reobf .reobf-label {
    color: var(--vt-c-text-1);
}

.prefer-maven .api-switch .vt-switch-check {
    transform: translateX(18px);
}

.prefer-groovy .dsl-switch .vt-switch-check {
    transform: translateX(18px);
}

.prefer-reobf .mapping-switch .vt-switch-check {
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