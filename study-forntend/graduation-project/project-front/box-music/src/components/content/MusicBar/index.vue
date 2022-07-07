<script setup lang="ts">
import Audio from './components/Audio/index.vue';
import { useMusicStore } from '@/store/music';
const musicStore = useMusicStore();
</script>
<template>
	<div v-if="musicStore.playMusic.music?.musicId" class="music-bar">
		<Audio />
		<div class="container">
			<MusicBarLeft />
			<MusicBarCenter />
			<MusicBarRight />
		</div>
	</div>
	<div v-else class="music-no-music">请选择音乐播放</div>
</template>
<style lang="scss" scoped>
.music-bar {
	height: 100%;
	min-width: 930px;
	padding: 0 20px;
	.container {
		display: flex;
		justify-content: space-between;
		align-items: center;
		height: 100%;
	}
}
.music-no-music {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100%;
	color: var(--el-text-color-secondary);
}
</style>
