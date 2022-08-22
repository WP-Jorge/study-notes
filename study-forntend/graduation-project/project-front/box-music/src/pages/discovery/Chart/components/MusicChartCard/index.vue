<script setup lang="ts">
import { ResourceType } from '@/globals/GlobalValues';
import { ResponseType } from '@/globals/ResponseType';
import { getMusicsByTotalViewsSortPageApi, Music } from '@/networks/music';
import { getResourceUrl } from '@/utils/fileUtil';
import { useContextMenu } from '@/components/common/ContextMenu/hooks/useContextMenu';
const pageData = reactive({
	total: 0,
	pageSize: 10,
	currentPage: 1,
	totalPages: 0,
	maxPage: 5
});
const musics = ref([] as Music[]);

const getMusicsByTotalViewsSortPage = async () => {
	let res = await getMusicsByTotalViewsSortPageApi(
		pageData.currentPage,
		pageData.pageSize
	);
	if (res && res.data.type === ResponseType.SUCCESS) {
		res.data.pageList.map((item: Music) => {
			item.album.albumPic = getResourceUrl(
				item.album.albumPic,
				ResourceType.ALBUM_PICTURE
			);
			return item;
		});
		musics.value = res.data.pageList;
		pageData.totalPages = res.data.totalPages;
		pageData.total = res.data.total;
	}
};

const contextMneu = useContextMenu({
	playMusic: true,
	addMusicToPlaylist: true
});

const open = (e: PointerEvent, row: Music) => {
	contextMneu.openContextMenu(e, row);
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
	getMusicsByTotalViewsSortPage();
};

getMusicsByTotalViewsSortPage();
</script>
<template>
	<div class="music-chart-card">
		<CardContainer>
			<template #title>
				<div class="title-bar">
					<div class="title">音乐榜</div>
					<div class="options">
						<span class="text" @click="nextBatch">换一批</span>
					</div>
				</div>
			</template>
			<template #content>
				<Card
					v-for="item of musics"
					:key="item.musicId"
					:picUrl="item.album.albumPic"
					:title="item.musicTitle"
					@contextmenu="(e: PointerEvent) => open(e, item)"
					@click="contextMneu.menuFunctions.playMusic(item)" />
			</template>
		</CardContainer>
	</div>
</template>
<style lang="scss" scoped>
.music-chart-card {
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
