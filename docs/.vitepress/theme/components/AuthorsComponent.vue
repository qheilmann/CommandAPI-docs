<!--modified from https://github.com/FabricMC/fabric-docs , original license is CC BY-NC-SA 4.0 -->

<script setup lang="ts">
import {onContentUpdated, useData, useRoute} from "vitepress";
import { ref } from "vue";

const data = useData();
const route = useRoute();

const authors = ref<string[]>([]);
const authorsNoGitHub = ref<string[]>([]);
const heading = ref<string>("");
const labelNoGitHub = ref<string>("");

function refreshOptions() {
  const isZh = route.path.startsWith("/zhHans/");
  heading.value = isZh ? "本页作者" : "Page authors";
  labelNoGitHub.value = isZh ? "无 GitHub 账号作者" : "Authors without GitHub account";

  authors.value = [];
  if (data.frontmatter.value["authors"]) {
    let notSortedAuthors = [...data.frontmatter.value["authors"]];
    notSortedAuthors.sort((a, b) => a.localeCompare(b));
    authors.value = notSortedAuthors;
  }

  authorsNoGitHub.value = [];
  if (data.frontmatter.value["authors-nogithub"]) {
    let notSortedAuthorsNoGitHub = [...data.frontmatter.value["authors-nogithub"]];
    notSortedAuthorsNoGitHub.sort((a, b) => a.localeCompare(b));
    authorsNoGitHub.value = notSortedAuthorsNoGitHub;
  }
}

onContentUpdated(() => {
  refreshOptions();
});
</script>

<template>
  <div v-if="authors.length > 0" class="authors-section">
    <h2>{{ heading }}</h2>
    <div class="page-authors">
      <a
          v-for="author in authors"
          :href="`https://github.com/${author}`"
          target="_blank"
          class="author-link"
          :title="author"
      >
        <img
            loading="lazy"
            class="author-avatar"
            :src="`https://wsrv.nl/?url=${encodeURIComponent(
            `https://github.com/${author}.png?size=32`
          )}&af`"
            :alt="author"
        />
      </a>
      <img
          v-for="author in authorsNoGitHub"
          loading="lazy"
          class="author-avatar"
          :src="`https://wsrv.nl/?url=${encodeURIComponent(
          'https://github.com/CommandAPI.png?size=32'
        )}&af`"
          :alt="author"
          :title="labelNoGitHub.replace('%s', author)"
      />
    </div>
  </div>
</template>

<style scoped>
.authors-section {
  margin-top: 8px;
}

.authors-section > h2 {
  font-weight: 700;
  color: var(--vp-c-text-1);
  font-size: 14px;
}

.page-authors {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  align-items: center;
  margin-top: 8px;
  gap: 8px;
}

.author-avatar {
  border-radius: 50%;
  width: 32px;
  height: 32px;

  transition: filter 0.2s ease-in-out;
}

.page-authors > a:hover > .author-avatar {
  filter: brightness(1.2);
}
</style>