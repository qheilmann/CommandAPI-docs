import MarkdownIt from "markdown-it";
import {upgradeInfos} from "./upgrade";
import fs from "fs";

export const injectUpgradePartsPlugin = (md: MarkdownIt) => {
    md.core.ruler.before('normalize', 'generate-upgrade-page', (state) => {
        const INJECT_UPGRADE_PARTS = '<div class="upgrade-parts-container"></div>';
        if (!state.src.includes(INJECT_UPGRADE_PARTS)) {
            return;
        }
        state.src = `<div class="upgrade-parts-container">
${state.src.replace(INJECT_UPGRADE_PARTS, upgradeInfos
            .map(info => {
                const targetPartFile = `./docs/en/upgrade-parts/${info.from}-to-${info.to}.md`;
                const targetPartContent = fs.readFileSync(targetPartFile, 'utf-8');
                return `
<div id="${info.from}-to-${info.to}" class="default-hide">

## ${info.from} → ${info.to} {#${info.from}-to-${info.to}}

${targetPartContent}

</div>

`
            }).join('\n'))}
</div>`;
    });
}
