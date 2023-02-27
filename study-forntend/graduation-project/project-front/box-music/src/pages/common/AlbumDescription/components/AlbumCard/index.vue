<script setup lang="ts">
import { Album } from '@/networks/album';
import { Music } from '@/networks/music';
interface Props {
	albumDes: Album;
	musics: Music[];
}
defineProps<Props>();
</script>
<template>
	<div class="album-card">
		<div class="album-img">
			<el-image style="width: 200px; height: 200px" :src="albumDes.albumPic" />
		</div>
		<div class="album-des">
			<div class="album-des-top">
				<h2 class="album-name">{{ albumDes.albumName }}</h2>
				<span class="album-music-count">歌曲数：{{ musics?.length }}</span>
			</div>
			<p class="album-description" :title="albumDes.albumDescription">
				专辑简介：
				<br />
				{{ albumDes.albumDescription }}
			</p>
		</div>
	</div>
</template>
<style lang="scss" scoped>
.album-card {
	display: flex;
	.album-img {
		.el-image {
			border-radius: 5px;
		}
	}
	.album-des {
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		margin-left: 20px;
		.album-music-count,
		.album-description {
			margin-top: 20px;
			color: #949494;
			font-size: 12px;
		}
		.album-description {
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
