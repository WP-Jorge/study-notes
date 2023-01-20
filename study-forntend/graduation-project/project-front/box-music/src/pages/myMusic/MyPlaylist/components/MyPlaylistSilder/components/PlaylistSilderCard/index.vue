<script setup lang="ts">
import { Playlist } from '@/networks/playlist';

interface PropType {
	cardData: Playlist;
}
withDefaults(defineProps<PropType>(), {
	cardData() {
		return {} as Playlist;
	}
});
</script>
<template>
	<div :class="{ 'playlist-silder-card': true, active: false }">
		<div class="img"><img :src="cardData.playlistPic" alt="歌单封面" /></div>
		<div class="card-content">
			<div class="title">{{ cardData.playlistName }}</div>
			<div class="desc">{{ cardData.playlistDescription }}</div>
		</div>
	</div>
</template>
<style lang="scss" scoped>
.playlist-silder-card {
	display: flex;
	justify-content: space-between;
	align-items: center;
	box-sizing: border-box;
	margin: 10px;
	padding: 10px;
	height: 100px;
	cursor: pointer;
	border-radius: 5px;
	box-shadow: 1px 1px 5px rgba($color: #000000, $alpha: 0.1);
	.img {
		height: 80px;
		width: 80px;
		img {
			width: 80px;
			height: 80px;
			border-radius: 5px;
		}
	}
	.card-content {
		display: flex;
		flex-direction: column;
		justify-content: space-evenly;
		margin-left: 10px;
		width: calc(100% - 90px);
		height: 70px;
		.title {
			width: 100%;
			font-size: 14px;
			font-weight: 500;
			overflow: hidden;
			white-space: nowrap;
			text-overflow: ellipsis;
		}
		.desc {
			font-size: 12px;
			color: rgb(106, 106, 106);
			overflow: hidden;
			text-overflow: ellipsis;
			display: -webkit-box;
			-webkit-line-clamp: 2;
			-webkit-box-orient: vertical;
		}
	}
}
.active {
	background-color: rgb(187, 229, 255);
}
</style>
