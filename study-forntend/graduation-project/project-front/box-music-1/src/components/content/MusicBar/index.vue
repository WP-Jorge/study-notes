<script setup lang="ts">
import { getFormatTime } from '@/utils/mathUtil';
import { useMusicBar } from './useMusicBar';
import { useMusicStore } from '@/store/music';
import { storeToRefs } from 'pinia';
const musicStore = useMusicStore();
const { music, play, currentTime, audio, musicList } = storeToRefs(musicStore);
const {
	musicOptions,
	singers,
	isPlayRecycle,
	isPlayOne,
	isPlayRandom,
	isDragging,
	playHandle,
	preHandle,
	nextHandle,
	progressChange,
	playTypeChange,
	timeUpdate,
	ended,
	volumeChange,
	volumeClick,
	playlistClick,
	musicPicClick
} = useMusicBar();

const { muted, controls, volume, progress } = toRefs(musicOptions);
</script>
<template>
	<div class="music-bar">
		<audio
			v-show="false"
			ref="audio"
			:muted="muted"
			:loop="isPlayOne"
			:src="music.musicUrl"
			:controls="controls"
			@ended="ended"
			@timeupdate="timeUpdate" />
		<div class="container">
			<div class="music-left-container">
				<img :src="music.musicPic" alt="logo" @click="musicPicClick" />
				<div class="music-info">
					<p class="music-title">
						{{ music.musicTitle }}
					</p>
					<p class="music-singers">
						{{ singers }}
					</p>
				</div>
			</div>
			<div class="music-center-container">
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
						<i-la-random
							class="item small"
							tabindex="-1"
							@click="playTypeChange" />
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
						v-if="play"
						:show-after="500"
						:hide-after="0"
						:offset="0"
						:show-arrow="false"
						:content="!play ? '暂停' : '播放'"
						tabindex="-1"
						effect="light"
						placement="bottom">
						<i-ic-round-pause class="item" tabindex="-1" @click="playHandle" />
					</el-tooltip>
					<el-tooltip
						v-if="!play"
						:show-after="500"
						:hide-after="0"
						:offset="0"
						:show-arrow="false"
						:content="play ? '暂停' : '播放'"
						tabindex="-1"
						effect="light"
						placement="bottom">
						<i-ic-round-play-arrow
							class="item"
							tabindex="-1"
							@click="playHandle" />
					</el-tooltip>
					<el-tooltip
						:show-after="500"
						:hide-after="0"
						:offset="0"
						:show-arrow="false"
						effect="light"
						content="下一曲"
						placement="bottom">
						<i-ic-round-skip-next
							class="item"
							tabindex="-1"
							@click="nextHandle" />
					</el-tooltip>
				</div>
				<div class="center-bottom">
					<div v-show="true" class="slider-container">
						<span class="current-time">{{ getFormatTime(currentTime) }}</span>
						<el-slider
							v-model="progress"
							:max="1"
							:step="0.01"
							size="small"
							:show-tooltip="false"
							tabindex="-1"
							@mousedown="isDragging = true"
							@mouseup="progressChange"
							@change="progressChange" />
						<span class="total-time">
							{{ getFormatTime(music.duration as number) }}
						</span>
					</div>
				</div>
			</div>
			<div class="music-right-container">
				<el-popover
					:teleported="false"
					:width="37"
					popper-class="popover-volume"
					placement="top">
					<template #reference>
						<i-ic-round-volume-up
							v-if="!muted"
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
							v-model="volume"
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
								</div>
							</template>
							<div
								v-for="item in musicList"
								:key="item.musicId"
								:class="{
									text: true,
									item: true,
									active: item.musicId === music.musicId
								}"
								@click="playlistClick(item)">
								<p class="music-title">
									{{ item.musicTitle }}
								</p>
								<p class="singers">
									{{ item.singers.map(item => item.singerName).join(' / ') }}
								</p>
								<p class="duration">
									{{ getFormatTime(item.duration as number) }}
								</p>
							</div>
						</el-card>
					</template>
				</el-popover>
			</div>
		</div>
	</div>
</template>
<style lang="scss" scoped>
.music-bar {
	height: 100%;
	min-width: 930px;
	padding: 0 20px;
	.container {
		display: flex;
		justify-content: space-between;
		align-items: center;
		height: 100%;
		.music-left-container {
			display: flex;
			justify-content: space-between;
			align-items: center;
			width: 150px;
			height: 50px;
			img {
				height: 100%;
				cursor: pointer;
			}
			.music-info {
				width: 90px;
				font-size: 12px;
				.music-title {
					margin-bottom: 5px;
					font-size: 14px;
					overflow: hidden;
					text-overflow: ellipsis;
					white-space: nowrap;
				}
				.music-singers {
					color: var(--el-color-info);
					overflow: hidden;
					text-overflow: ellipsis;
					white-space: nowrap;
				}
			}
		}
		.music-center-container {
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
		.music-right-container {
			display: flex;
			justify-content: space-around;
			align-items: center;
			width: 100px;

			.box-card {
				.card-header {
					display: flex;
					justify-content: space-between;
					align-items: center;
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
					padding: 10px;
					border-radius: 5px;
					.music-title {
						flex: 1;
						min-width: 100px;
					}
					.singers {
						flex: 1;
						min-width: 100px;
					}
					.duration {
						flex: 1;
						min-width: 100px;
					}
				}

				.item:hover {
					background-color: rgba($color: #e4e4e4, $alpha: 0.3);
				}

				.box-card {
					width: 480px;
					max-height: 600px;
				}
			}

			:deep(.popover-volume) {
				padding: 20px 5px;
			}

			:deep(.popover-playlist) {
				padding: 0;
			}

			.item {
				cursor: pointer;
			}
		}
	}
}
</style>
