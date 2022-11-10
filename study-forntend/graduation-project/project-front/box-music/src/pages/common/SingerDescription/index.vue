<script setup lang="ts">
import { useContextMenu } from '@/components/common/ContextMenu/hooks/useContextMenu';
import { Album } from '@/networks/album';
import { Singer } from '@/networks/singer';
import router from '@/router';
import SingerCard from './components/SingerCard/index.vue';
import { getAlbumsBySingerIdPageApi } from '@/networks/album';
import { ResponseType } from '@/globals/ResponseType';
import { getResourceUrl } from '@/utils/fileUtil';
import { ResourceType } from '@/globals/GlobalValues';
const singerDes = reactive(router.currentRoute.value.query as Singer);

const pageData = reactive({
	total: 0,
	pageSize: -1,
	currentPage: 1,
	totalPages: 0,
	maxPage: 5
});
const albums = ref([] as Album[]);

const contextMneu = useContextMenu({
	playAlbum: true,
	addAlbumToPlaylist: true,
	go: true
});

const open = (e: PointerEvent, row: Album) => {
	contextMneu.openContextMenu(e, row, 'albumDescription');
};

async function getAlbumsBySingerIdPage() {
	let res = await getAlbumsBySingerIdPageApi(
		pageData.currentPage,
		pageData.pageSize,
		singerDes.singerId
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
}
getAlbumsBySingerIdPage();
</script>
<template>
	<div class="singer-description">
		<SingerCard :singerDes="singerDes" />
		<CardContainer>
			<template #title>
				<div class="title-bar">
					<div class="title">专辑</div>
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
.singer-description {
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
