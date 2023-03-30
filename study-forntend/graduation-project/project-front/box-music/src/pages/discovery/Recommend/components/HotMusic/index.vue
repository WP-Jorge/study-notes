<script setup lang="ts">
import { ResourceType } from '@/globals/GlobalValues';
import { ResponseType } from '@/globals/ResponseType';
import { getMusicsByTotalViewsSortPageApi, Music } from '@/networks/music';
import { getResourceUrl } from '@/utils/fileUtil';
import { useContextMenu } from '@/components/common/ContextMenu/hooks/useContextMenu';

const musics = ref([] as Music[]);

const pageData = ref({
	total: 0,
	pageSize: 10,
	currentPage: 1,
	totalPages: 0,
	nextBatchSize: 5
});

const getSingersByTotalViewsSortPage = async () => {
	let res = await getMusicsByTotalViewsSortPageApi();
	if (res && res.data.type === ResponseType.SUCCESS) {
		res.data.musicList.map((item: Music) => {
			item.album.albumPic = getResourceUrl(
				item.album.albumPic,
				ResourceType.ALBUM_PICTURE
			);
			return item;
		});
		musics.value = res.data.musicList;
	}
};

const contextMneu = useContextMenu({
	downloadOne: true,
	playMusic: true,
	addMusicToPlaylist: true,
	addCollection: true,
	deleteCollection: true,
	addMusicToMyPlaylist: true
});

const open = (e: PointerEvent, row: Music) => {
	contextMneu.openContextMenu(e, row);
};

getSingersByTotalViewsSortPage();
</script>
<template>
	<div class="hot-music">
		<CardContainer
			title="歌曲-猜你喜欢"
			:pageData="pageData"
			:nextBatchCallback="getSingersByTotalViewsSortPage"
			alwaysShowNextBatch>
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
