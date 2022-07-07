<script setup lang="ts">
import { ResourceType } from '@/globals/GlobalValues';
import { ResponseType } from '@/globals/ResponseType';
import { getResourceUrl } from '@/utils/fileUtil';
import { getSingersByTotalViewsSortPageApi, Singer } from '@/networks/singer';
const pageData = reactive({
	total: 0,
	pageSize: 10,
	currentPage: 1,
	totalPages: 0,
	maxPage: 5
});
const singers = ref([] as Singer[]);

const getSingersByTotalViewsSortPage = async () => {
	let res = await getSingersByTotalViewsSortPageApi(
		pageData.currentPage,
		pageData.pageSize
	);
	if (res && res.data.type === ResponseType.SUCCESS) {
		res.data.pageList.map((item: Singer) => {
			item.singerPic = getResourceUrl(
				item.singerPic,
				ResourceType.SINGER_PICTURE
			);
			return item;
		});
		singers.value = res.data.pageList;
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
	getSingersByTotalViewsSortPage();
};

getSingersByTotalViewsSortPage();
</script>
<template>
	<div class="singer-chart-card">
		<CardContainer>
			<template #title>
				<div class="title-bar">
					<div class="title">歌手榜</div>
					<div class="options">
						<span class="text" @click="nextBatch">换一批</span>
					</div>
				</div>
			</template>
			<template #content>
				<Card
					v-for="item of singers"
					:key="item.singerId"
					:picUrl="item.singerPic"
					:title="item.singerName" />
			</template>
		</CardContainer>
	</div>
</template>
<style lang="scss" scoped>
.singer-chart-card {
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
