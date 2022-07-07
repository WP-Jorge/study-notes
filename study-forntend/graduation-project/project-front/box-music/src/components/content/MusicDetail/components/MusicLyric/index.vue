<script setup lang="ts">
export interface Lyric {
	id: string;
	timestamp: number;
	lyricRow: string;
}
import { useMusicStore } from '@/store/music';
import { getFormatTime, getTimestamp } from '@/utils/mathUtil';
import { customAlphabet } from 'nanoid/non-secure';

const nanoid = customAlphabet('1234567890', 10);
const musicStore = useMusicStore();
const lyricList = ref([] as Lyric[]);
const currentIndex = ref(0);
const lyricHeight = 45;
const lyricContainer = ref(null);
const isMouseOver = ref(false);
let timer: number;

const formatLyric = (lyric: string) => {
	let timeReg = /\[(.*)\](.*)/g;
	let row;
	const lyrics = [] as Lyric[];
	while ((row = timeReg.exec(lyric))) {
		let lyric = { id: '', timestamp: 0, lyricRow: '' } as Lyric;
		if (row[2]) {
			let formatTime = getTimestamp(row[1]);
			lyric.id = nanoid();
			lyric.timestamp =
				formatTime > musicStore.playMusic.music.duration
					? musicStore.playMusic.music.duration
					: formatTime;
			lyric.lyricRow = row[2];
			lyrics.push(lyric);
		}
	}
	lyricList.value = lyrics;
};

const lyricRowClick = (index: number) => {
	currentIndex.value = index;
	musicStore.playMusic.currentTime = lyricList.value[index].timestamp;
	musicStore.playMusic.audio.currentTime = musicStore.playMusic.currentTime;
	musicStore.playMusic.play = true;
};

// onMounted(() => {
// 	formatLyric(musicStore.playMusic.music.lyric as string);
// });

watchEffect(() => {
	formatLyric(musicStore.playMusic.music.lyric as string);
	currentIndex.value = 0;
});

// watchEffect(() => {
// 	while (
// 		currentIndex.value < lyricList.value.length - 1 &&
// 		musicStore.playMusic.currentTime > lyricList.value[currentIndex.value + 1].timestamp
// 	) {
// 		currentIndex.value++;
// 	}
// 	while (
// 		currentIndex.value > 0 &&
// 		musicStore.playMusic.currentTime < lyricList.value[currentIndex.value - 1].timestamp
// 	) {
// 		currentIndex.value--;
// 	}
// });

onMounted(() => {
	watchEffect(() => {
		cancelAnimationFrame(timer);
		if (lyricContainer.value && !isMouseOver.value) {
			while (
				currentIndex.value < lyricList.value.length - 1 &&
				musicStore.playMusic.currentTime >
					lyricList.value[currentIndex.value + 1].timestamp
			) {
				currentIndex.value++;
			}
			while (
				currentIndex.value > 0 &&
				musicStore.playMusic.currentTime <
					lyricList.value[currentIndex.value - 1].timestamp
			) {
				currentIndex.value--;
			}
			timer = requestAnimationFrame(function fn() {
				if (
					(lyricContainer.value as unknown as HTMLElement) &&
					(lyricContainer.value as unknown as HTMLElement).scrollTop !==
						currentIndex.value * lyricHeight
				) {
					let move = Math.ceil(
						(currentIndex.value * lyricHeight -
							(lyricContainer.value as unknown as HTMLElement).scrollTop) /
							12
					);
					(lyricContainer.value as unknown as HTMLElement).scrollTop += move;
					timer = requestAnimationFrame(fn);
				} else {
					cancelAnimationFrame(timer);
				}
			});
		}
	});
});
</script>
<template>
	<div class="music-lyric">
		<div class="top-container">
			<h1 class="title">{{ musicStore.playMusic.music?.musicTitle }}</h1>
			<p class="singers">
				<span>
					{{
						musicStore.playMusic.music?.singers
							?.map(item => item.singerName)
							.join(' / ')
					}}
				</span>
				<span>&nbsp;-&nbsp;</span>
				<span>{{ musicStore.playMusic.music?.album?.albumName }}</span>
			</p>
		</div>
		<div
			ref="lyricContainer"
			class="bottom-container"
			@mouseenter="isMouseOver = true"
			@mouseleave="isMouseOver = false">
			<div class="lyric-container">
				<div
					v-for="(item, index) of lyricList"
					:key="item.id"
					:class="{ 'lyric-row': true, active: currentIndex === index }">
					<span class="time">{{ getFormatTime(item.timestamp) }}</span>
					<span class="lyric ellipse2">{{ item.lyricRow }}</span>
					<span class="option">
						<el-tooltip
							effect="light"
							:show-after="500"
							:content="'跳转至' + getFormatTime(item.timestamp)"
							placement="bottom">
							<i-ic-round-play-arrow
								class="item"
								tabindex="-1"
								@click="lyricRowClick(index)" />
						</el-tooltip>
					</span>
				</div>
			</div>
		</div>
	</div>
</template>
<style lang="scss" scoped>
.music-lyric {
	height: 100%;
	padding: 20px;
	box-sizing: border-box;
	.top-container {
		height: 150px;
		display: flex;
		flex-direction: column;
		justify-content: center;
		.title {
			text-align: center;
			font-size: 30px;
			font-weight: 500;
		}
		.singers {
			margin-top: 10px;
			font-size: 16px;
			color: var(--el-text-color-secondary);
			text-align: center;
		}
	}
	.bottom-container::-webkit-scrollbar {
		width: 0;
	}
	.bottom-container {
		height: 100%;
		max-height: 366px;
		overflow: overlay;
		.lyric-container {
			text-align: center;
			padding: 165px 0;
			.lyric-row {
				display: flex;
				justify-content: space-between;
				align-items: center;
				height: 45px;
				color: var(--el-text-color-secondary);
				font-size: 13px;

				.time {
					visibility: hidden;
					width: 50px;
					text-align: center;
				}
				.lyric {
					width: 420px;
				}
				.option {
					visibility: hidden;
					width: 50px;
					.item {
						display: flex;
						align-items: center;
						justify-content: center;
						font-size: 20px;
						cursor: pointer;
					}
				}
			}
			.lyric-row:hover {
				.time,
				.option {
					visibility: visible;
				}
				.time {
					font-size: 16px;
					font-weight: normal;
				}
			}
			.active {
				font-size: 16px;
				font-weight: 800;
				color: var(--el-text-color-primary);
			}
		}
	}
}
</style>
