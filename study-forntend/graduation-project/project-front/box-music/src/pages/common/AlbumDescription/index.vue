<script setup lang="ts">
import { useContextMenu } from '@/components/common/ContextMenu/hooks/useContextMenu';
import { Album } from '@/networks/album';
import router from '@/router';
import AlbumCard from './components/AlbumCard/index.vue';
import { getMusicsByAlbumIdPageApi } from '@/networks/music';
import { ResponseType } from '@/globals/ResponseType';
import { getResourceUrl } from '@/utils/fileUtil';
import { ResourceType } from '@/globals/GlobalValues';
import { reactive } from 'vue';
import { Music } from '@/networks/music';
import { getFormatTime } from '@/utils/mathUtil';
const albumDes = reactive(router.currentRoute.value.query as Album);

const pageData = reactive({
	total: 0,
	pageSize: -1,
	currentPage: 1,
	totalPages: 0,
	maxPage: 5
});
const musics = ref([] as Music[]);

const contextMenu = useContextMenu({
	downloadOne: true,
	playMusic: true,
	addMusicToPlaylist: true
});
const open = (row: Music, cloumn: any, e: PointerEvent) => {
	contextMenu.openContextMenu(e, row);
};

async function getMusicsByAlbumIdPage() {
	let res = await getMusicsByAlbumIdPageApi(
		pageData.currentPage,
		pageData.pageSize,
		albumDes.albumId
	);
	if (res && res.data.type === ResponseType.SUCCESS) {
		res.data.pageList.map((item: Album) => {
			item.albumPic = getResourceUrl(item.albumPic, ResourceType.ALBUM_PICTURE);
			return item;
		});
		musics.value = res.data.pageList;
		pageData.totalPages = res.data.totalPages;
		pageData.total = res.data.total;
	}
}
getMusicsByAlbumIdPage();
</script>
<template>
	<div class="album-description">
		<AlbumCard :albumDes="albumDes" />
		<SimpleContainer :pageData="pageData">
			<template #title>
				<div class="title-bar">
					<div class="title">歌曲</div>
				</div>
			</template>
			<template #content>
				<el-table
					:data="musics"
					@row-contextmenu="open"
					@row-dblclick="contextMenu.menuFunctions.playMusic">
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
.album-description {
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
