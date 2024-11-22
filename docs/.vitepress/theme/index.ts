import type {Theme} from 'vitepress'
import DefaultTheme from 'vitepress/theme'
import PluginTabs from './components/PluginTabs.vue'
import PluginTabsTab from './components/PluginTabsTab.vue'
import {provideTabsSharedState} from './tabs/useTabsSelectedState'
import PreferenceSwitch from './components/PreferenceSwitch.vue';
import mediumZoom from "medium-zoom";
import {onMounted, watch, nextTick, h} from 'vue'
import {useRoute} from 'vitepress'
import './style/global.css'

export default {
    extends: DefaultTheme,
    enhanceApp({app}) {
        provideTabsSharedState(app)
        app.component('PluginTabs', PluginTabs)
        app.component('PluginTabsTab', PluginTabsTab)
    },
    Layout() {
        const children = {
            "doc-before": () => h(PreferenceSwitch),
            "aside-outline-before": () => h(PreferenceSwitch),
        };

        return h(DefaultTheme.Layout, null, children);
    },
    setup() {
        const route = useRoute()
        const initZoom = () => {
            mediumZoom('.main img', {background: 'var(--vp-c-bg)'})
        }
        onMounted(() => {
            initZoom()
        })
        watch(
            () => route.path,
            () => nextTick(() => initZoom())
        )
    }
} satisfies Theme