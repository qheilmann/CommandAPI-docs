import {ref, watch} from "vue";
import {inBrowser} from "vitepress";

function getString(key: string, defaultValue: string) {
    if (inBrowser) {
        return sessionStorage.getItem(key) || defaultValue;
    } else {
        return defaultValue;
    }
}

function getBoolean(key: string, defaultValue: boolean) {
    if (inBrowser) {
        return sessionStorage.getItem(key) === 'true';
    } else {
        return defaultValue;
    }
}

export const isLatest = ref(true);
export const isOld = ref(false);
export const currentVersion = ref(getString('current-version', ''));
export const latestVersion = ref('');
export const changingVersion = ref(getBoolean('changing-version', true));

watch(currentVersion, (value: string) => {
    if (inBrowser) {
        sessionStorage.setItem('current-version', value);
    }
});