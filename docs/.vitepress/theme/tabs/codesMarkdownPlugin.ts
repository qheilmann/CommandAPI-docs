// copy from https://github.com/sapphi-red/vitepress-plugins , original license is MIT

import MarkdownIt, {Renderer, Token} from "markdown-it";
import container from "markdown-it-container";
import {ruleBlockTab} from "./rulePluginTabs";

type Params = {
    shareStateKey: string | undefined
}

const parseTabsParams = (): Params => {
    return {
        shareStateKey: "always"
    }
}

export const tabsPlugin = (md: MarkdownIt) => {
    md.use(container, 'tabs', {
        render(tokens: Token[], index: number) {
            const token = tokens[index]
            if (token.nesting === 1) {
                const params = parseTabsParams()
                const shareStateKeyProp = params.shareStateKey
                    ? `sharedStateKey="${md.utils.escapeHtml(params.shareStateKey)}"`
                    : ''
                return `<PluginTabs ${shareStateKeyProp}>\n`
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