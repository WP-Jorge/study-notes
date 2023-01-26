<script setup lang="ts">
import { ClickOutside as vClickOutside } from 'element-plus';
import { useContextMenu } from '@/components/common/ContextMenu/hooks/useContextMenu';
import { ResourceType } from '@/globals/GlobalValues';
import { Album } from '@/networks/album';
import { DesMusic, getMusicsByKeywordApi, Music } from '@/networks/music';
import { Singer } from '@/networks/singer';
import { useSearchStore } from '@/store/search';
import { debounce } from '@/utils/baseUtil';
import { getResourceUrl } from '@/utils/fileUtil';
import { useRouter } from 'vue-router';
interface MyMouseEvent extends MouseEvent {
	path: Element[];
}
const router = useRouter();
const searchStore = useSearchStore();
const loading = ref(false);
const searchPop = ref();
const keyword = ref('');
const musicData = ref([] as Music[]);
const visible = ref(false);
const contextMenu = useContextMenu({
	downloadOne: true,
	playMusic: true,
	addMusicToPlaylist: true
});
const getMusicList = async (replace = false, keyword = searchStore.keyword) => {
	// searchMusicList.value = tempMusicList as unknown as Music[];
	const res = await getMusicsByKeywordApi(keyword);
	console.log('🦃🦃res', res);
	if (res && res.data) {
		const formatMusics = [] as Music[];
		res.data.musics.map((item: DesMusic) => {
			let music = {} as Music;
			const album = {} as Album;
			const singers = [] as Singer[];
			album.albumId = item.albumId;
			album.albumName = item.albumName;
			album.albumPic = getResourceUrl(
				item.albumPic,
				ResourceType.ALBUM_PICTURE
			);
			album.albumDescription = item.albumDescription;
			music.album = album;
			const singerIdArr = item.singerId?.split(',') ?? [];
			const singerNameArr = item.singerName?.split(',') ?? [];
			const singerPicArr = item.singerPic.split(',') ?? [];
			singerIdArr?.map((singerId, index) => {
				const singer = {} as Singer;
				singer.singerId = singerId;
				singer.singerName = singerNameArr[index];
				singer.singerPic = singerPicArr[index];
				singers.push(singer);
			});
			music.singers = singers;
			music = { ...item, ...music };
			formatMusics.push(music);
		});
		replace && (searchStore.searchMusicList = formatMusics);
		return formatMusics;
	}
};
searchStore.getMusicList = getMusicList;
const remoteMethod = debounce(async () => {
	console.log('🦃🦃query', keyword.value);
	loading.value = true;
	const tempMusics = (await getMusicList(false, keyword.value)) as Music[];
	loading.value = false;
	musicData.value = tempMusics.filter(item => {
		return (
			item.musicTitle.includes(keyword.value) ||
			item.singers.map(item => item.singerName.includes(keyword.value))
		);
	});
	console.log('🦃🦃musicData.value', musicData.value);
	// if (keyword.value) {
	// 	loading.value = true;
	// 	setTimeout(() => {
	// 		loading.value = false;
	// 		options.value = list.value.filter(item => {
	// 			return item.label.toLowerCase().includes(keyword.value.toLowerCase());
	// 		});
	// 	}, 200);
	// } else {
	// 	options.value = [];
	// }
});
const search = async () => {
	searchStore.keyword = keyword.value;
	await getMusicList(true, keyword.value);
	router.push({ name: 'SearchDes' });
};
const select = (music: Music) => {
	console.log('🦃🦃item', music);
	contextMenu.menuFunctions.playMusic(music);
	// searchPop.value.hide();
	visible.value = false;
};
const onClickOutside = (e: MyMouseEvent) => {
	const searchPopEl = document.querySelector('.searchPopEl');
	if (!e.path.includes(searchPopEl as Element)) {
		visible.value = false;
	}
};
// const getData = () => {
// 	musicData.value = tempMusicList as never as Music[];
// };
// getData();
</script>
<template>
	<div class="search-select">
		<el-popover
			ref="searchPop"
			popper-class="searchPopEl"
			placement="bottom"
			:visible="visible && musicData.length > 0"
			:enterable="false"
			:teleported="false"
			:width="300"
			:hide-after="0"
			:show-arrow="false">
			<template #reference>
				<el-input
					v-model="keyword"
					v-click-outside="onClickOutside"
					placeholder="请输入关键字"
					clearable
					@keyup="remoteMethod"
					@keyup.enter="search"
					@click="visible = true">
					<template #suffix>
						<i-akar-icons:search class="search-icon" @click="search" />
					</template>
				</el-input>
			</template>
			<div class="search-content">
				<div
					v-for="item of musicData"
					:key="item.musicId"
					:class="{ 'seatch-item': true }"
					:title="item.musicTitle"
					@click="select(item)">
					{{ item.musicTitle }} --
					{{ item.singers.map(item => item.singerName).join(' \ ') }}
				</div>
			</div>
		</el-popover>
	</div>
</template>
<style lang="scss" scoped>
.search-select {
	display: flex;
	justify-content: center;
	align-items: center;
	margin-right: 20px;
	-webkit-app-region: no-drag;
	.el-input {
		width: 150px;
		.search-icon {
			cursor: pointer;
		}
		:deep(.el-input__wrapper) {
			border-radius: 50px;
			background-color: transparent;
			font-size: 12px;
		}
		:deep(.el-select__input) {
			padding-left: 20px;
		}
	}
	.search-content {
		max-height: 500px;
		overflow: overlay;
		&::-webkit-scrollbar {
			width: 5px;
			height: 8px;
			// background-color: var(--el-color-info-light-9);
			background-color: transparent;
		}
		&::-webkit-scrollbar-thumb {
			background-color: transparent;
		}
		.seatch-item {
			padding: 5px;
			overflow: hidden;
			text-overflow: ellipsis;
			white-space: nowrap;
			transition: all 0.1s ease;
			cursor: pointer;
		}
		.seatch-item:hover {
			background-color: rgb(233, 233, 233);
		}
	}
	.search-content:hover {
		&::-webkit-scrollbar-thumb {
			background-color: var(--el-color-primary-light-5);
		}
	}
}
</style>
