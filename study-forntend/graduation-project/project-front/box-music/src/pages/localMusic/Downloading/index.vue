<script setup lang="ts">
import { useContextMenu } from '@/components/common/ContextMenu/hooks/useContextMenu';
import { Music } from '@/networks/music';
import { useDownloadStore } from '@/store/download';
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

const contextMneu = useContextMenu({
	resumeOne: true,
	pauseOne: true,
	cancelOne: true
});

const open = (row: Music, cloumn: any, e: PointerEvent) => {
	contextMneu.openContextMenu(e, row);
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
					:data="[...downloadStore.downloadQueue]"
					@row-contextmenu="open">
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
