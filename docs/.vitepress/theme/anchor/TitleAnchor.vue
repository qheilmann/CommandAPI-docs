<script setup>

const props = defineProps({
    anchor: String
})

function onClick() {
    const element = document.getElementById(props.anchor)
    if(!element) {
        return;
    }
    const y = element.getBoundingClientRect().top + window.scrollY - 109;
    window.scrollTo({ top: y, behavior: 'smooth' });
    history.pushState(null, '', `#${props.anchor}`);
}
</script>

<template>
    <div @click="onClick">
        <slot/>
    </div>
</template>

<style scoped>

</style>