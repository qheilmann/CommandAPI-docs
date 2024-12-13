import type {Theme} from 'vitepress'
import {useData, useRoute} from 'vitepress'
import DefaultTheme from 'vitepress/theme'
import PluginTabs from './tabs/PluginTabs.vue'
import PluginTabsTab from './tabs/PluginTabsTab.vue'
import {provideTabsSharedState} from './tabs/useTabsSelectedState'
import PreferenceSwitch from './preference/PreferenceSwitch.vue';
import mediumZoom from "medium-zoom";
import {h, nextTick, onMounted, watch} from 'vue'
import AuthorsComponent from "./author/PageAuthors.vue";
import {NolebaseEnhancedReadabilitiesMenu, NolebaseEnhancedReadabilitiesScreenMenu} from "@nolebase/vitepress-plugin-enhanced-readabilities";

import './style/global.css'
import '@nolebase/vitepress-plugin-enhanced-readabilities/client/style.css'
import VersionSwitcher from "./versioning/VersionSwitcher.vue";
import TitleAnchor from "./anchor/TitleAnchor.vue";
import NotFoundComponent from "./404/NotFoundComponent.vue";
import DownloadCard from "./download/DownloadCard.vue";

// noinspection JSUnusedGlobalSymbols
export default {
    extends: DefaultTheme,
    enhanceApp({app}) {
        provideTabsSharedState(app)
        app.component('DownloadCard', DownloadCard)
        app.component('VersionSwitcher', VersionSwitcher)
        app.component('PluginTabs', PluginTabs)
        app.component('PluginTabsTab', PluginTabsTab)
        app.component('TitleAnchor', TitleAnchor)
    },
    Layout() {
        const children = {
            "doc-before": () => h(PreferenceSwitch),
            "aside-outline-before": () => h(PreferenceSwitch),
            "aside-outline-after": () => h(AuthorsComponent),
            'nav-bar-content-after': () => h(NolebaseEnhancedReadabilitiesMenu),
            'nav-screen-content-after': () => h(NolebaseEnhancedReadabilitiesScreenMenu),
        };

        if (useData().page.value.isNotFound) {
            children["not-found"] = () => h(NotFoundComponent);
        }

        return h(DefaultTheme.Layout, null, children);
    },
    setup() {
        const route = useRoute();
        onMounted(async () => {
            initZoom()
            injectIndexPageIcons()
            await nextTick(() => scrollToActiveSidebarItem())
        });
        watch(() => route.path, () => nextTick(() => {
            scrollToActiveSidebarItem()
            injectIndexPageIcons()
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

function injectIndexPageIcons() {
    document.querySelectorAll("a.VPButton.medium").forEach((button) => {
        const a = button as HTMLLinkElement;
        const href = a.href;
        if (href.includes("CommandAPI/releases/latest")) {
            a.classList.add("index-download-button");
        } else if (href.includes("/intro")) {
            a.classList.add("index-docs-button");
        }
    })
}