<script setup lang="ts">
import { Singer } from '@/networks/singer';
import { useMusicStore } from '@/store/music';
import { useSystemStore } from '@/store/system';
import { storeToRefs } from 'pinia';
const musicStore = useMusicStore();
const systemStore = useSystemStore();
const { showMusicDetail } = storeToRefs(systemStore);
const musicPicClick = () => {
	showMusicDetail.value = !showMusicDetail.value;
};
const singers = computed(() => {
	return musicStore.playMusic.music?.singers
		.map((item: Singer) => item.singerName)
		.join(' / ');
});
</script>
<template>
	<div class="music-bar-left">
		<img
			:src="musicStore.playMusic.music.album?.albumPic"
			alt="logo"
			@click="musicPicClick" />
		<div class="music-info">
			<p class="music-title">
				{{ musicStore.playMusic.music.musicTitle }}
			</p>
			<p class="music-singers">
				{{ singers }}
			</p>
		</div>
	</div>
</template>
<style lang="scss" scoped>
.music-bar-left {
	display: flex;
	justify-content: space-between;
	align-items: center;
	width: 150px;
	height: 50px;
	img {
		height: 50px;
		width: 50px;
		cursor: pointer;
		border-radius: 5px;
	}
	.music-info {
		width: 90px;
		font-size: 12px;
		.music-title {
			margin-bottom: 5px;
			font-size: 14px;
			overflow: hidden;
			text-overflow: ellipsis;
			white-space: nowrap;
		}
		.music-singers {
			color: var(--el-color-info);
			overflow: hidden;
			text-overflow: ellipsis;
			white-space: nowrap;
		}
	}
}
</style>
