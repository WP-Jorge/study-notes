<script setup lang="ts">
import { Playlist } from '@/networks/playlist';
interface Props {
	playlistDes: Playlist;
}
defineProps<Props>();
</script>
<template>
	<div class="playlist-card">
		<div class="playlist-img">
			<el-image
				style="width: 200px; height: 200px"
				:src="playlistDes.playlistPic" />
		</div>
		<div class="playlist-des">
			<div class="playlist-des-top">
				<h2 class="playlist-name">{{ playlistDes.playlistName }}</h2>
				<span class="playlist-music-count">
					播放量：{{ playlistDes.totalViews }}
				</span>
			</div>
			<p class="playlist-description" :title="playlistDes.playlistDescription">
				歌单简介：
				<br />
				{{ playlistDes.playlistDescription }}
			</p>
		</div>
	</div>
</template>
<style lang="scss" scoped>
.playlist-card {
	display: flex;
	.playlist-img {
		.el-image {
			border-radius: 5px;
		}
	}
	.playlist-des {
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		margin-left: 20px;
		.playlist-music-count,
		.playlist-description {
			margin-top: 20px;
			color: #949494;
			font-size: 12px;
		}
		.playlist-description {
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
