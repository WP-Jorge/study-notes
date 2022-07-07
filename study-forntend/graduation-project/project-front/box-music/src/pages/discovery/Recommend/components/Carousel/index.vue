<script setup lang="ts">
import { ResourceType } from '@/globals/GlobalValues';
import { ResponseType } from '@/globals/ResponseType';
import { Album, getAlbumsByTotalViewsSortPageApi } from '@/networks/album';
import { getResourceUrl } from '@/utils/fileUtil';
const carousels = ref([] as Album[]);

const getAlbumsByTotalViewsSortPage = async () => {
	let res = await getAlbumsByTotalViewsSortPageApi(10, 6);
	if (res && res.data.type === ResponseType.SUCCESS) {
		res.data.pageList.map((item: Album) => {
			item.albumPic = getResourceUrl(item.albumPic, ResourceType.ALBUM_PICTURE);
			return item;
		});
		carousels.value = res.data.pageList;
	}
};

getAlbumsByTotalViewsSortPage();
</script>
<template>
	<div class="carousel">
		<el-carousel
			v-if="carousels.length"
			:interval="4000"
			type="card"
			height="200px"
			trigger="click">
			<el-carousel-item v-for="item of carousels" :key="item.albumId">
				<carousel-item
					:picUrl="item.albumPic"
					:title="item.albumName"
					:content="item.albumDescription" />
			</el-carousel-item>
		</el-carousel>
	</div>
</template>
<style lang="scss" scoped>
.carousel {
	height: 200px;
	.el-carousel__item h3 {
		color: #475669;
		opacity: 0.75;
		line-height: 200px;
		margin: 0;
		text-align: center;
	}
}
</style>
