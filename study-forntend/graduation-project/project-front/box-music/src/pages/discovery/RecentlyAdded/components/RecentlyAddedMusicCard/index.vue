<script setup lang="ts">
import { ResourceType } from '@/globals/GlobalValues';
import { ResponseType } from '@/globals/ResponseType';
import { getMusicsByCreateTimeSortPageApi, Music } from '@/networks/music';
import { getResourceUrl } from '@/utils/fileUtil';
import SimpleContainer from '@/components/common/SimpleContainer/index.vue';
import { useDownloadStore } from '@/store/download';
import { getFormatTime } from '@/utils/mathUtil';
import { useContextMenuStore } from '@/store/contextMenu';
const menuStore = useContextMenuStore();
const downloadStore = useDownloadStore();

const pageData = ref({
	total: 0,
	pageSize: 10,
	currentPage: 1,
	totalPages: 0,
	maxPage: 5
});
const musics = ref([] as Music[]);

const getMusicsByCreateTimeSortPage = async () => {
	let res = await getMusicsByCreateTimeSortPageApi(
		pageData.value.currentPage,
		pageData.value.pageSize
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

const downloadOne = (music: Music) => {
	downloadStore.startDownloadOne(music);
};

const open = (row: Music, cloumn: any, e: PointerEvent) => {
	const contextMenuList = [
		{
			type: 'li',
			title: '下载',
			disabled: !downloadStore.downloadable(row),
			callback: () => downloadOne(row)
		}
	];
	menuStore.openContextMenu({ event: e, args: row, contextMenuList });
};

getMusicsByCreateTimeSortPage();
</script>
<template>
	<div class="recently-added-music-card">
		<SimpleContainer
			title="最近新增"
			:pageData="pageData"
			:nextBatchCallback="getMusicsByCreateTimeSortPage">
			<template #content>
				<el-table :data="musics" @row-contextmenu="open">
					<el-table-column #default="{ row: music }" label="封面" :width="80">
						<div class="music-img">
							<img :src="music.album.albumPic" :alt="music.musicTitle" />
						</div>
					</el-table-column>
					<el-table-column
						#default="{ row: music }"
						label="音乐标题"
						:width="220">
						<div class="music-title ellipse">
							{{ music.musicTitle }}
						</div>
					</el-table-column>
					<el-table-column #default="{ row: music }" label="歌手">
						<div class="ellipse">
							{{ (music as Music).singers.map(singer => singer.singerName).join(' / ') }}
						</div>
					</el-table-column>
					<el-table-column #default="{ row: music }" label="专辑">
						<div class="ellipse">
							{{ music.album.albumName }}
						</div>
					</el-table-column>
					<el-table-column #default="{ row: music }" label="质量">
						<div class="ellipse">
							{{ music.level }}
						</div>
					</el-table-column>
					<el-table-column #default="{ row: music }" label="加入时间">
						<div class="ellipse">
							{{
								new Date(music.createTime)
									.toLocaleDateString()
									.replaceAll('/', '-')
							}}
						</div>
					</el-table-column>
					<el-table-column #default="{ row: music }" label="时长">
						<div class="ellipse">
							{{ getFormatTime(music.duration) }}
						</div>
					</el-table-column>
				</el-table>
			</template>
		</SimpleContainer>
	</div>
</template>
<style lang="scss" scoped>
.recently-added-music-card {
	.el-table {
		.music-img {
			display: flex;
			justify-content: space-between;
			align-items: center;
			height: 100%;
			img {
				width: 50px;
				height: 50px;
				border-radius: 5px;
			}
		}
	}
}
</style>
