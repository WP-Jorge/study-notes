<script setup lang="ts">
import { ResourceType } from '@/globals/GlobalValues';
import { ResponseType } from '@/globals/ResponseType';
import { getResourceUrl } from '@/utils/fileUtil';
import { Playlist } from '@/networks/playlist';
import { getPlaylistsByCategoryIdPageApi } from '@/networks/playlist';
import { usePlaylistStore } from '@/store/playlist';
import { getMusicsByPlaylistIdPageApi } from '@/networks/music';
import { useMusicStore } from '@/store/music';
import { useContextMenuStore } from '@/store/contextMenu';
const playlistStore = usePlaylistStore();
const musicStore = useMusicStore();
const menuStore = useContextMenuStore();
const playlists = ref([] as Playlist[]);
const pageData = ref({
	total: 0,
	pageSize: 10,
	currentPage: 1,
	totalPages: 0,
	nextBatchSize: 5
});

const getPlaylistsByCategoryIdPage = async (categoryId: string) => {
	const res = await getPlaylistsByCategoryIdPageApi(
		pageData.value.currentPage,
		pageData.value.pageSize,
		categoryId
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

// const { nextBatch, showNextBatch } = useNextBatch(
// 	() => getPlaylistsByCategoryIdPage(playlistStore.category.categoryId),
// 	pageData.value
// );
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

watch(
	() => playlistStore.category.categoryId,
	() => {
		pageData.value.currentPage = 1;
		playlistStore.category.categoryId &&
			getPlaylistsByCategoryIdPage(playlistStore.category.categoryId);
	},
	{
		immediate: true
	}
);
</script>
<template>
	<div class="category-playlist-card">
		<CardContainer
			:title="`【${playlistStore.category.categoryName}】歌单`"
			:pageData="pageData"
			:nextBatchCallback="
				() => getPlaylistsByCategoryIdPage(playlistStore.category.categoryId)
			">
			<template #content>
				<Card
					v-for="item of playlists"
					:key="item.playlistId"
					:picUrl="item.playlistPic"
					:title="item.playlistName"
					@contextmenu="(e: PointerEvent) => openContextMenu(e, item)"
					@click="playPlaylist(item)" />
			</template>
		</CardContainer>
	</div>
</template>
<style lang="scss" scoped></style>
