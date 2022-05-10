<script setup lang="ts">
import { ElMessage } from 'element-plus';
import { ref } from 'vue';
ElMessage.error('asd');
defineProps<{ msg: string }>();

const count = ref(0);
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
</script>

<template>
	<h1>{{ msg }}</h1>
	<el-button>asd</el-button>
</template>

<style lang="scss" scoped></style>
