// copy from https://github.com/sapphi-red/vitepress-plugins , original license is MIT

// @ts-ignore
import MarkdownIt, {Renderer, Token} from "markdown-it";
// @ts-ignore
import container from "markdown-it-container";
import {ruleBlockTab} from "./rulePluginTabs";

type Params = {
    shareStateKey: string | undefined
}

export const tabsPlugin = (md: MarkdownIt) => {
    md.use(container, 'tabs', {
        render(tokens: Token[], index: number) {
            let i = index + 1;
            let tabsCnt = 0;
            while(tokens[i].type !== 'container_tabs_close') {
                if (tokens[i].type === 'tab_open') {
                    tabsCnt++;
                }
                i++;
                if (i >= tokens.length) {
                    break;
                }
            }
            const token = tokens[index]
            if (token.nesting === 1) {
                return `<PluginTabs sharedStateKey="${tabsCnt}">\n`
            } else {
                return `</PluginTabs>\n`
            }
        }
    })

    md.block.ruler.after('container_tabs', 'tab', ruleBlockTab)
    const renderTab: Renderer.RenderRule = (tokens, index) => {
        const token = tokens[index]
        if (token.nesting === 1) {
            const label = token.info
            const labelProp = `label="${md.utils.escapeHtml(label)}"`
            return `<PluginTabsTab ${labelProp}>\n`
        } else {
            return `</PluginTabsTab>\n`
        }
    }
    md.renderer.rules['tab_open'] = renderTab
    md.renderer.rules['tab_close'] = renderTab
}