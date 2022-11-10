<script setup lang="ts">
import { useMusicStore } from '@/store/music';
import { useIsPlayOne } from '../MusicBarCenter/useIsPlayOne';
import { useNextHandle } from '../MusicBarCenter/useNextHandle';
const musicStore = useMusicStore();
const nextHandle = useNextHandle();
const isPlayOne = useIsPlayOne();
const audio = ref(null);
const timeUpdate = () => {
	if (!musicStore.playMusic.audio) {
		return;
	}
	musicStore.playMusic.currentTime = (
		musicStore.playMusic.audio as unknown as HTMLAudioElement
	).currentTime;
	if (!musicStore.playMusic.isDragging) {
		// musicStore.playMusic.progress =
		// 	musicStore.playMusic.currentTime /
		// 	(musicStore.playMusic.musicList[musicStore.playMusic.playOrders[musicStore.playMusic.currentIndex]]
		// 		.duration as number);
		musicStore.playMusic.progress =
			musicStore.playMusic.currentTime / musicStore.playMusic.music.duration;
	}
};
const ended = () => {
	nextHandle();
};
onMounted(() => {
	musicStore.playMusic.audio = audio as unknown as HTMLAudioElement;
});
</script>
<template>
	<audio
		v-show="false"
		ref="audio"
		:muted="musicStore.playMusic.muted"
		:loop="isPlayOne"
		:src="
			musicStore.playMusic.music.downloadItemInfo?.localPath ??
			musicStore.playMusic.music.musicUrl
		"
		:controls="musicStore.playMusic.controls"
		@ended="ended"
		@timeupdate="timeUpdate" />
</template>
<style lang="scss" scoped></style>
