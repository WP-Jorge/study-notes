<script setup lang="ts">
import { GlobalValues, PlayTypes } from '@/globals/GlobalValues';
import { useMusicStore } from '@/store/music';
import { debounce } from '@/utils/baseUtil';
import { getFormatTime } from '@/utils/mathUtil';
import { useNextHandle } from './useNextHandle';
import { useIsPlayOne } from './useIsPlayOne';
import { usePlayOrderChange } from './usePlayOrderChange';

const musicStore = useMusicStore();
const nextHandle = useNextHandle();
const isPlayOne = useIsPlayOne();
const playOrderChange = usePlayOrderChange();
const isPlayRecycle = computed(
	() =>
		PlayTypes[musicStore.playMusic.playTypeIndex] === GlobalValues.PLAY_RECYCLE
);
const isPlayRandom = computed(
	() =>
		PlayTypes[musicStore.playMusic.playTypeIndex] === GlobalValues.PLAY_RANDOM
);
const playTypeChange = () => {
	let playTypeIndex =
		(musicStore.playMusic.playTypeIndex + 1) % PlayTypes.length;
	localStorage.setItem('playTypeIndex', JSON.stringify(playTypeIndex));
	musicStore.playMusic.playTypeIndex = playTypeIndex;
	playOrderChange();
};
const playHandle = () => {
	(musicStore.playMusic.audio as unknown as HTMLAudioElement).muted =
		musicStore.playMusic.muted;
	(musicStore.playMusic.audio as unknown as HTMLAudioElement).volume =
		musicStore.playMusic.volume;
	musicStore.playMusic.play = !musicStore.playMusic.play;
	musicStore.playMusic.play
		? (musicStore.playMusic.audio as unknown as HTMLAudioElement).play()
		: (musicStore.playMusic.audio as unknown as HTMLAudioElement).pause();
};

const preHandle = () => {
	musicStore.playMusic.shouldChangeMusic = true;
	const tempMusic =
		musicStore.musicList[
			musicStore.playMusic.playOrders[musicStore.playMusic.currentIndex]
		];
	musicStore.playMusic.play = true;
	musicStore.playMusic.currentIndex =
		musicStore.playMusic.currentIndex <= 0
			? musicStore.musicList.length - 1
			: (musicStore.playMusic.currentIndex - 1) % musicStore.musicList.length;
	if (
		musicStore.musicList.length > 1 &&
		tempMusic.musicId ===
			musicStore.musicList[
				musicStore.playMusic.playOrders[musicStore.playMusic.currentIndex]
			].musicId
	) {
		preHandle();
		return;
	}
	nextTick(() =>
		(musicStore.playMusic.audio as unknown as HTMLAudioElement).play()
	);
};

const progressChange = debounce(() => {
	musicStore.playMusic.currentTime =
		musicStore.playMusic.music.duration * musicStore.playMusic.progress;
	(musicStore.playMusic.audio as unknown as HTMLAudioElement).currentTime =
		musicStore.playMusic.currentTime;
	musicStore.playMusic.isDragging = false;
}, 20);

const initMusicBar = () => {
	(musicStore.playMusic.audio as unknown as HTMLAudioElement).volume =
		musicStore.playMusic.volume;
};

onMounted(() => {
	initMusicBar();
});

