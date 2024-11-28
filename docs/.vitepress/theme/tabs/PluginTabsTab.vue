<!--copy from https://github.com/sapphi-red/vitepress-plugins , original license is MIT-->

<script setup lang="ts">
import { useTabsSingleState } from './useTabsSingleState'

defineProps<{ label: string }>()

const { uid, selected } = useTabsSingleState()
</script>

<template>
  <div
      v-if="selected === label"
      :id="`panel-${label}-${uid}`"
      class="plugin-tabs--content"
      role="tabpanel"
      tabindex="0"
      :aria-labelledby="`tab-${label}-${uid}`"
  >
    <slot />
  </div>
</template>

<style scoped>
.plugin-tabs--content {
  padding: 0;
}

.plugin-tabs--content > :first-child:first-child {
  margin-top: 0;
}

.plugin-tabs--content > :last-child:last-child {
  margin-bottom: 0;
}

.plugin-tabs--content > :deep(div[class*='language-']) {
  border-radius: 8px;
  margin: 16px 0;
}

:root:not(.dark) .plugin-tabs--content :deep(div[class*='language-']) {
  background-color: var(--vp-c-bg-alt);
}
</style>