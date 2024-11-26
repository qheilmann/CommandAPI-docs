<script setup lang="ts">
import {ref} from 'vue';
import JSZip from 'jszip';
import {parse} from "yaml";

enum Status {
    PROCESSING,
    SUCCESS,
    INVALID_PLUGIN_JAR,
    INVALID_PLUGIN_YML,
    IS_PAPER_PLUGIN
}

class PluginInfo {
    name: string;
    fileName: string;
    commands: Set<CommandInfo> = new Set<CommandInfo>();
}

class CommandInfo {
    name: string;
    description: string;
}

const status = ref(Status.PROCESSING);
const pluginInfo = ref(new PluginInfo());

const handleDrop = async (event: DragEvent) => {
    status.value = Status.PROCESSING;
    const file = event.dataTransfer.files[0];
    pluginInfo.value.fileName = (!file) ? "unknown" : file.name;
    if (!file || !file.name.endsWith(".jar")) {
        status.value = Status.INVALID_PLUGIN_JAR;
        return;
    }
    try {
        const zip = await new JSZip().loadAsync(file);
        try {
            const ymlContent = await zip.file("plugin.yml").async("string");
            try {
                const ymlData = parse(ymlContent);
                if (ymlData.name === undefined) {
                    status.value = Status.INVALID_PLUGIN_YML;
                    return;
                }
                pluginInfo.value.commands.clear();
                pluginInfo.value.name = ymlData.name;
                if (ymlData.commands !== undefined) {
                    for (let name of Object.keys(ymlData.commands)) {
                        const info = new CommandInfo();
                        info.name = name;
                        if(ymlData.commands[name] === null || !("description" in ymlData.commands[name])) {
                            info.description = undefined;
                        } else {
                            info.description = ymlData.commands[name].description;
                        }
                        pluginInfo.value.commands.add(info);
                    }
                }
                status.value = Status.SUCCESS;
            } catch (_) {
                status.value = Status.INVALID_PLUGIN_YML;
            }
        } catch (_) {
            try {
                const ymlContent = await zip.file("paper-plugin.yml").async("string");
                const ymlData = parse(ymlContent);
                if (ymlData.name === undefined) {
                    status.value = Status.INVALID_PLUGIN_YML;
                    return;
                }
                pluginInfo.value.name = ymlData.name;
                status.value = Status.IS_PAPER_PLUGIN;
                return;
            } catch (_) {
                status.value = Status.INVALID_PLUGIN_YML;
                return;
            }
        }
    } catch (_) {
        status.value = Status.INVALID_PLUGIN_JAR;
    }
};
</script>

<template>
    <div class="drop_zone_parent">
        <div class="drop_zone" @drop.prevent="handleDrop" @dragover.prevent>
            <span><b class="drop_zone_text" id="plugin_upload_text">Drag and drop a plugin.jar file here</b></span>
        </div>
        <div class="important custom-block" v-if="status === Status.SUCCESS">
            <div class="custom-block-title">Successfully parsed plugin <code>{{pluginInfo.name}}</code>,
                {{ pluginInfo.commands.size === 0 ? " no commands found." : ` found ${pluginInfo.commands.size} commands inside.` }}
            </div>
            <ul v-if="pluginInfo.commands.size !== 0">
                <li v-for="command in Array.from(pluginInfo.commands)" :key="command.name">
                    <code>{{ command.name }}</code> - {{ command.description }}
                </li>
            </ul>
        </div>
        <div class="danger custom-block" v-if="status === Status.INVALID_PLUGIN_JAR || status === Status.INVALID_PLUGIN_YML">
            <div class="custom-block-title">Failed to parse <code>{{pluginInfo.fileName}}</code>,
                {{ status === Status.INVALID_PLUGIN_JAR ?
                    " it is not a valid plugin JAR file." :
                    " it contains an invalid plugin.yml file." }}
            </div>
        </div>
        <div class="warning custom-block" v-if="status === Status.IS_PAPER_PLUGIN">
            <div class="custom-block-title"><code>{{pluginInfo.fileName}}</code>
                is a Paper-only plugin, which can‘t register commands in the <code>paper-plugin.yml</code> file.
            </div>
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