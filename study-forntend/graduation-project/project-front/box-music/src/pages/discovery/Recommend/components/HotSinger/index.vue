<script setup lang="ts">
import { ResourceType } from '@/globals/GlobalValues';
import { ResponseType } from '@/globals/ResponseType';
import { getSingersByTotalViewsSortPageApi, Singer } from '@/networks/singer';
import { getResourceUrl } from '@/utils/fileUtil';
const singers = ref([] as Singer[]);
const pageData = ref({
	total: 0,
	pageSize: 10,
	currentPage: 1,
	totalPages: 0,
	nextBatchSize: 5
});
const getSingersByTotalViewsSortPage = async () => {
	let res = await getSingersByTotalViewsSortPageApi(
		pageData.value.currentPage,
		pageData.value.pageSize
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
		pageData.value.totalPages = res.data.totalPages;
		pageData.value.total = res.data.total;
	}
};
getSingersByTotalViewsSortPage();
</script>
<template>
	<div class="hot-singer">
		<CardContainer
			title="热门歌手"
			:pageData="pageData"
			:nextBatchCallback="getSingersByTotalViewsSortPage">
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
<style lang="scss" scoped></style>
