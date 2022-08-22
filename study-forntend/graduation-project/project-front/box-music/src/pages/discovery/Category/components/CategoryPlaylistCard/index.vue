<script setup lang="ts">
import { ResourceType } from '@/globals/GlobalValues';
import { ResponseType } from '@/globals/ResponseType';
import { getResourceUrl } from '@/utils/fileUtil';
import { Playlist } from '@/networks/playlist';
import { getPlaylistsByCategoryIdPageApi } from '@/networks/playlist';
import { usePlaylistStore } from '@/store/playlist';
import { useContextMenu } from '@/components/common/ContextMenu/hooks/useContextMenu';
const playlistStore = usePlaylistStore();
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

const contextMenu = useContextMenu({
	playPlaylist: true,
	addPlaylistToPlaylist: true
});
const open = (e: PointerEvent, clickItem: Playlist) => {
	contextMenu.openContextMenu(e, clickItem);
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
				() => getPlaylistsByCategoryIdPage(playlistStore.category.categoryId as string)
			">
			<template #content>
				<Card
					v-for="item of playlists"
					:key="item.playlistId"
					:picUrl="item.playlistPic"
					:title="item.playlistName"
					@contextmenu="(e: PointerEvent) => open(e, item)"
					@click="contextMenu.menuFunctions.playPlaylist(item)" />
			</template>
		</CardContainer>
	</div>
</template>
<style lang="scss" scoped></style>
