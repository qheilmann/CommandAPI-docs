import {ref, watch} from "vue";
import {inBrowser} from "vitepress";

function getString(key: string, defaultValue: string) {
    if (inBrowser) {
        return localStorage.getItem(key) || defaultValue;
    } else {
        return defaultValue;
    }
}

export const currentVersion = ref(getString('current-version', ''));
export const latestVersion = ref('');

watch(currentVersion, (value) => {
    if (inBrowser) {
        localStorage.setItem('current-version', value);
    }
});