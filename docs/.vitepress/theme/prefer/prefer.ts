import {ref} from 'vue'

export const inBrowser = typeof window !== 'undefined'

export const preferMavenKey = 'command-api-docs-prefer-maven'
export const preferMaven = ref(inBrowser ? (localStorage.getItem(preferMavenKey) ?? 'true') === 'true' : true)
