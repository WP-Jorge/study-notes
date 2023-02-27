<script setup lang="ts">
import { useSystemStore } from '@/store/system';
import { storeToRefs } from 'pinia';
import Header from '@/components/content/Header/index.vue';
import Aside from '@/components/content/Aside/index.vue';
import Menu from '@/components/content/Menu/index.vue';
import MusicBar from '@/components/content/MusicBar/index.vue';
import MusicDetail from '@/components/content/MusicDetail/index.vue';
import ContentMexu from './components/common/ContextMenu/index.vue';
import Login from '@/components/content/Login/index.vue';
import { useRoute } from 'vue-router';
import { useMusicStore } from './store/music';
import {
	addMusicToPlaylistApi,
	deleteMusicFromPlaylistApi,
	getPlaylistsByPlaylistNameAndUserIdPageApi,
	getSimplePlaylistsWithMusicsApi,
	Playlist
} from './networks/playlist';
import { ResponseType } from './globals/ResponseType';
import { getResourceUrl } from './utils/fileUtil';
import { ResourceType } from './globals/GlobalValues';
import { Music } from './networks/music';
import { usePlaylistStore } from './store/playlist';
import { ElMessage } from 'element-plus';
import { useContextMenuStore } from './store/contextMenu';

const systemStore = useSystemStore();
const musicStore = useMusicStore();
const playlistStore = usePlaylistStore();
const menuStore = useContextMenuStore();
const route = useRoute();
console.log('🦃🦃route.name', route.name);
const { showSiderMenu, showMain, showMusicDetail } = storeToRefs(systemStore);

// 初始化数据
const getPlaylistsByPlaylistNameAndUserIdPage = async (keyword = '') => {
	let res = await getPlaylistsByPlaylistNameAndUserIdPageApi(1, -1, keyword);
	console.log('🦃🦃res', res);
	if (res.data && res.data.type === ResponseType.SUCCESS) {
		res.data.pageList.map((item: Playlist) => {
			item.playlistPic = getResourceUrl(
				item.playlistPic,
				ResourceType.PLAYLIST_PICTURE
			);
			item.musics?.map((music: Music) => {
				music.album.albumPic = getResourceUrl(
					music.album.albumPic,
					ResourceType.ALBUM_PICTURE
				);
			});
		});
		console.log('🦃🦃res.data.pageList', res.data.pageList);
		playlistStore.collectionPlaylist = res.data.pageList;
	}
};
const getSimplePlaylists = async () => {
	const res = await getSimplePlaylistsWithMusicsApi();
	console.log('🦃🦃res', res);
	if (res.data && res.data.type === ResponseType.SUCCESS) {
		res.data.pageList.map((playlist: Playlist) => {
			playlist.musics?.map((music: Music) => {
				music.album.albumPic = getResourceUrl(
					music.album.albumPic,
					ResourceType.ALBUM_PICTURE
				);
			});
		});
		playlistStore.playlists = res.data.pageList ?? [];
		playlistStore.currentPlaylist = playlistStore.playlists?.[0] ?? {};
	}
};
const addMusicToPlaylist = async (data: any) => {
	const res = await addMusicToPlaylistApi(data);
	if (res.data && res.data.type === ResponseType.SUCCESS) {
		return ElMessage.success(res.data.msg);
	}
	ElMessage.error(res.data.type);
};
const deleteMusicFromPlaylist = async (data: any) => {
	const res = await deleteMusicFromPlaylistApi(data);
	console.log('🦃🦃res', res);
	if (res.data && res.data.type === ResponseType.SUCCESS) {
		getSimplePlaylists();
		return ElMessage.success(res.data.msg);
	}
	ElMessage.error(res.data.type);
};
playlistStore.getPlaylistsByPlaylistNameAndUserIdPage =
	getPlaylistsByPlaylistNameAndUserIdPage;
playlistStore.getSimplePlaylists = getSimplePlaylists;
playlistStore.addMusicToMyPlaylist = addMusicToPlaylist;
playlistStore.deleteMusicFromPlaylist = deleteMusicFromPlaylist;

musicStore.getCollection();
getSimplePlaylists();
getPlaylistsByPlaylistNameAndUserIdPage();

