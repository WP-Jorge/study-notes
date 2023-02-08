<script setup lang="ts">
import { useContextMenu } from '@/components/common/ContextMenu/hooks/useContextMenu';
import { ResourceType } from '@/globals/GlobalValues';
import { ResponseType } from '@/globals/ResponseType';
import { getMusicsByCategoryIdPageApi, Music } from '@/networks/music';
import { useMusicStore } from '@/store/music';
import { getResourceUrl } from '@/utils/fileUtil';
const musicStore = useMusicStore();

const musics = ref([] as Music[]);
const pageData = ref({
	total: 0,
	pageSize: 20,
	currentPage: 1,
	totalPages: 0,
	nextBatchSize: 5
});

const getMusicsByCategoryIdPage = async (categoryId: string) => {
	const res = await getMusicsByCategoryIdPageApi(
		pageData.value.currentPage,
		pageData.value.pageSize,
		categoryId
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
		pageData.value.totalPages = res.data.totalPages;
		pageData.value.total = res.data.total;
	}
};

const contextMneu = useContextMenu({
	playMusic: true,
	addMusicToPlaylist: true,
	downloadOne: true,
	addCollection: true,
	deleteCollection: true
});

const open = (e: PointerEvent, row: Music) => {
	contextMneu.openContextMenu(e, row);
};

watch(
	() => musicStore.category.categoryId,
	() => {
		pageData.value.currentPage = 1;
		musicStore.category.categoryId &&
			getMusicsByCategoryIdPage(musicStore.category.categoryId);
	},
	{
		immediate: true
	}
);
</script>
<template>
	<div class="category-music-card">
		<CardContainer
			:title="`【${musicStore.category.categoryName}】单曲`"
			:pageData="pageData"
			:nextBatchCallback="
				() => getMusicsByCategoryIdPage(musicStore.category.categoryId as string)
			">
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
<style lang="scss" scoped></style>
