import { ResponseType } from '@/globals/ResponseType';
import { Album } from '@/networks/album';
import { Category } from '@/networks/category';
import {
	getMusicsByAlbumIdPageApi,
	getMusicsByPlaylistIdPageApi,
	Music
} from '@/networks/music';
import { Playlist } from '@/networks/playlist';
import { defineStore } from 'pinia';
import { useMusicStore } from './music';

// 1.定义并导出容器
/**
 * 参数一：容器 id，必须为唯一值，将来 pinia 会把所有的容器挂载到跟容器上
 * 参数二：选项对象
 */

export const usePlaylistStore = defineStore('playlist', {
	/**
	 * 类似组件的 data
	 */
	state: () => {
		return {
			category: {} as Category,
			playlists: [] as Playlist[],
			currentPlaylist: {} as Playlist,
			getPlaylistsByPlaylistNameAndUserIdPage: Function as any,
			getSimplePlaylists: Function as any
		};
	},
	/**
	 * 类似组件的 computed，用来封装计算属性，具有缓存功能
	 */
	getters: {},
	/**
	 * 类似组件的 methods，封装业务逻辑，修改 state
	 */
	actions: {
		async playPlaylist(playlist: Playlist) {
			const musicStore = useMusicStore();
			let res = await getMusicsByPlaylistIdPageApi(1, -1, playlist.playlistId);
			if (res && res.data.type === ResponseType.SUCCESS) {
				musicStore.setMusicList(res.data.pageList);
			}
		},
		async addPlaylistToPlaylist(playlist: Playlist) {
			const musicStore = useMusicStore();
			let res = await getMusicsByPlaylistIdPageApi(1, -1, playlist.playlistId);
			if (res && res.data.type === ResponseType.SUCCESS) {
				musicStore.setMusicList(res.data.pageList, true);
			}
		},
		async addMusicToPlaylist(music: Music) {
			const musicStore = useMusicStore();
			musicStore.setMusicList([music], true);
		},
		async playAlbum(album: Album) {
			const musicStore = useMusicStore();
			let res = await getMusicsByAlbumIdPageApi(1, -1, album.albumId);
			if (res && res.data.type === ResponseType.SUCCESS) {
				musicStore.setMusicList(res.data.pageList);
			}
		},
		async addAlbumToPlaylist(album: Album) {
			const musicStore = useMusicStore();
			let res = await getMusicsByAlbumIdPageApi(1, -1, album.albumId);
			if (res && res.data.type === ResponseType.SUCCESS) {
				musicStore.setMusicList(res.data.pageList, true);
			}
		}
	}
});

// 2.使用容器中的state

// 3.修改state

// 4.使用 action
