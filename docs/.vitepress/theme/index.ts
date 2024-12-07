import type {Theme} from 'vitepress'
import DefaultTheme from 'vitepress/theme'
import PluginTabs from './tabs/PluginTabs.vue'
import PluginTabsTab from './tabs/PluginTabsTab.vue'
import {provideTabsSharedState} from './tabs/useTabsSelectedState'
import PreferenceSwitch from './prefer/PreferenceSwitch.vue';
import mediumZoom from "medium-zoom";
import {onMounted, watch, nextTick, h} from 'vue'
import {useRoute} from 'vitepress'
import AuthorsComponent from "./author/PageAuthors.vue";
import {NolebaseEnhancedReadabilitiesMenu, NolebaseEnhancedReadabilitiesScreenMenu} from "@nolebase/vitepress-plugin-enhanced-readabilities";

import './style/global.css'
import '@nolebase/vitepress-plugin-enhanced-readabilities/client/style.css'
import VersionSwitcher from "./versioning/VersionSwitcher.vue";

// noinspection JSUnusedGlobalSymbols
export default {
    extends: DefaultTheme,
    enhanceApp({app}) {
        provideTabsSharedState(app)
        app.component('VersionSwitcher', VersionSwitcher)
        app.component('PluginTabs', PluginTabs)
        app.component('PluginTabsTab', PluginTabsTab)
    },
    Layout() {
        const children = {
            "doc-before": () => h(PreferenceSwitch),
            "aside-outline-before": () => h(PreferenceSwitch),
            "aside-outline-after": () => h(AuthorsComponent),
            'nav-bar-content-after': () => h(NolebaseEnhancedReadabilitiesMenu),
            'nav-screen-content-after': () => h(NolebaseEnhancedReadabilitiesScreenMenu),
        };

        return h(DefaultTheme.Layout, null, children);
    },
    setup() {
        const route = useRoute();
        onMounted(async () => {
            initZoom()
            await nextTick(() => scrollToActiveSidebarItem())
        });
        watch(() => route.path, () => nextTick(() => {
            scrollToActiveSidebarItem()
            initZoom()
        }));
    }
} satisfies Theme

function initZoom() {
    mediumZoom('.main img', {background: 'var(--vp-c-bg)'})
}

function scrollToActiveSidebarItem() {
    const activeLink = document.querySelector('#VPSidebarNav div.is-link.is-active.has-active')
    if (activeLink) {
        activeLink.scrollIntoView({behavior: 'smooth', block: 'center'})
    }
}