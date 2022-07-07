<script setup lang="ts">
import { getAssetsFileUrl } from '@/utils/fileUtil';

const props = defineProps({
	picUrl: {
		type: String,
		default: getAssetsFileUrl('images', '奉君书-481895137.jpg')
	},
	title: {
		type: String,
		default: '标题'
	},
	content: {
		type: String,
		default: '内容'
	}
});
</script>
<template>
	<div
		class="carousel-item"
		:style="{ backgroundImage: `url('${props.picUrl}')` }">
		<div class="carousel-container">
			<div class="left-container">
				<div class="title">{{ props.title }}</div>
				<div class="content">
					{{ props.content }}
				</div>
			</div>
			<div class="right-container">
				<el-image :src="props.picUrl" />
			</div>
		</div>
	</div>
</template>
<style lang="scss" scoped>
.carousel-item {
	height: 100%;
	width: 100%;
	border-radius: 10px;
	background-repeat: no-repeat;
	background-size: 100% 100%;
	background-color: white;
	.carousel-container {
		display: flex;
		justify-content: space-between;
		box-sizing: border-box;
		padding: 20px;
		width: 100%;
		height: 100%;
		backdrop-filter: blur(20px);
		border-radius: 5px;
		// background-color: var(--el-text-color);

		.left-container {
			width: 55%;
			display: flex;
			flex-direction: column;
			justify-content: space-around;
			.title {
				font-family: newFont;
				font-size: 28px;
				font-weight: bolder;
				color: var(--el-text-color-primary);
				// -webkit-text-stroke: 0.1px rgb(255, 255, 255);
				text-shadow: 1px 1px 0 rgba($color: white, $alpha: 1),
					-1px -1px 0 rgba($color: white, $alpha: 1),
					-1px 1px 0 rgba($color: white, $alpha: 1),
					1px -1px 0 rgba($color: white, $alpha: 1);
			}
			.content {
				font-family: newFont;
				width: 100%;
				// color: var(--el-text-color-regular);
				color: white;
				overflow: hidden;
				display: -webkit-box;
				-webkit-box-orient: vertical;
				-webkit-line-clamp: 2;
				font-size: 16px;
				text-shadow: 1px 1px 0 rgba($color: black, $alpha: 1),
					-1px -1px 0 rgba($color: black, $alpha: 1),
					-1px 1px 0 rgba($color: black, $alpha: 1),
					1px -1px 0 rgba($color: black, $alpha: 1);
			}
		}
		.right-container {
			width: 45%;
			display: flex;
			justify-content: center;
			align-items: center;
			.el-image {
				height: 150px;
				width: 150px;
				border-radius: 5px;
			}
		}
	}
}
</style>
