import MarkdownIt from "markdown-it";
import {upgradingInfos} from "./upgrading";
import fs from "fs";

export const injectUpgradingPartsPlugin = (md: MarkdownIt) => {
    md.core.ruler.before('normalize', 'generate-upgrading-page', (state) => {
        const INJECT_UPGRADING_PARTS = '<div class="upgrading-parts-container"></div>';
        if (!state.src.includes(INJECT_UPGRADING_PARTS)) {
            return;
        }
        state.src = `<div class="upgrading-parts-container">
${state.src.replace(INJECT_UPGRADING_PARTS, upgradingInfos
            .map(info => {
                const targetPartFile = `./docs/en/upgrading-parts/${info.from}-to-${info.to}.md`;
                const targetPartContent = fs.readFileSync(targetPartFile, 'utf-8')
                    .replace(new RegExp("]\\(\\.\\./", 'g'), "](./");
                return `
<div id="${info.from}-to-${info.to}" class="default-hide">

<h2 style="font-family: 'Helvetica Neue', sans-serif;">${info.from} → ${info.to}</h2>

${targetPartContent}

</div>

`
            }).join('\n'))}
</div>`;
    });
}
