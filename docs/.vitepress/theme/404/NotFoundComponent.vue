<script setup lang="ts">
import { useData } from "vitepress";
import { computed } from "vue";
import { changingVersion, currentVersion, isLatest, isOld } from "../versioning/version";
import getRandomNotFoundMsg from "./notFoundMsg";

const {theme} = useData();
const redirectFromAnotherVersion = computed(() =>
    window.location.search.startsWith("?from=") && !changingVersion.value,
);
const redirectFromVersion = computed(() => {
    if (redirectFromAnotherVersion.value) {
        return window.location.search.split("?from=")[1].split("&")[0];
    }
    return "";
});
</script>

<template>
    <div class="NotFound">
        <p class="code">{{ changingVersion ? "" : "404" }}</p>
        <h1 class="title">{{ changingVersion ? "Redirecting" : "PAGE NOT FOUND" }}</h1>
        <div class="divider" />
        <div v-if="redirectFromAnotherVersion">
            You switched here from version {{ redirectFromVersion }}, but this version does not have a corresponding page.
        </div>
        <div v-else class="quote">{{ changingVersion ? "Please wait......" : getRandomNotFoundMsg() }}</div>

        <div class="action" v-if="!changingVersion">
            <a
                class="link"
                :href="isLatest ? '/' : `/${currentVersion}/`"
                :aria-label="theme.notFound?.linkLabel ?? 'go to home'"
                :onclick="isOld ? 'location.reload(true);location.href=this.href;return false;' : undefined"
            >
                {{ theme.notFound?.linkText ?? "Take me home" }}
            </a>
        </div>
    </div>
</template>

<style scoped>
.NotFound {
    padding: 64px 24px 96px;
    text-align: center;
}

@media (min-width: 768px) {
    .NotFound {
        padding: 96px 32px 168px;
    }
}

.code {
    line-height: 64px;
    font-size: 64px;
    font-weight: 600;
}

.title {
    padding-top: 12px;
    letter-spacing: 2px;
    line-height: 20px;
    font-size: 20px;
    font-weight: 700;
}

.divider {
    margin: 24px auto 18px;
    width: 64px;
    height: 1px;
    background-color: var(--vp-c-divider);
}

.quote {
    margin: 0 auto;
    max-width: 256px;
    font-size: 14px;
    font-weight: 500;
    color: var(--vp-c-text-2);
}

.action {
    padding-top: 20px;
}

.link {
    display: inline-block;
    border: 1px solid var(--vp-c-brand-1);
    border-radius: 16px;
    padding: 3px 16px;
    font-size: 14px;
    font-weight: 500;
    color: var(--vp-c-brand-1);
    transition: border-color 0.25s,
    color 0.25s;
}

.link:hover {
    border-color: var(--vp-c-brand-2);
    color: var(--vp-c-brand-2);
}
</style>