<script setup lang="ts">
import Carousel from './components/Carousel/index.vue';
import HotPlaylist from './components/HotPlaylist/index.vue';
import HotSinger from './components/HotSinger/index.vue';
import HotMusic from './components/HotMusic/index.vue';
</script>
<template>
	<div class="recommend">
		<Carousel />
		<HotPlaylist />
		<HotSinger />
		<HotMusic />
	</div>
</template>
<style lang="scss" scoped></style>
