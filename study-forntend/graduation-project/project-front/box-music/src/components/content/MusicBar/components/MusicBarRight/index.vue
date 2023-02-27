<script setup lang="ts">
import { useContextMenu } from '@/components/common/ContextMenu/hooks/useContextMenu';
import { Music } from '@/networks/music';
import { useMusicStore } from '@/store/music';
import { getFormatTime } from '@/utils/mathUtil';
import { storeToRefs } from 'pinia';
const musicStore = useMusicStore();
const { musicList } = storeToRefs(musicStore);
const contextMenu = useContextMenu();

// const getMusicsByMusicTitlePage = async () => {
// 	let res = await getMusicsByMusicTitlePageApi(1, 200, '');
// 	console.log(res);
// 	if (res && res.data.type === ResponseType.SUCCESS) {
// 		musicStore.musicList = res.data.pageList;
// 	}
// };
// getMusicsByMusicTitlePage();

const volumeClick = (flag: boolean) => {
	musicStore.playMusic.muted = flag;
	(musicStore.playMusic.audio as unknown as HTMLAudioElement).muted =
		musicStore.playMusic.muted;
	localStorage.setItem('muted', JSON.stringify(flag));
	if (flag) {
		musicStore.playMusic.volume = 0;
		localStorage.setItem('volume', JSON.stringify(0));
		return;
	}
	musicStore.playMusic.volume = musicStore.playMusic.tempVolume;
	(musicStore.playMusic.audio as unknown as HTMLAudioElement).volume =
		musicStore.playMusic.tempVolume;
	localStorage.setItem(
		'volume',
		JSON.stringify(musicStore.playMusic.tempVolume)
	);
};

const volumeChange = (volume: number) => {
	musicStore.playMusic.volume = volume;
	localStorage.setItem('volume', JSON.stringify(volume));
	musicStore.playMusic.tempVolume = volume;
	localStorage.setItem('tempVolume', JSON.stringify(volume));
	(musicStore.playMusic.audio as unknown as HTMLAudioElement).volume = volume;
	musicStore.playMusic.muted = volume === 0;
	localStorage.setItem('muted', JSON.stringify(volume === 0));
};

// const playlistClick = (item: Music) => {
// 	musicStore.setMusic(item);
// 	// nextTick(() => {
// 	// 	(musicStore.audio as unknown as HTMLAudioElement).play();
// 	// 	musicStore.play = true;
// 	// });
// };

const clearPlaylist = () => {
	musicStore.setMusicList([]);
};
const deleteFromMusicList = (music: Music) => {
	musicStore.setMusicList(
		musicStore.musicList.filter(item => {
			return item.musicId !== music.musicId;
		})
	);
};
</script>
<template>
	<div class="music-bar-right">
		<el-popover
			:teleported="false"
			:width="37"
			popper-class="popover-volume"
			placement="top">
			<template #reference>
				<i-ic-round-volume-up
					v-if="!musicStore.playMusic.muted"
					tabindex="-1"
					class="item"
					@click="volumeClick(true)" />
				<i-ic-round-volume-off
					v-else
					tabindex="-1"
					class="item"
					@click="volumeClick(false)" />
			</template>
			<template #default>
				<el-slider
					v-model="musicStore.playMusic.volume"
					:max="1"
					:step="0.01"
					:show-tooltip="false"
					tabindex="-1"
					vertical
					height="80px"
					@input="volumeChange" />
			</template>
		</el-popover>
		<el-popover
			:teleported="false"
			:width="500"
			:offset="30"
			:hide-after="0"
			trigger="click"
			popper-class="popover-playlist"
			placement="top">
			<template #reference>
				<i-ic-round-format-list-bulleted class="item" tabindex="-1" />
			</template>
			<template #default>
				<el-card class="box-card">
					<template #header>
						<div class="card-header">
							<span>播放列表</span>
							<span class="clearPlatlist" @click="clearPlaylist">清空列表</span>
						</div>
					</template>
					<div
						v-for="item in musicList"
						:key="item.musicId"
						:class="{
							text: true,
							item: true,
							active: item.musicId === musicStore.playMusic.music.musicId
						}"
						@click="contextMenu.menuFunctions.playMusic(item)">
						<p class="music-title">
							{{ item.musicTitle }}
						</p>
						<p class="singers">
							{{ item.singers.map(item => item.singerName).join(' / ') }}
						</p>
						<p class="duration">
							{{ getFormatTime(item.duration as number) }}
						</p>
						<div class="options">
							<div class="delete" @click.stop="deleteFromMusicList(item)">
								移除
							</div>
						</div>
					</div>
				</el-card>
			</template>
		</el-popover>
	</div>
</template>
<style lang="scss" scoped>
.music-bar-right {
	display: flex;
	justify-content: space-around;
	align-items: center;
	width: 100px;

	.box-card {
		.card-header {
			display: flex;
			justify-content: space-between;
			align-items: center;
			.clearPlatlist {
				cursor: pointer;
			}
		}

		.active {
			background-color: rgba($color: #e4e4e4, $alpha: 0.5);
		}

		.text {
			font-size: 14px;
		}

		.item {
			display: flex;
			justify-content: space-between;
			position: relative;
			padding: 10px;
			border-radius: 5px;
			.music-title {
				width: 100px;
				overflow: hidden;
				text-overflow: ellipsis;
				white-space: nowrap;
			}
			.singers {
				width: 100px;
				overflow: hidden;
				text-overflow: ellipsis;
				white-space: nowrap;
			}
			.duration {
				width: 100px;
				overflow: hidden;
				text-overflow: ellipsis;
				white-space: nowrap;
			}
			.options {
				display: none;
				position: absolute;
				right: 10px;
				color: red;
			}
		}
		.item:hover {
			.options {
				display: block;
			}
		}

		.item:hover {
			background-color: rgba($color: #e4e4e4, $alpha: 0.3);
		}

		.box-card {
			width: 480px;
			height: 600px;
		}
	}

	:deep(.popover-volume) {
		padding: 20px 5px;
	}

	:deep(.popover-playlist) {
		padding: 0;
		.el-card__body {
			max-height: 500px;
			overflow-y: overlay;
			&::-webkit-scrollbar {
				width: 5px;
				height: 8px;
				background-color: var(--el-color-info-light-9);
			}
			&::-webkit-scrollbar-thumb {
				background-color: transparent;
			}
		}
		.el-card__body:hover {
			&::-webkit-scrollbar-thumb {
				background-color: var(--el-color-primary-light-7);
			}
		}
	}

	.item {
		cursor: pointer;
	}
}
</style>
