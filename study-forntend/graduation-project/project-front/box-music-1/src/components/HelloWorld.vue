<script setup lang="ts">
// const electronAPI = window.electronAPI;

defineProps<{ msg: string }>();

// const ffmpeg = require('fluent-ffmpeg');

// electronAPI.getFFmpeg((ffmpeg: any) => {
// 	console.log(ffmpeg);

// 	// ffmpeg.ffprobe(
// 	// 	'G:/Musics/1/漫长的告白-双笙-162283619.flac',
// 	// 	function (err: any, metadata: any) {
// 	// 		if (err) {
// 	// 			console.error(err);
// 	// 		}
// 	// 		console.dir(metadata);
// 	// 	}
// 	// );
// });
// electronAPI.getMusicInfo(
// 	'G:/Musics/1/漫长的告白-双笙-162283619.flac',
// 	(musicInfo: any) => {
// 		console.log(musicInfo);
// 	}
// );
</script>

<template>
	<h1>{{ msg }}</h1>
	<el-button>asd</el-button>
</template>

<style lang="scss" scoped></style>
