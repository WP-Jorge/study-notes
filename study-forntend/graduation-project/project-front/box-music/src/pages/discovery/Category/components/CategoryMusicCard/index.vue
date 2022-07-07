<script setup lang="ts">
import { usePlayOrderChange } from '@/components/content/MusicBar/components/MusicBarCenter/usePlayOrderChange';
import { ResourceType } from '@/globals/GlobalValues';
import { ResponseType } from '@/globals/ResponseType';
import { getMusicsByCategoryIdPageApi, Music } from '@/networks/music';
import { useMusicStore } from '@/store/music';
import { getResourceUrl } from '@/utils/fileUtil';
const musicStore = useMusicStore();
const playOrderChange = usePlayOrderChange();

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

const cardClick = (music: Music) => {
	if (!musicStore.musicList.includes(music)) {
		musicStore.musicList.push(music);
	}
	musicStore.setMusic(music);
	playOrderChange();
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
				() => getMusicsByCategoryIdPage(musicStore.category.categoryId)
			">
			<!-- <template #title>
				<div class="title-bar">
					<div class="title">
						{{ `【${musicStore.category.categoryName}】单曲` }}
					</div>
					<div v-show="showNextBatch" class="options">
						<span class="text" @click="nextBatch">换一批</span>
					</div>
				</div>
			</template> -->
			<template #content>
				<Card
					v-for="item of musics"
					:key="item.musicId"
					:picUrl="item.album.albumPic"
					:title="item.musicTitle"
					@click="cardClick(item)" />
			</template>
		</CardContainer>
	</div>
</template>
<style lang="scss" scoped></style>
