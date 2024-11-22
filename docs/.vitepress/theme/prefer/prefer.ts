import {ref} from 'vue'

export const preferMavenKey = 'command-api-docs-prefer-maven'
export const preferMaven = ref((localStorage.getItem(preferMavenKey) ?? 'true') === 'true')
