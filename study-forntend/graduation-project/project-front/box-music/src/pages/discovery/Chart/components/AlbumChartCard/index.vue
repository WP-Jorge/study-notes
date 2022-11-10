<script setup lang="ts">
import { ResourceType } from '@/globals/GlobalValues';
import { ResponseType } from '@/globals/ResponseType';
import { getResourceUrl } from '@/utils/fileUtil';
import { Album, getAlbumsByTotalViewsSortPageApi } from '@/networks/album';
import { useContextMenu } from '@/components/common/ContextMenu/hooks/useContextMenu';

const pageData = reactive({
	total: 0,
	pageSize: 10,
	currentPage: 1,
	totalPages: 0,
	maxPage: 5
});
const albums = ref([] as Album[]);

const getAlbumsByTotalViewsSortPage = async () => {
	let res = await getAlbumsByTotalViewsSortPageApi(
		pageData.currentPage,
		pageData.pageSize
	);
	if (res && res.data.type === ResponseType.SUCCESS) {
		res.data.pageList.map((item: Album) => {
			item.albumPic = getResourceUrl(item.albumPic, ResourceType.ALBUM_PICTURE);
			return item;
		});
		albums.value = res.data.pageList;
		pageData.totalPages = res.data.totalPages;
		pageData.total = res.data.total;
	}
};

const nextBatch = () => {
	if (
		pageData.currentPage < pageData.totalPages &&
		pageData.currentPage < pageData.maxPage
	) {
		pageData.currentPage++;
	} else if (
		pageData.currentPage === pageData.totalPages ||
		pageData.currentPage === pageData.maxPage
	) {
		pageData.currentPage = 1;
	}
	getAlbumsByTotalViewsSortPage();
};

const contextMneu = useContextMenu({
	playAlbum: true,
	addAlbumToPlaylist: true,
	go: true
});

const open = (e: PointerEvent, row: Album) => {
	contextMneu.openContextMenu(e, row, 'albumDescription');
};

getAlbumsByTotalViewsSortPage();
</script>
<template>
	<div class="album-chart-card">
		<CardContainer>
			<template #title>
				<div class="title-bar">
					<div class="title">专辑榜</div>
					<div class="options">
						<span class="text" @click="nextBatch">换一批</span>
					</div>
				</div>
			</template>
			<template #content>
				<Card
					v-for="item of albums"
					:key="item.albumId"
					:picUrl="item.albumPic"
					:title="item.albumName"
					@contextmenu="(e: PointerEvent) => open(e, item)"
					@click="contextMneu.menuFunctions.playAlbum(item)" />
			</template>
		</CardContainer>
	</div>
</template>
<style lang="scss" scoped>
.album-chart-card {
	.title-bar {
		display: flex;
		justify-content: space-between;
		align-items: center;
		.options {
			color: #000;
			font-weight: normal;
			span {
				padding-right: 10px;
				cursor: pointer;
				&:hover {
					color: var(--el-color-info-dark-2);
				}
			}
		}
	}
}
</style>
