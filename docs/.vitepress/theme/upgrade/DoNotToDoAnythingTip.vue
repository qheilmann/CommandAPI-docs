<script setup>

import {nowChanges, nowUpgradeInfo} from "./upgrade";
</script>

<template>
    <div style="text-align: center" v-if="nowChanges.length === 0  ">
        <b>Congratulations!<br/>You do not need to do anything to upgrade from {{nowUpgradeInfo.from}} to {{nowUpgradeInfo.to}}</b>
    </div>
</template>

<style scoped>

</style>