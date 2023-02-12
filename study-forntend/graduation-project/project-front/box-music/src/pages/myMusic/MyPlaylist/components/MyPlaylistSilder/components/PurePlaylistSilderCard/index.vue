<script setup lang="ts">
import { ResponseType } from '@/globals/ResponseType';
import { usePlaylistStore } from '@/store/playlist';
import {
	deleteSimplePlaylistsByPlaylistIdsApi,
	Playlist,
	updateSimplePlaylistApi
} from '@/networks/playlist';
import { ElMessage, ElMessageBox } from 'element-plus';

interface PropType {
	cardData: Playlist;
}
const props = withDefaults(defineProps<PropType>(), {
	cardData() {
		return {} as Playlist;
	}
});
const playlistStore = usePlaylistStore();

const deleteSimplePlaylistsByPlaylistIds = () => {
	ElMessageBox.confirm('确定要删除该歌单？', '提示', {
		confirmButtonText: '确定',
		cancelButtonText: '取消',
		type: 'warning'
	})
		.then(async () => {
			const res = await deleteSimplePlaylistsByPlaylistIdsApi([
				props.cardData.playlistId as string
			]);
			console.log('🦃🦃res', res);
			if (res.data && res.data.type === ResponseType.SUCCESS) {
				ElMessage.success('删除成功');
				playlistStore.getSimplePlaylists();
			} else {
				ElMessage.error('删除失败');
			}
		})
		.catch(e => e);
};
const updateSimplePlaylist = () => {
	ElMessageBox.prompt('请输入歌单名称', '修改歌单', {
		confirmButtonText: '确定',
		cancelButtonText: '取消',
		inputValue: props.cardData.playlistName
	})
		.then(async ({ value }) => {
			const res = await updateSimplePlaylistApi({
				playlistName: value,
				playlistId: props.cardData.playlistId
			});
			console.log('🦃🦃res', res);
			if (res.data && res.data.type === ResponseType.SUCCESS) {
				ElMessage({
					type: 'success',
					message: `修改歌单成功`
				});
				playlistStore.getSimplePlaylists();
			} else {
				ElMessage.error('修改歌单失败');
			}
		})
		.catch(() => ({}));
};
</script>
<template>
	<div :class="{ 'pure-playlist-silder-card': true, active: false }">
		<div class="card-content">
			<div class="title">{{ cardData.playlistName }}</div>
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
				content="编辑"
				placement="bottom">
				<i-material-symbols:edit-rounded
					class="edit-option"
					@click="updateSimplePlaylist" />
			</el-tooltip>
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
					@click.stop="deleteSimplePlaylistsByPlaylistIds" />
			</el-tooltip>
		</div>
	</div>
</template>
<style lang="scss" scoped>
.pure-playlist-silder-card {
	display: flex;
	justify-content: space-between;
	align-items: center;
	position: relative;
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
.pure-playlist-silder-card:hover {
	.options {
		display: flex;
	}
}
.active {
	background-color: rgb(187, 229, 255);
}
</style>
