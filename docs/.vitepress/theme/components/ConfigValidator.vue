<script setup lang="ts">
import {onMounted, onUnmounted, ref} from 'vue';
import {parse} from "yaml";
import {createHighlighter} from 'shiki'
import {HighlighterGeneric} from "@shikijs/types";

enum Status {
    PROCESSING,
    PASS,
    INVALID_FORMAT,
}

const status = ref(Status.PROCESSING);
const codeBlockHtml = ref("")
const errorLines = ref(new Set<number>())
let highlighter: HighlighterGeneric<any, any>;

const handleDrop = async (event: DragEvent) => {
    status.value = Status.PROCESSING;
    const file = event.dataTransfer.files[0];
    errorLines.value.clear();
    if (!file || !file.name.endsWith(".yml")) {
        status.value = Status.INVALID_FORMAT;
        return;
    }
    const ymlContent = await file.text();
    try {
        parse(ymlContent);
    } catch (_) {
        status.value = Status.INVALID_FORMAT;
    }
    ymlContent.split('\n').forEach((line, index) => {
        if (line.includes('\t')) {
            errorLines.value.add(index + 1);
            return;
        }
        if (!line.includes(":")) {
            return;
        }
        const trimStartLine = line.trimStart();
        if (trimStartLine.startsWith("#")) {
            return;
        }
        const spiltLine = trimStartLine.split(":");
        if (spiltLine.length !== 2) {
            errorLines.value.add(index + 1);
            return;
        }
        const trimEndLine = spiltLine[1].trimEnd();
        if (trimEndLine !== "" && !trimEndLine.startsWith(" ")) {
            errorLines.value.add(index + 1);
        }
    });
    if (errorLines.value.size !== 0) {
        status.value = Status.INVALID_FORMAT;
    }
    let html = highlighter.codeToHtml(ymlContent, {
        lang: 'yaml',
        themes: {
            light: 'github-light',
            dark: 'github-dark'
        },
        defaultColor: false
    }).replace("shiki shiki-themes", "shiki shiki-themes vp-code");
    if (status.value === Status.INVALID_FORMAT) {
        let count = 0;
        const regex = new RegExp("<span class=\"line\">", 'g');
        codeBlockHtml.value = html.replace(regex, (match) => {
            count++;
            if (errorLines.value.has(count)) {
                return "<span class=\"line highlighted error\">";
            }
            return match;
        });
    } else {
        codeBlockHtml.value = html;
        status.value = Status.PASS
    }
}

onMounted(async () => {
    highlighter = await createHighlighter({
        themes: ['github-dark', "github-light"],
        langs: ['yaml'],
    })
})

onUnmounted(() => {
    errorLines.value.clear();
    highlighter.dispose();
})
</script>

<template>
    <div class="drop_zone_parent">
        <div class="drop_zone" @drop.prevent="handleDrop" @dragover.prevent>
            <span><b class="drop_zone_text" id="plugin_upload_text">Drag and drop a <code>config.yml</code> file here</b></span>
        </div>
        <div class="important custom-block" v-if="status === Status.INVALID_FORMAT">
            <div class="custom-block-title">Unfortunately, there is {{errorLines.size}} errors in your <code>config.yml</code></div>
            <div class="language-yaml vp-adaptive-theme">
                <span class="lang">yaml</span>
                <div v-html="codeBlockHtml"></div>
            </div>
        </div>
        <div class="important custom-block" v-if="status === Status.PASS">
            <div class="custom-block-title">Congratulations! There isn't any error in your <code>config.yml</code>!</div>
        </div>
    </div>
</template>

<style scoped>
.drop_zone_parent {
    padding: 20px;
}

.drop_zone {
    border-radius: 20px;
    padding: 20px;
    border-style: dashed;
    height: 50px;
    display: flex;
    justify-content: center;
    align-items: center;
}

.drop_zone_text {
    margin-left: 20px;
    margin-top: 20px;
}
</style>