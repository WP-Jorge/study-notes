<script setup lang="ts">
import { useContextMenu } from '@/components/common/ContextMenu/hooks/useContextMenu';
import { Music } from '@/networks/music';
import { Playlist } from '@/networks/playlist';
import { usePlaylistStore } from '@/store/playlist';
import { getFormatTime } from '@/utils/mathUtil';
interface PropType {
	playlist: Playlist;
}
withDefaults(defineProps<PropType>(), {
	playlist() {
		return {} as Playlist;
	}
});
const playlistStore = usePlaylistStore();
let contextMenu: any;
watch(
	[() => playlistStore.isMyCreated],
	() => {
		contextMenu = useContextMenu({
			downloadOne: true,
			playMusic: true,
			addMusicToPlaylist: true,
			addCollection: true,
			deleteCollection: true,
			deleteMusicFromPlaylist: playlistStore.isMyCreated
		});
	},
	{
		immediate: true
	}
);
const open = (row: Music, cloumn: any, e: PointerEvent) => {
	contextMenu.openContextMenu(e, row, playlistStore.currentPlaylist);
};
</script>
<template>
	<el-table
		class="my-playlist-table"
		:data="playlist.musics"
		@row-contextmenu="open"
		@row-dblclick="contextMenu?.menuFunctions.playMusic">
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
