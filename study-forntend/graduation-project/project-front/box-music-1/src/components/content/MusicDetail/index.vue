<script setup lang="ts">
import MusicPic from './components/MusicPic/index.vue';
import MusicLyric from './components/MusicLyric/index.vue';
</script>
<template>
	<div class="music-detail">
		<div class="left-container">
			<MusicPic />
		</div>
		<div class="right-container"><MusicLyric /></div>
	</div>
</template>
<style lang="scss" scoped>
.music-detail {
	display: flex;
	width: 100%;
	min-width: 960px;
	padding: 10px;
	.left-container {
		flex: 1;
		min-width: 480px;
	}
	.right-container {
		flex: 1.5;
		min-width: 480px;
	}
}
</style>
