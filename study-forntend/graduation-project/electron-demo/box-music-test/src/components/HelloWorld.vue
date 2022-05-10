<template>
	<div>Files - My Musics</div>
	请选择你的音乐文件夹：<input
		ref="dir"
		type="file"
		webkitdirectory
		directory
		@change="dirSelected"
	/>
	<template v-if="dirPath">
		<div v-for="(item, index) in fileMsg.fileNames" :key="index">
			<h3>{{ item?.split('-')[0] }}</h3>
			<audio :src="dirPath + item" controls />
		</div>
	</template>
</template>
<script setup lang="ts">
import { reactive, ref } from 'vue';
import { readFileNames } from '../utils/fileUtils';
const path = require('path');
const ffmpeg = require('fluent-ffmpeg');

ffmpeg.ffprobe(
	'G:/Musics/1/漫长的告白-双笙-162283619.flac',
	function (err: any, metadata: any) {
		if (err) {
			console.error(err);
		}
		console.dir(metadata);
	}
);
// console.log(ffmpeg.ffprobe);

// ffmpeg('http://m7.music.126.net/20220411145342/04b18e5355dbf3ea75bc7a000184c694/ymusic/8008/b4d5/68e0/d78760aa9f8ea730ef66b403cb51bd54.flac')
// 	// .output('G:/Musics/1/故梦（一周年礼物）-双笙2-13992750.mp3')
// 	.addOption('-metadata', `album=122`)
// 	.addOption('-metadata', `artist=31231`)
// 	.addOption('-metadata', `genre=fsdfs`)
// 	.addOption('-metadata', `title=1312asa`)
// 	.on('end', function () {
// 		console.log(1);
// 	})
// 	.save('G:/Musics/1/漫长的告白2-双笙-162283619.flac');
// .run();

// ffmpeg()
// 	.addInput('G:/Musics/1/故事外的人-1319371515.mp3')
// 	.addInput('G:/Musics/1/春衫年少-1401456544.jpg')
// 	.addOption('-map', '0:0')
// 	.addOption('-map', '1:0')
// 	.addOption('-c', 'copy')
// 	.addOption('-metadata', 'asd=aassdd')
// 	.on('end', function () {
// 		console.log(1);
// 	})
// 	.save('G:/Musics/1/故事外的人3-1319371515.mp3');

// G:\\box_music\\古风\\春三月-1426844352\\春三月-1426844352.flac
// ffmpeg.ffprobe(
// 	'G:/Musics/1/红昭愿-452986458.mp3',
// 	function (err: any, metadata: any) {
// 		// console.log(err);
// 		console.dir(metadata);
// 		// metadata.format.tags.lyric = 'asdasd';
// 		// metadata.format.tags.comment = 'llll';
// 		// console.dir(metadata);
// 	}
// );

// ffmpeg.ffprobe('D:/App/QQ/PersonalFiles/853539461/FileRecv/MobileFile/大鱼-周深-7095195(1).mp3', function (err: any, metadata: any) {
// 	// console.log(err);
// 	console.log('高品');
// 	console.dir(metadata);
// });
// ffmpeg.ffprobe('D:/App/QQ/PersonalFiles/853539461/FileRecv/MobileFile/大鱼-周深-7095195.mp3', function (err: any, metadata: any) {
// 	// console.log(err);
// 	console.log('超高品');
// 	console.dir(metadata);
// });
// ffmpeg.ffprobe(
// 	'D:/App/QQ/PersonalFiles/853539461/FileRecv/MobileFile/大鱼-周深-7095195.flac',
// 	function (err: any, metadata: any) {
// 		// console.log(err);
// 		console.log('无损');
// 		console.dir(metadata);
// 	}
// );
let dirPath = ref('');
let fileMsg = reactive({
	fileNames: [] as Array<string | null>
});
function dirSelected(e: any) {
	dirPath.value = path.dirname(e.target?.files[0].path) + '\\';
	console.log(dirPath.value);
	if (dirPath.value) {
		fileMsg.fileNames = reactive(readFileNames(dirPath.value));
	}
}
</script>
<style lang="scss" scoped></style>
