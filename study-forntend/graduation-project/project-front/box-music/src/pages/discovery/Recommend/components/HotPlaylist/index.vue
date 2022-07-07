<script setup lang="ts">
import { ResponseType } from '@/globals/ResponseType';
import { ResourceType } from '@/globals/GlobalValues';
import {
	getPlaylistsByTotalViewsSortPageApi,
	Playlist
} from '@/networks/playlist';
import { getResourceUrl } from '@/utils/fileUtil';
import { useContextMenuStore } from '@/store/contextMenu';
import { getMusicsByPlaylistIdPageApi } from '@/networks/music';
import { useMusicStore } from '@/store/music';
const menuStore = useContextMenuStore();
const musicStore = useMusicStore();
const playlists = ref([] as Playlist[]);
const pageData = ref({
	total: 0,
	pageSize: 10,
	currentPage: 1,
	totalPages: 0,
	nextBatchSize: 5
});
const getPlaylistsByTotalViewsSortPage = async () => {
	let res = await getPlaylistsByTotalViewsSortPageApi(
		pageData.value.currentPage,
		pageData.value.pageSize
	);
	if (res && res.data.type === ResponseType.SUCCESS) {
		res.data.pageList.map((item: Playlist) => {
			item.playlistPic = getResourceUrl(
				item.playlistPic,
				ResourceType.PLAYLIST_PICTURE
			);
			return item;
		});
		playlists.value = res.data.pageList;
		pageData.value.totalPages = res.data.totalPages;
		pageData.value.total = res.data.total;
	}
};

const playPlaylist = async (playlist: Playlist) => {
	let res = await getMusicsByPlaylistIdPageApi(1, -1, playlist.playlistId);
	if (res && res.data.type === ResponseType.SUCCESS) {
		musicStore.setMusicList(res.data.pageList);
	}
};
const addToPlaylist = async (playlist: Playlist) => {
	let res = await getMusicsByPlaylistIdPageApi(1, -1, playlist.playlistId);
	if (res && res.data.type === ResponseType.SUCCESS) {
		musicStore.setMusicList(res.data.pageList, true);
	}
};
const openContextMenu = (e: PointerEvent, playlist: Playlist) => {
	const contextMenuList = [
		{
			type: 'li',
			title: '播放歌单',
			callback: () => playPlaylist(playlist)
		},
		{
			type: 'li',
			title: '添加至播放列表',
			callback: () => addToPlaylist(playlist)
		}
	];
	menuStore.openContextMenu({ event: e, args: playlist, contextMenuList });
};

getPlaylistsByTotalViewsSortPage();
</script>
<template>
	<div class="hot-playlist">
		<CardContainer
			title="今日歌单"
			:pageData="pageData"
			:nextBatchCallback="getPlaylistsByTotalViewsSortPage">
			<template #content>
				<Card
					v-for="item of playlists"
					:key="item.playlistId"
					:picUrl="item.playlistPic"
					:title="item.playlistDescription"
					@contextmenu="(e: PointerEvent) => openContextMenu(e, item)"
					@click="playPlaylist(item)" />
			</template>
		</CardContainer>
	</div>
</template>
<style lang="scss" scoped></style>
