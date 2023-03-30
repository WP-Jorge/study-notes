<script setup lang="ts">
import { ResponseType } from '@/globals/ResponseType';
import { ResourceType } from '@/globals/GlobalValues';
import {
	getPlaylistsByTotalViewsSortPageApi,
	Playlist
} from '@/networks/playlist';
import { getResourceUrl } from '@/utils/fileUtil';
import { useContextMenu } from '@/components/common/ContextMenu/hooks/useContextMenu';
const playlists = ref([] as Playlist[]);
const pageData = ref({
	total: 0,
	pageSize: 10,
	currentPage: 1,
	totalPages: 0,
	nextBatchSize: 5
});
const getPlaylistsByTotalViewsSortPage = async () => {
	let res = await getPlaylistsByTotalViewsSortPageApi();
	if (res && res.data.type === ResponseType.SUCCESS) {
		res.data.playlistList.map((item: Playlist) => {
			item.playlistPic = getResourceUrl(
				item.playlistPic,
				ResourceType.PLAYLIST_PICTURE
			);
			return item;
		});
		playlists.value = res.data.playlistList;
	}
};
const contextMenu = useContextMenu({
	playPlaylist: true,
	addPlaylistToPlaylist: true,
	go: true,
	deletePlaylistCollection: true,
	addPlaylistToCollection: true
});
const open = (e: PointerEvent, clickItem: Playlist) => {
	contextMenu.openContextMenu(
		e,
		JSON.parse(JSON.stringify(clickItem)),
		'playlistDescription'
	);
};

getPlaylistsByTotalViewsSortPage();
</script>
<template>
	<div class="hot-playlist">
		<CardContainer
			title="歌单-猜你喜欢"
			:pageData="pageData"
			:nextBatchCallback="getPlaylistsByTotalViewsSortPage"
			alwaysShowNextBatch>
			<template #content>
				<Card
					v-for="item of playlists"
					:key="item.playlistId"
					:picUrl="item.playlistPic"
					:title="item.playlistDescription"
					@contextmenu="(e: PointerEvent) => open(e, item)"
					@click="contextMenu.menuFunctions.playPlaylist(item)" />
			</template>
		</CardContainer>
	</div>
</template>
<style lang="scss" scoped></style>
