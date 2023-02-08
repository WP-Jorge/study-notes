<script setup lang="ts">
import { useMusicStore } from '@/store/music';
import { useDownloadStore } from '@/store/download';
const musicStore = useMusicStore();
const downloadStore = useDownloadStore();
const isFavorite = computed(() => {
	return musicStore.favoriteMusics.find(
		item => item.musicId == musicStore.playMusic.music.musicId
	);
});
</script>
<template>
	<div class="music-pic">
		<div class="pic-container">
			<div :class="{ outer: true, 'pause-rotate': !musicStore.playMusic.play }">
				<div class="inner">
					<el-image
						v-if="musicStore.playMusic.music?.album?.albumPic"
						:src="musicStore.playMusic.music?.album?.albumPic" />
				</div>
			</div>
		</div>
		<div class="options-container">
			<div class="options">
				<el-tooltip
					:show-after="500"
					:hide-after="0"
					:offset="0"
					:show-arrow="false"
					tabindex="-1"
					effect="light"
					content="移除喜欢"
					placement="bottom">
					<i-ic-round-favorite
						v-show="isFavorite"
						class="item favorite"
						@click="() => musicStore.deleteCollection()" />
				</el-tooltip>
				<el-tooltip
					:show-after="500"
					:hide-after="0"
					:offset="0"
					:show-arrow="false"
					tabindex="-1"
					effect="light"
					content="添加喜欢"
					placement="bottom">
					<i-ic-round-favorite-border
						v-show="!isFavorite"
						class="item"
						@click="() => musicStore.addCollection()" />
				</el-tooltip>
				<el-tooltip
					:show-after="500"
					:hide-after="0"
					:offset="0"
					:show-arrow="false"
					tabindex="-1"
					effect="light"
					content="下载"
					placement="bottom">
					<i-ic-round-download
						:class="{
							item: true,
							hidden: !downloadStore.downloadable(musicStore.playMusic.music)
						}"
						@click="
							downloadStore.startDownloadOne(musicStore.playMusic.music)
						" />
				</el-tooltip>
				<el-tooltip
					:show-after="500"
					:hide-after="0"
					:offset="0"
					:show-arrow="false"
					tabindex="-1"
					effect="light"
					content="已下载"
					placement="bottom">
					<i-ic-round-download-done
						:class="{
							item: true,
							hidden: downloadStore.downloadable(musicStore.playMusic.music)
						}" />
				</el-tooltip>
			</div>
		</div>
	</div>
</template>
<style lang="scss" scoped>
.music-pic {
	display: flex;
	flex-direction: column;
	width: 100%;
	height: 100%;
	background-color: transparent;
	.pic-container {
		flex: 2;
		display: flex;
		justify-content: center;
		align-items: center;
		.outer {
			width: 300px;
			height: 300px;
			border: 10px solid rgba($color: #000000, $alpha: 0.2);
			border-radius: 100%;
			animation: rotate linear 40s infinite forwards;
			background-repeat: no-repeat;
			background-size: 300px;
			background-image: url('../../../../../assets/images/唱片.png');
			.inner {
				margin: 40px;
				width: 220px;
				height: 220px;
				border-radius: 100%;
				.el-image {
					width: 100%;
					height: 100%;
					border-radius: 100%;
				}
			}
		}
		.pause-rotate {
			animation-play-state: paused;
		}
		@keyframes rotate {
			from {
				transform: rotate(0deg);
			}
			to {
				transform: rotate(360deg);
			}
		}
	}
	.options-container {
		flex: 0.5;
		display: flex;
		justify-content: space-evenly;
		.options {
			.item {
				margin: 0 10px;
				font-size: 24px;
				cursor: pointer;
			}

			.favorite {
				color: rgba($color: #ff5f5f, $alpha: 1);
			}

			.hidden {
				display: none;
			}
		}
	}
}
</style>