watchEffect(() => {
	if (musicStore.playMusic.shouldChangeMusic) {
		musicStore.setMusic(
			musicStore.musicList[
				musicStore.playMusic.playOrders[musicStore.playMusic.currentIndex]
			]
		);
	}
});
onMounted(() => {
	watchEffect(() => {
		musicStore.playMusic.play
			? musicStore.playMusic.audio.play()
			: musicStore.playMusic.audio.pause();
	});
});
</script>
<template>
	<div class="music-bar-center">
		<div class="center-top">
			<el-tooltip
				v-if="isPlayRecycle"
				:show-after="500"
				:hide-after="0"
				:offset="0"
				:show-arrow="false"
				tabindex="-1"
				effect="light"
				content="循环列表"
				placement="bottom">
				<i-ic-round-repeat
					class="item small"
					tabindex="-1"
					@click="playTypeChange" />
			</el-tooltip>
			<el-tooltip
				v-if="isPlayOne"
				:show-after="500"
				:hide-after="0"
				:offset="0"
				:show-arrow="false"
				tabindex="-1"
				effect="light"
				content="单曲循环"
				placement="bottom">
				<i-ic-round-repeat-one
					class="item small"
					tabindex="-1"
					@click="playTypeChange" />
			</el-tooltip>
			<el-tooltip
				v-if="isPlayRandom"
				:show-after="500"
				:hide-after="0"
				:offset="0"
				:show-arrow="false"
				tabindex="-1"
				effect="light"
				content="随机播放"
				placement="bottom">
				<i-la-random class="item small" tabindex="-1" @click="playTypeChange" />
			</el-tooltip>
			<el-tooltip
				:show-after="500"
				:hide-after="0"
				:offset="0"
				:show-arrow="false"
				tabindex="-1"
				effect="light"
				content="上一曲"
				placement="bottom">
				<i-ic-round-skip-previous
					class="item"
					tabindex="-1"
					@click="preHandle" />
			</el-tooltip>
			<el-tooltip
				v-if="musicStore.playMusic.play"
				:show-after="500"
				:hide-after="0"
				:offset="0"
				:show-arrow="false"
				content="暂停"
				tabindex="-1"
				effect="light"
				placement="bottom">
				<i-ic-round-pause class="item" tabindex="-1" @click="playHandle" />
			</el-tooltip>
			<el-tooltip
				v-if="!musicStore.playMusic.play"
				:show-after="500"
				:hide-after="0"
				:offset="0"
				:show-arrow="false"
				content="播放"
				tabindex="-1"
				effect="light"
				placement="bottom">
				<i-ic-round-play-arrow class="item" tabindex="-1" @click="playHandle" />
			</el-tooltip>
			<el-tooltip
				:show-after="500"
				:hide-after="0"
				:offset="0"
				:show-arrow="false"
				effect="light"
				content="下一曲"
				placement="bottom">
				<i-ic-round-skip-next class="item" tabindex="-1" @click="nextHandle" />
			</el-tooltip>
		</div>
		<div class="center-bottom">
			<div v-show="true" class="slider-container">
				<span class="current-time">
					{{ getFormatTime(musicStore.playMusic.currentTime) }}
				</span>
				<el-slider
					v-model="musicStore.playMusic.progress"
					:max="1"
					:step="0.01"
					size="small"
					:show-tooltip="false"
					tabindex="-1"
					@mousedown="musicStore.playMusic.isDragging = true"
					@mouseup="progressChange"
					@change="progressChange" />
				<span class="total-time">
					{{ getFormatTime(musicStore.playMusic.music.duration as number) }}
				</span>
			</div>
		</div>
	</div>
</template>
<style lang="scss" scoped>
.music-bar-center {
	width: 500px;
	.center-top {
		display: flex;
		justify-content: center;
		align-items: center;
		.item {
			font-size: 24px;
			margin: 0 10px;
			cursor: pointer;
		}
		.small {
			font-size: 14px;
		}
	}
	.center-bottom {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		height: 30px;
		.el-progress {
			width: 500px;
		}
		.progress-container {
			display: flex;
			align-items: center;
			width: 500px;
		}
		.slider-container {
			display: flex;
			align-items: center;
			width: 500px;
			.el-slider {
				width: 445px;
				height: 6px;
				:deep(.el-slider__button-wrapper) {
					display: none;
				}
			}
		}
		.slider-container:hover :deep(.el-slider__button-wrapper) {
			display: block;
		}
		.current-time {
			margin-right: 12px;
			color: var(--el-text-color-regular);
			font-size: 14.4px;
		}
		.total-time {
			margin-left: 12px;
			color: var(--el-text-color-regular);
			font-size: 14.4px;
		}
	}
}
</style>