// const disableOption = computed(() => {
// 	return !!playlistStore.playlists.find(playlist => {
// 		return playlist?.musics?.find(music => {
// 			return menuStore.requestData.musicId === music.musicId;
// 		});
// 	});
// });
const disableOption = (currentPlaylist: Playlist) => {
	return !!currentPlaylist?.musics?.find((music: Music) => {
		return menuStore.requestData.musicId === music.musicId;
	});
};
// const electronAPI = window.electronAPI;
// electronAPI.getMusicInfo(
// 	'D:/CloudMusic/双笙.flac',
// 	// 'G:/Musics/1/漫长的告白-双笙-162283619.flac',
// 	(musicInfo: any) => {
// 		console.log(musicInfo);
// 	}
// );
// electronAPI.getMsg(
// 	'D:/CloudMusic/双笙.flac',
// 	'D:/CloudMusic/双笙6.flac',
// 	(data: string) => {
// 		console.log(data);
// 	},
// 	(data: string) => {
// 		console.log(data);
// 	},
// 	(data: any) => {
// 		console.log(data);
// 	}
// );
</script>
<template>
	<ContentMexu />
	<el-container>
		<el-header>
			<Header />
		</el-header>
		<el-container>
			<el-aside v-show="showSiderMenu && !showMusicDetail" width="200px">
				<Aside />
			</el-aside>
			<el-main v-show="showMain && !showMusicDetail">
				<Menu />
				<div
					class="content"
					:class="{ 'search-des-main': route.name === 'SearchDes' }">
					<router-view />
				</div>
			</el-main>
			<MusicDetail v-show="showMusicDetail" />
		</el-container>
		<el-footer><MusicBar /></el-footer>
	</el-container>
	<Login />
	<el-dialog
		v-model="menuStore.showSelectPlaylist"
		title="选择歌单"
		width="50%">
		<el-select
			v-model="menuStore.requestData.playlistId"
			placeholder="请选择歌单">
			<el-option
				v-for="item in playlistStore.playlists"
				:key="item.playlistId"
				:label="item.playlistName"
				:value="item.playlistId"
				:disabled="disableOption(item)" />
		</el-select>
		<template #footer>
			<span class="dialog-footer">
				<el-button @click="menuStore.showSelectPlaylist = false">
					取消
				</el-button>
				<el-button type="primary" @click="menuStore.addMusicToPlaylist">
					确定
				</el-button>
			</span>
		</template>
	</el-dialog>
</template>
<style lang="scss">
@use './assets/global.scss';
.el-container {
	height: 100%;
	.el-header {
		padding: 0;
		-webkit-app-region: drag;
	}
	.el-aside {
		max-height: calc(100vh - 60px - 71px);
		overflow: overlay;
		&::-webkit-scrollbar {
			width: 5px;
			height: 8px;
			background-color: var(--el-color-info-light-9);
		}
		&::-webkit-scrollbar-thumb {
			background-color: transparent;
		}
	}
	.el-aside:hover {
		&::-webkit-scrollbar-thumb {
			background-color: var(--el-color-primary-light-5);
		}
	}
	.el-main {
		padding: 0;
		height: calc(100vh - 60px - 71px);
		min-width: 780px;
		overflow: hidden;
		& > .content {
			padding: 10px;
			overflow: overlay;
			height: calc(100% - 60px);
			&::-webkit-scrollbar {
				width: 5px;
				height: 8px;
				background-color: var(--el-color-info-light-9);
			}
			&::-webkit-scrollbar-thumb {
				background-color: transparent;
			}
		}
		& > .content:hover {
			&::-webkit-scrollbar-thumb {
				background-color: var(--el-color-primary-light-5);
			}
		}
	}
	.search-des-main {
		height: calc(100vh - 60px - 91px) !important;
	}
	.el-footer {
		padding: 0;
		height: 71px;
		border-top: 1px solid var(--el-border-color);
	}
}

.el-popper {
	.user-info-container {
		.title,
		.content {
			border-bottom: 1px solid #e0e0e0;
			padding-bottom: 5px;
			margin-bottom: 5px;
			p {
				position: relative;
				margin-top: 5px;
				font-weight: 500;
				.edit {
					display: none;
					position: absolute;
					right: 0;
					padding: 0 5px;
					cursor: pointer;
					color: var(--el-color-primary);
					font-size: 12px;
					line-height: 19.6px;
				}
			}
			p:hover {
				.edit {
					display: inline-block;
				}
			}
		}
		.footer {
			display: flex;
			justify-content: end;
			margin-top: 10px;
		}
	}
}
</style>
