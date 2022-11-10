<script setup lang="ts">
import { Singer } from '@/networks/singer';
interface Props {
	singerDes: Singer;
}
defineProps<Props>();
</script>
<template>
	<div class="singer-card">
		<div class="singer-img">
			<el-image
				style="width: 200px; height: 200px"
				:src="singerDes.singerPic" />
		</div>
		<div class="singer-des">
			<div class="singer-des-top">
				<h2 class="singer-name">{{ singerDes.singerName }}</h2>
				<span class="singer-music-count">
					播放量：{{ singerDes.totalViews }}
				</span>
			</div>
			<p class="singer-description" :title="singerDes.singerDescription">
				歌手简介：
				<br />
				{{ singerDes.singerDescription }}
			</p>
		</div>
	</div>
</template>
<style lang="scss" scoped>
.singer-card {
	display: flex;
	.singer-img {
		.el-image {
			border-radius: 5px;
		}
	}
	.singer-des {
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		margin-left: 20px;
		.singer-music-count,
		.singer-description {
			margin-top: 20px;
			color: #949494;
			font-size: 12px;
		}
		.singer-description {
			min-height: 50px;
			line-height: 20px;
			display: -webkit-box;
			-webkit-box-orient: vertical;
			-webkit-line-clamp: 6;
			overflow: hidden;
			text-overflow: ellipsis;
			white-space: pre-line;
		}
	}
}
</style>
