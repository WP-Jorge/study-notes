<script setup lang="ts">
export interface Lyric {
	id: string;
	timestamp: number;
	lyricRow: string;
}
import { useMusicStore } from '@/store/music';
import { getFormatTime, getTimestamp } from '@/utils/mathUtil';
import { storeToRefs } from 'pinia';
import { customAlphabet } from 'nanoid/non-secure';

const nanoid = customAlphabet('1234567890', 10);
const musicStore = useMusicStore();
const { music } = storeToRefs(musicStore);
const lyricList = ref([] as Lyric[]);
const currentIndex = ref(0);
const lyricHeight = 36;
const lyricContainer = ref(null);
const isMouseOver = ref(false);

const formatLyric = (lyric: string) => {
	let timeReg = /\[(.*)\](.*)/g;
	let row;
	const lyrics = [] as Lyric[];
	while ((row = timeReg.exec(lyric))) {
		let lyric = { id: '', timestamp: 0, lyricRow: '' } as Lyric;
		if (row[2]) {
			lyric.id = nanoid();
			lyric.timestamp = getTimestamp(row[1]);
			lyric.lyricRow = row[2];
			lyrics.push(lyric);
		}
	}
	lyricList.value = lyrics;
};

const lyricRowClick = (index: number) => {
	currentIndex.value = index;
	musicStore.currentTime = lyricList.value[index].timestamp;
	musicStore.audio.currentTime = musicStore.currentTime;
	musicStore.play = true;
};

// onMounted(() => {
// 	formatLyric(musicStore.music.lyric as string);
// });

watchEffect(() => {
	formatLyric(musicStore.music.lyric as string);
	currentIndex.value = 0;
});

watchEffect(() => {
	while (
		currentIndex.value < lyricList.value.length - 1 &&
		musicStore.currentTime > lyricList.value[currentIndex.value + 1].timestamp
	) {
		currentIndex.value++;
	}
	while (
		currentIndex.value > 0 &&
		musicStore.currentTime < lyricList.value[currentIndex.value - 1].timestamp
	) {
		currentIndex.value--;
	}
});

onMounted(() => {
	watchEffect(() => {
		while (
			currentIndex.value < lyricList.value.length - 1 &&
			musicStore.currentTime > lyricList.value[currentIndex.value + 1].timestamp
		) {
			currentIndex.value++;
		}
		while (
			currentIndex.value > 0 &&
			musicStore.currentTime < lyricList.value[currentIndex.value - 1].timestamp
		) {
			currentIndex.value--;
		}
		if (lyricContainer.value && !isMouseOver.value) {
			let timer = requestAnimationFrame(function fn() {
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
			<h1 class="title">{{ music.musicTitle }}</h1>
			<p class="singers">
				{{ music.singers.map(item => item.singerName).join(' / ') }}
			</p>
		</div>
		<div ref="lyricContainer" class="bottom-container">
			<div
				class="lyric-container"
				@mouseover="isMouseOver = true"
				@mouseleave="isMouseOver = false">
				<div
					v-for="(item, index) of lyricList"
					:key="item.id"
					:class="{ 'lyric-row': true, active: currentIndex === index }">
					<span class="time">{{ getFormatTime(item.timestamp) }}</span>
					<span class="lyric">{{ item.lyricRow }}</span>
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
				height: 36px;
				color: var(--el-text-color-secondary);

				.time {
					visibility: hidden;
					width: 50px;
					text-align: center;
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
				font-size: 18px;
				font-weight: 800;
				color: var(--el-text-color-primary);
			}
		}
	}
}
</style>
