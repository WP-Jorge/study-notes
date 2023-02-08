<script setup lang="ts">
import { useContextMenu } from '@/components/common/ContextMenu/hooks/useContextMenu';
import { Music } from '@/networks/music';
import { getFormatTime } from '@/utils/mathUtil';
interface PropType {
	tableData: Music[];
}
withDefaults(defineProps<PropType>(), {
	tableData() {
		return [];
	}
});

const contextMenu = useContextMenu({
	downloadOne: true,
	playMusic: true,
	addMusicToPlaylist: true,
	addCollection: true,
	deleteCollection: true
});
const open = (row: Music, cloumn: any, e: PointerEvent) => {
	contextMenu.openContextMenu(e, row);
};
</script>
<template>
	<el-table
		class="my-playlist-table"
		:data="tableData"
		@row-contextmenu="open"
		@row-dblclick="contextMenu.menuFunctions.playMusic">
		<el-table-column #default="{ row: music }" label="封面" :width="80">
			<div class="music-img">
				<img :src="music.album?.albumPic" :alt="music.musicTitle" />
			</div>
		</el-table-column>
		<el-table-column #default="{ row: music }" label="音乐标题" :width="220">
			<div class="music-title ellipse">
				{{ music.musicTitle }}
			</div>
		</el-table-column>
		<el-table-column #default="{ row: music }" label="歌手">
			<div class="ellipse">
				{{ (music as Music).singers?.map(singer => singer.singerName).join(' / ') }}
			</div>
		</el-table-column>
		<el-table-column #default="{ row: music }" label="专辑">
			<div class="ellipse">
				{{ music.album.albumName }}
			</div>
		</el-table-column>
		<el-table-column #default="{ row: music }" label="时长">
			<div class="ellipse">
				{{ getFormatTime(music.duration) }}
			</div>
		</el-table-column>
	</el-table>
</template>
<style lang="scss" scoped>
.my-playlist-table {
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
</style>
