<script setup lang="ts">
import { useDownloadStore } from '@/store/download';
import { Music } from '@/networks/music';
import { getFormatTime } from '@/utils/mathUtil';
import { useContextMenu } from '@/components/common/ContextMenu/hooks/useContextMenu';
const downloadStore = useDownloadStore();

const clearHistroyList = () => {
	downloadStore.clearHistroyList();
};
const clearHistoryListAndComputerFiles = () => {
	downloadStore.clearHistoryListAndComputerFiles();
};

const contextMneu = useContextMenu({
	removeFromHistoryList: true,
	removeFromComputer: true,
	openfolder: true,
	playMusic: true,
	addMusicToPlaylist: true
});

const open = (row: Music, cloumn: any, e: PointerEvent) => {
	contextMneu.openContextMenu(e, row);
};
</script>
<template>
	<div class="finished">
		<el-button @click="clearHistroyList">清空列表</el-button>
		<el-button @click="clearHistoryListAndComputerFiles">
			清空列表并删除本地文件
		</el-button>
		<SimpleContainer title="下载完成">
			<template #content>
				<el-table
					:data="downloadStore.downloadHistoryList"
					@row-contextmenu="open"
					@row-dblclick="contextMneu.menuFunctions.playMusic">
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
					<el-table-column #default="{ row: music }" label="歌手">
						<div class="ellipse">
							{{ (music as Music).singers.map(singer => singer.singerName).join(' / ') }}
						</div>
					</el-table-column>
					<el-table-column #default="{ row: music }" label="专辑">
						<div class="ellipse">
							{{ music.album.albumName }}
						</div>
					</el-table-column>
					<el-table-column #default="{ row: music }" label="质量">
						<div class="ellipse">
							{{ music.level }}
						</div>
					</el-table-column>
					<el-table-column #default="{ row: music }" label="加入时间">
						<div class="ellipse">
							{{
								new Date(music.createTime)
									.toLocaleDateString()
									.replaceAll('/', '-')
							}}
						</div>
					</el-table-column>
					<el-table-column #default="{ row: music }" label="时长">
						<div class="ellipse">
							{{ getFormatTime(music.duration) }}
						</div>
					</el-table-column>
				</el-table>
			</template>
		</SimpleContainer>
	</div>
</template>
<style lang="scss" scoped>
.finished {
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
