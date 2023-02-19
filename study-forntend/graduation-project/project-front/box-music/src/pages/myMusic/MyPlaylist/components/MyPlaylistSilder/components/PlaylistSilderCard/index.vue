<script setup lang="ts">
import { Playlist } from '@/networks/playlist';
import { usePlaylistStore } from '@/store/playlist';
import { ElMessageBox } from 'element-plus';

interface PropType {
	cardData: Playlist;
}
const props = withDefaults(defineProps<PropType>(), {
	cardData() {
		return {} as Playlist;
	}
});
const playlistStore = usePlaylistStore();
const deleteUserPlaylist = () => {
	ElMessageBox.confirm('确定要删除该歌单？', '提示', {
		confirmButtonText: '确定',
		cancelButtonText: '取消',
		type: 'warning'
	})
		.then(() => {
			playlistStore.deletePlaylistCollection([
				props.cardData.playlistId as string
			]);
		})
		.catch(e => e);
};
</script>
<template>
	<div :class="{ 'playlist-silder-card': true, active: false }">
		<div class="img"><img :src="cardData.playlistPic" alt="歌单封面" /></div>
		<div class="card-content">
			<div class="title">{{ cardData.playlistName }}</div>
			<div class="desc">{{ cardData.playlistDescription }}</div>
			<div class="count">歌曲数：{{ cardData.musics?.length }}</div>
		</div>
		<div class="options">
			<el-tooltip
				:show-after="500"
				:hide-after="0"
				:offset="0"
				:show-arrow="false"
				tabindex="-1"
				effect="light"
				content="删除"
				placement="bottom">
				<i-material-symbols:delete-rounded
					class="delete-option"
					@click.stop="deleteUserPlaylist" />
			</el-tooltip>
		</div>
	</div>
</template>
<style lang="scss" scoped>
.playlist-silder-card {
	display: flex;
	justify-content: space-between;
	align-items: center;
	position: relative;
	box-sizing: border-box;
	margin: 10px;
	padding: 10px;
	height: 110px;
	cursor: pointer;
	border-radius: 5px;
	box-shadow: 1px 1px 5px rgba($color: #000000, $alpha: 0.1);
	.img {
		height: 85px;
		width: 85px;
		img {
			width: 85px;
			height: 85px;
			border-radius: 5px;
		}
	}
	.card-content {
		display: flex;
		flex-direction: column;
		justify-content: space-evenly;
		margin-left: 10px;
		width: calc(100% - 90px);
		height: 80px;
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
		.count {
			font-size: 12px;
			color: rgb(106, 106, 106);
		}
	}
	.options {
		display: none;
		align-items: center;
		justify-content: space-around;
		position: absolute;
		right: 3px;
		top: 3px;
		padding: 0 10px;
		border-radius: 50px;
		background-color: #eaeaea;
		.edit-option {
			color: #575757;
		}
		.delete-option {
			color: rgb(225, 0, 0);
		}
	}
}
.playlist-silder-card:hover {
	.options {
		display: flex;
	}
}
.active {
	background-color: rgb(187, 229, 255);
}
</style>
