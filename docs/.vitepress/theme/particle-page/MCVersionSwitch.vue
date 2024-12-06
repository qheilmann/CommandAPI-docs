<script setup lang="ts">
import {ref, Ref} from "vue";
import {VTSwitch} from "@vue/theme";

const before1204 = ref(false);

function useToggleFn(
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
    }
}

const toggleBefore1204 = useToggleFn(before1204, "choose-before-1204")
</script>

<template>
    <div class="switch-container" id="preference-switches">
        <label class="after-1205-label" style="width: fit-content" @click="toggleBefore1204(false)">1.20.5+</label>
        <VTSwitch
            class="mcver-switch"
            aria-label="before 1.20.4"
            :aria-checked="before1204"
            @click="toggleBefore1204()"
        />
        <label class="before-1204-label" style="width: fit-content" @click="toggleBefore1204(true)">1.20.4-</label>
    </div>
</template>

<style scoped>
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

@media (max-width: 1439px) {
    .vt-switch {
        margin: auto;
    }

    .switch-container label:first-child {
        width: 46px;
    }
}

#preference-switches {
    width: fit-content;
    padding: 12px 16px;
    background-color: var(--vt-c-bg-soft);
    margin: 6px 0 12px;
    border-radius: 8px;
    font-weight: 600;
}
</style>

<style>
.after-1205 {
    display: initial;
}

.before-1204 {
    display: none;
}

.choose-before-1204 .before-1204 {
    display: initial;
}

.choose-before-1204 .after-1205 {
    display: none;
}

.choose-before-1204 .after-1205-label,
.before-1204-label {
    color: var(--vt-c-text-3);
}

.choose-before-1204 .before-1204-label,
.after-1205-label {
    color: var(--vt-c-text-1);
}

.choose-before-1204 .mcver-switch .vt-switch-check {
    transform: translateX(18px);
}

.tip .after-1205,
.tip .before-1204 {
    color: var(--vt-c-text-code);
    /* transition: color 0.5s; */
    font-weight: 600;
}
</style>