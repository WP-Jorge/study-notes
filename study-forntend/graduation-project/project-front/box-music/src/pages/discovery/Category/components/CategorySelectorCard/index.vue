<script setup lang="ts">
import { ResourceType } from '@/globals/GlobalValues';
import { ResponseType } from '@/globals/ResponseType';
import {
	Category,
	getCategoriesByCategoryNamePageApi
} from '@/networks/category';
import { useMusicStore } from '@/store/music';
import { usePlaylistStore } from '@/store/playlist';
import { getResourceUrl } from '@/utils/fileUtil';
const musicStore = useMusicStore();
const playlistStore = usePlaylistStore();

const categories = ref([] as Category[]);
const getCategoriesByCategoryNamePage = async () => {
	const res = await getCategoriesByCategoryNamePageApi(1, -1);
	if (res && res.data.type === ResponseType.SUCCESS) {
		res.data.pageList.map((item: Category) => {
			item.categoryPic = getResourceUrl(
				item.categoryPic,
				ResourceType.CATEGORY_PICTURE
			);
			return item;
		});
		categories.value = res.data.pageList;
		musicStore.category = categories.value[0];
		playlistStore.category = categories.value[0];
	}
};
getCategoriesByCategoryNamePage();

const itemClick = (category: Category) => {
	musicStore.category = category;
	playlistStore.category = category;
};
</script>
<template>
	<div class="category-selector-card">
		<SimpleContainer>
			<template #title>音乐分类</template>
			<template #content>
				<div class="selector-container">
					<span
						v-for="item of categories"
						:key="item.categoryId"
						:class="{
							'selector-item': true,
							active: item === musicStore.category
						}"
						@click="itemClick(item)">
						{{ item.categoryName }}
					</span>
				</div>
			</template>
		</SimpleContainer>
	</div>
</template>
<style lang="scss" scoped>
.category-selector-card {
	.selector-container {
		.selector-item {
			display: inline-block;
			margin: 10px;
			cursor: pointer;

			&:hover {
				color: var(--el-color-info-dark-2);
			}
		}
		.active {
			// font-weight: bold;
			color: var(--el-color-primary);
		}
	}
}
</style>
