<script setup lang="ts">
import { useContextMenu } from '@/components/common/ContextMenu/hooks/useContextMenu';
import router from '@/router';
import PlaylistCard from './components/PlaylistCard/index.vue';
import { ResponseType } from '@/globals/ResponseType';
import { getResourceUrl } from '@/utils/fileUtil';
import { ResourceType } from '@/globals/GlobalValues';
import { getMusicsByPlaylistIdPageApi, Music } from '@/networks/music';
import { Playlist } from '@/networks/playlist';
import { getFormatTime } from '@/utils/mathUtil';
const playlistDes = reactive(router.currentRoute.value.query as Playlist);

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

async function getMusicsByPlaylistIdPage() {
	let res = await getMusicsByPlaylistIdPageApi(
		pageData.currentPage,
		pageData.pageSize,
		playlistDes.playlistId
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
		pageData.totalPages = res.data.totalPages;
		pageData.total = res.data.total;
	}
}
getMusicsByPlaylistIdPage();
</script>
<template>
	<div class="playlist-description">
		<PlaylistCard :playlistDes="playlistDes" />
		<SimpleContainer title="歌曲" :pageData="pageData">
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
.playlist-description {
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
