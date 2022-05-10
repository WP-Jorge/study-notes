<template>
	<div class="about">
		<h1>This is an about page</h1>
	</div>
</template>
<script setup lang="ts">
const ffmpegPath = require('ffmpeg-static-electron');
const ffprobePath = require('ffprobe-static-electron');
const ffmpeg = require('fluent-ffmpeg');

// asar打包后路径有所变化
// if (process.env.NODE_ENV !== 'development') {
// 	ffmpeg.setFfmpegPath(ffmpegPath.path.replace('app.asar', 'app.asar.unpacked'));
// 	ffmpeg.setFfprobePath(ffprobePath.path.replace('app.asar', 'app.asar.unpacked'));
// } else {
// 	ffmpeg.setFfmpegPath(ffmpegPath.path);
// 	ffmpeg.setFfprobePath(ffprobePath.path);
// }
ffmpeg.setFfmpegPath(ffmpegPath.path);
ffmpeg.setFfprobePath(ffprobePath.path);
ffmpeg.ffprobe('D:/音乐/漫长的告白-双笙-162283619.flac', function (err, metadata) {
	console.log(err);
	console.dir(metadata);
});
</script>
