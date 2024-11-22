import {ref} from 'vue'

export const inBrowser = typeof window !== 'undefined'

function getBoolean(key: string, defaultValue: boolean) {
    if (inBrowser) {
        return (localStorage.getItem(key) ?? defaultValue.toString()) === 'true'
    } else {
        return defaultValue
    }
}

export const openPreferenceKey = 'command-api-docs-prefer-open-preference'
export const openPreference = ref(getBoolean(openPreferenceKey, false))

export const preferMavenKey = 'command-api-docs-prefer-maven'
export const preferMaven = ref(getBoolean(preferMavenKey, true))

export const preferKotlinDslInGradleKey = 'command-api-docs-prefer-kotlin-dsl-in-gradle'
export const preferKotlinDslInGradle = ref(getBoolean(preferKotlinDslInGradleKey, true))

export const preferMojmapKey = 'command-api-docs-prefer-mojmap'
export const preferMojmap = ref(getBoolean(preferMojmapKey, true))