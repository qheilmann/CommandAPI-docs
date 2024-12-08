import {ref} from 'vue'
import {inBrowser} from "vitepress";

function getBoolean(key: string, defaultValue: boolean) {
    if (inBrowser) {
        return (localStorage.getItem(key) ?? defaultValue.toString()) === 'true'
    } else {
        return defaultValue
    }
}

export const openPreferenceKey = 'command-api-docs-prefer-open-preference'
export const openPreference = ref(getBoolean(openPreferenceKey, true))

export const preferMavenKey = 'command-api-docs-prefer-maven'
export const preferMaven = ref(getBoolean(preferMavenKey, false))

export const preferGroovyInGradleKey = 'command-api-docs-prefer-groovy-dsl-in-gradle'
export const preferGroovyInGradle = ref(getBoolean(preferGroovyInGradleKey, false))

export const preferReobfKey = 'command-api-docs-prefer-mojmap'
export const preferReobf = ref(getBoolean(preferReobfKey, false))