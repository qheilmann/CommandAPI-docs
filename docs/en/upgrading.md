---
order: 13
preferences: ['build-system']
authors:
    - JorelAli
    - DerEchtePilz
    - willkroboth
    - MC-XiaoHei
outline: false
---

<script setup>

import UpgradeHelper from '../.vitepress/theme/upgrade/UpgradeHelper.vue';
import DoNotToDoAnythingTip from '../.vitepress/theme/upgrade/DoNotToDoAnythingTip.vue';

</script>

<div class="hide-anchor">

# Upgrading

:::info

Please select your current version and the version you want to upgrade to below. The page will automatically display the steps you need to follow.

If you are upgrading from a version that is not listed, please select the next version that is slightly larger than your version.

e.g., if you are upgrading from 8.7.4, you should select to upgrade from 8.8.0.

<UpgradeHelper></UpgradeHelper>

:::

<div class="upgrade-parts-container"></div>

<DoNotToDoAnythingTip></DoNotToDoAnythingTip>

</div>