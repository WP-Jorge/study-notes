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
	<div :class="{ 'pure-playlist-silder-card': true, active: false }">
		<div class="card-content">
			<div class="title">{{ cardData.playlistName }}1111111111111</div>
			<div class="count">歌曲数：{{ 100 }}</div>
		</div>
	</div>
</template>
<style lang="scss" scoped>
.pure-playlist-silder-card {
	display: flex;
	justify-content: space-between;
	align-items: center;
	box-sizing: border-box;
	margin: 10px;
	padding: 10px;
	height: 70px;
	cursor: pointer;
	border-radius: 5px;
	box-shadow: 1px 1px 5px rgba($color: #000000, $alpha: 0.1);
	.card-content {
		display: flex;
		flex-direction: column;
		justify-content: space-evenly;
		// box-sizing: border-box;
		height: 50px;
		width: 100%;
		.title {
			// width: 270px;
			font-size: 14px;
			font-weight: 500;
			overflow: hidden;
			white-space: nowrap;
			text-overflow: ellipsis;
		}
		.count {
			font-size: 12px;
			color: rgb(106, 106, 106);
		}
	}
}
.active {
	background-color: rgb(187, 229, 255);
}
</style>
