<template>
	<div>我是 HOME 组件</div>
	<HelloWorld :msg="msg" />
</template>
<script setup lang="ts">
import { ref } from 'vue';
import HelloWorld from '@/components/HelloWorld.vue';
import { useStore } from 'vuex';

const store = useStore();
// store.dispatch('getUserData');
console.log(store);

let msg = ref('Electron + Vue3 + Vite2 + TS + Eslint');
</script>
<style lang="scss"></style>
