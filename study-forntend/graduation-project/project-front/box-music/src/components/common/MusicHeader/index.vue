<script setup lang="ts">
export interface ShowOptions {
	showImg: boolean;
	showTitle: boolean;
	showSingers: boolean;
	showAlbum: boolean;
	showLevel: boolean;
	showCreatedTime: boolean;
	showTime: boolean;
}
import { PropType } from 'vue';
import { Music } from '@/networks/music';
import { getFormatTime } from '@/utils/mathUtil';
defineProps({
	music: {
		type: Object as PropType<Music>,
		required: true
	},
	showOptions: {
		type: Object as PropType<ShowOptions>,
		retuired: false,
		default: () => {
			return {
				showImg: true,
				showTitle: true,
				showSingers: true,
				showAlbum: true,
				showLevel: true,
				showCreatedTime: true,
				showTime: true
			};
		}
	}
});
</script>
<template>
	<div class="music-header">
		<div class="card-content">
			<div class="image">
				<div v-if="showOptions.showImg">音乐图片</div>
				<div v-if="showOptions.showTitle" class="title ellipse">音乐标题</div>
			</div>
			<div v-if="showOptions.showSingers" class="singers ellipse">音乐歌手</div>
			<div v-if="showOptions.showAlbum" class="album ellipse">
				{{ music.album.albumName }}
			</div>
			<div v-if="showOptions.showLevel" class="level ellipse">
				{{ music.level }}
			</div>
			<div v-if="showOptions.showCreatedTime" class="create-time ellipse">
				{{
					new Date(music.createTime).toLocaleDateString().replaceAll('/', '-')
				}}
			</div>
			<div v-if="showOptions.showTime" class="time ellipse">
				{{ getFormatTime(music.duration) }}
			</div>
		</div>
	</div>
</template>
<style lang="scss" scoped>
.music-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	height: 80px;
	padding: 10px;
	box-sizing: border-box;
	border-radius: 5px;
	&:nth-child(2n + 1) {
		background-color: var(--el-bg-color-page);
	}
	&:hover {
		background-color: var(--el-color-info-light-8);
	}
	.card-content {
		display: flex;
		justify-content: space-between;
		align-items: center;
		height: 100%;
		width: 100%;
		font-size: 14px;
		.image {
			display: flex;
			justify-content: space-between;
			align-items: center;
			height: 100%;
			width: 300px;
			img {
				width: 50px;
				height: 50px;
				border-radius: 5px;
			}
			.title {
				margin-left: 10px;
				width: 220px;
				line-height: 60px;
			}
		}

		.singers {
			width: 200px;
		}
		.album {
			width: 200px;
		}
		.level {
			width: 80px;
		}
		.create-time {
			width: 150px;
		}
		.time {
			width: 80px;
		}
	}
}
</style>
