<script setup lang="ts">
import { Music } from '@/networks/music';
import { useContextMenuStore } from '@/store/contextMenu';
import { useDownloadStore } from '@/store/download';
const menuStore = useContextMenuStore();
const downloadStore = useDownloadStore();

const startAll = () => {
	downloadStore.startAll();
};
const pauseAll = () => {
	downloadStore.pauseAll();
};
const cancelAll = () => {
	downloadStore.cancelAll();
};

const pauseOne = (music: Music) => {
	downloadStore.pauseOne(music);
};

const resumeOne = (music: Music) => {
	downloadStore.resumeOne(music);
};

const cancelOne = (music: Music) => {
	downloadStore.cancelOne(music);
};

const openContextMenu = (row: Music, cloumn: any, e: PointerEvent) => {
	const contextMenuList = [
		{
			type: 'li',
			title: '继续下载',
			disabled: row.downloadItemInfo?.state === 'progressing',
			callback: () => resumeOne(row)
		},
		{
			type: 'li',
			title: '暂停',
			disabled: row.downloadItemInfo?.state === 'paused',
			callback: () => pauseOne(row)
		},
		{
			type: 'li',
			title: '从下载列表中移除',
			callback: () => cancelOne(row)
		}
	];
	menuStore.openContextMenu({ event: e, args: row, contextMenuList });
};
</script>
<template>
	<div class="downloading">
		<el-button @click="startAll">全部开始</el-button>
		<el-button @click="pauseAll">全部暂停</el-button>
		<el-button @click="cancelAll">全部取消</el-button>
		<el-button @click="downloadStore.clearStore">清除Store</el-button>
		<SimpleContainer title="正在下载">
			<template #content>
				<el-table
					:data="[
						...downloadStore.downloadQueue
						// ...downloadStore.pauseQueue
						// ...downloadStore.waitQueue
					]"
					@row-contextmenu="openContextMenu">
					<el-table-column #default="{ row: music }" label="封面" :width="80">
						<div class="music-img">
							<img :src="music.album.albumPic" :alt="music.musicTitle" />
						</div>
					</el-table-column>
					<el-table-column
						#default="{ row: music }"
						label="音乐标题"
						:width="220">
						<div class="music-title ellipse">
							{{ music.musicTitle }}
						</div>
					</el-table-column>
					<el-table-column #default="{ row: music }" label="进度">
						<DownloadProgress :downloadMusic="music" />
					</el-table-column>
				</el-table>
			</template>
		</SimpleContainer>
	</div>
</template>
<style lang="scss" scoped>
.downloading {
	.el-table {
		.music-img {
			display: flex;
			justify-content: space-between;
			align-items: center;
			height: 100%;
			img {
				width: 50px;
				height: 50px;
				border-radius: 5px;
			}
		}
	}
}
</style>
