<script setup lang="ts">
import { getAssetsFileUrl } from '@/utils/fileUtil';

defineProps({
	picUrl: {
		type: String,
		default: getAssetsFileUrl('images', '奉君书-481895137.jpg')
	},
	title: {
		type: String,
		default: '标题'
	},
	width: {
		type: Number,
		default: 220
	},
	height: {
		type: Number,
		default: 270
	}
});
</script>
<template>
	<el-card class="card" shadow="never">
		<div class="top-container">
			<img :src="picUrl" class="image" loading="lazy" />
			<i-ic-round-play-arrow class="play-icon" />
		</div>
		<div class="bottom-container">
			<p class="title">{{ title }}</p>
		</div>
	</el-card>
</template>
<style lang="scss" scoped>
.card {
	width: 220px;
	height: 270px;
	box-sizing: border-box;
	border: 0;
	cursor: pointer;

	:deep(.el-card__body) {
		height: 100%;
		width: 100%;
		box-sizing: border-box;
		padding: 10px;
		.top-container {
			position: relative;
			height: 200px;
			.image {
				height: 100%;
				width: 100%;
				border-radius: 5px;
			}
			.play-icon {
				position: absolute;
				right: 10px;
				bottom: 10px;
				width: 30px;
				height: 30px;
				font-size: 28px;
				color: transparent;
				border-radius: 100%;
				transition: all 0.5s;
				background-color: transparent;
			}
			&:hover .play-icon {
				color: red;
				background-color: #fff;
			}
		}
		.bottom-container {
			.title {
				margin-top: 10px;
				overflow: hidden;
				display: -webkit-box;
				-webkit-box-orient: vertical;
				-webkit-line-clamp: 2;
				text-align: center;
			}
		}
	}
}
</style>
