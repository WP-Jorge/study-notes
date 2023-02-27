import { Music } from '@/networks/music';
import { usePlaylistStore } from '@/store/playlist';
import { useContextMenuStore } from '@/store/contextMenu';
import { useDownloadStore } from '@/store/download';
// import { ContextMenuType } from '@/globals/GlobalValues';
import { ContextMenuItem } from '@/store/contextMenu';
import { useMusicStore } from '@/store/music';
import { usePlayOrderChange } from '@/components/content/MusicBar/components/MusicBarCenter/usePlayOrderChange';
import router from '@/router';
const electronApis = window.electronApis;

export interface ContextMenuOptions {
	playPlaylist?: boolean;
	addPlaylistToPlaylist?: boolean;
	addMusicToPlaylist?: boolean;
	downloadOne?: boolean;
	pauseOne?: boolean;
	resumeOne?: boolean;
	cancelOne?: boolean;
	removeFromHistoryList?: boolean;
	removeFromComputer?: boolean;
	openfolder?: boolean;
	removeFromList?: boolean;
	playMusic?: boolean;
	playAlbum?: boolean;
	addAlbumToPlaylist?: boolean;
	go?: boolean;
	deleteCollection?: boolean;
	addCollection?: boolean;
	[x: string]: any;
}
interface MenuTemplates {
	[x: string]: ContextMenuItem;
}

// interface menuFunction {
// 	callback: (payload: Music | Singer | Playlist | Album, ...args: any) => any;
// }

interface menuFunctions {
	playPlaylist?: any;
	addPlaylistToPlaylist?: any;
	addMusicToPlaylist?: any;
	downloadOne?: any;
	pauseOne?: any;
	resumeOne?: any;
	cancelOne?: any;
	removeFromHistoryList?: any;
	removeFromComputer?: any;
	openfolder?: any;
	removeFromList?: any;
	playMusic?: any;
	playAlbum?: any;
	addAlbumToPlaylist?: any;
	go?: (path: string, payload: any) => any;
	deleteCollection?: any;
	addCollection?: any;
	addPlaylistToCollection?: any;
	deletePlaylistCollection?: any;
	addMusicToMyPlaylist?: any;
	deleteMusicFromPlaylist?: any;
	// [x: string]: (
	// 	payload?: Music | Singer | Playlist | Album,
	// 	...args: any
	// ) => any;
}

const contextMenuOptions = {
	// contextMenuType: ContextMenuType.MUSIC
};

export const useContextMenu = (options = {} as ContextMenuOptions) => {
	const musicStore = useMusicStore();
	const playlistStore = usePlaylistStore();
	const menuStore = useContextMenuStore();
	const downloadStore = useDownloadStore();
	const playOrderChange = usePlayOrderChange();
	options = options
		? { ...contextMenuOptions, ...options }
		: contextMenuOptions;
	// options = { ...contextMenuOptions, ...options };
	const menuFunctions = {
		playPlaylist: playlistStore.playPlaylist,
		addPlaylistToPlaylist: playlistStore.addPlaylistToPlaylist,
		playAlbum: playlistStore.playAlbum,
		addAlbumToPlaylist: playlistStore.addAlbumToPlaylist,
		addMusicToPlaylist: playlistStore.addMusicToPlaylist,
		downloadOne: downloadStore.startDownloadOne,
		pauseOne: downloadStore.pauseOne,
		resumeOne: downloadStore.pauseOne,
		cancelOne: downloadStore.cancelOne,
		removeFromHistoryList: downloadStore.removeFromHistoryList,
		removeFromComputer: downloadStore.removeFromComputer,
		openfolder: (music: Music) =>
			electronApis.openFolder(music.downloadItemInfo?.localPath),
		removeFromList: (music: Music, fromMusicList: Music[]) => {
			const index = fromMusicList.findIndex(
				item => item.musicId === music.musicId
			);
			fromMusicList.splice(index, 1);
			localStorage.setItem('localMusicList', JSON.stringify(fromMusicList));
		},
		playMusic: (music: Music) => {
			if (!musicStore.musicList.includes(music)) {
				musicStore.musicList.push(music);
				localStorage.setItem('musicList', JSON.stringify(musicStore.musicList));
			}
			musicStore.setMusic(music);
			musicStore.recentPlayMusics.unshift(music);
			playOrderChange();
		},
		go: (path: string, payload: any) => {
			router.push({
				path,
				query: payload
			});
		},
		addCollection: musicStore.addCollection,
		deleteCollection: musicStore.deleteCollection,
		addPlaylistToCollection: playlistStore.addPlaylistToCollection,
		deletePlaylistCollection: playlistStore.deletePlaylistCollection,
		addMusicToMyPlaylist: playlistStore.addMusicToMyPlaylist,
		deleteMusicFromPlaylist: playlistStore.deleteMusicFromPlaylist
	} as menuFunctions;
	const openContextMenu = (e: PointerEvent, ...payload: any) => {
		const menuTemplates = {
			playPlaylist: {
				type: 'li',
				title: '播放歌单',
				callback: () => menuFunctions.playPlaylist.apply(null, payload)
			},
			addPlaylistToPlaylist: {
				type: 'li',
				title: '添加歌单至播放列表',
				callback: () => menuFunctions.addPlaylistToPlaylist.apply(null, payload)
			},
			playAlbum: {
				type: 'li',
				title: '播放专辑',
				callback: () => menuFunctions.playAlbum.apply(null, payload)
			},
			addAlbumToPlaylist: {
				type: 'li',
				title: '添加专辑至播放列表',
				callback: () => menuFunctions.addAlbumToPlaylist.apply(null, payload)
			},
			addMusicToPlaylist: {
				type: 'li',
				title: '添加歌曲至播放列表',
				callback: () => {
					menuFunctions.addMusicToPlaylist.apply(null, payload);
				}
			},
			downloadOne: {
				type: 'li',
				title: '下载',
				disabled: !downloadStore.downloadable(payload[0] as Music),
				callback: () => menuFunctions.downloadOne.apply(null, payload)
			},
			resumeOne: {
				type: 'li',
				title: '继续下载',
				disabled: payload[0].downloadItemInfo?.state === 'progressing',
				callback: () => menuFunctions.resumeOne.apply(null, payload)
			},
			pauseOne: {
				type: 'li',
				title: '暂停',
				disabled: payload[0].downloadItemInfo?.state === 'paused',
				callback: () => menuFunctions.pauseOne.apply(null, payload)
			},
			cancelOne: {
				type: 'li',
				title: '从下载列表中移除',
				callback: () => menuFunctions.cancelOne.apply(null, payload)
			},
			removeFromHistoryList: {
				type: 'li',
				title: '从完成列表中移除',
				callback: () => menuFunctions.removeFromHistoryList.apply(null, payload)
			},
			removeFromComputer: {
				type: 'li',
				title: '从完成列表中移除并删除本地文件',
				callback: () => menuFunctions.removeFromComputer.apply(null, payload)
			},
			openfolder: {
				type: 'li',
				title: '打开文件所在文件夹',
				callback: () => menuFunctions.openfolder.apply(null, payload)
			},
			removeFromList: {
				type: 'li',
				title: '从列表中移除',
				callback: () => menuFunctions.removeFromList.apply(null, payload)
			},
			playMusic: {
				type: 'li',
				title: '立即播放',
				callback: () => menuFunctions.playMusic.apply(null, payload)
			},
			go: {
				type: 'li',
				title: '进入详情',
				callback: () => menuFunctions.go?.apply(null, [payload[1], payload[0]])
			},
			addCollection: {
				type: 'li',
				title: '添加至我的喜欢',
				hide: !!musicStore.favoriteMusics.find(
					item => item.musicId == payload?.[0].musicId
				),
				callback: () => menuFunctions.addCollection?.apply(null, [payload])
			},
			deleteCollection: {
				type: 'li',
				title: '从我的喜欢中移除',
				hide: !musicStore.favoriteMusics.find(
					item => item.musicId == payload?.[0].musicId
				),
				callback: () => menuFunctions.deleteCollection?.apply(null, [payload])
			},
			addPlaylistToCollection: {
				type: 'li',
				title: '添加至我的歌单',
				hide: !!playlistStore.collectionPlaylist.find(
					item => item.playlistId == payload?.[0].playlistId
				),
				callback: () => {
					menuFunctions.addPlaylistToCollection?.call(
						null,
						payload[0]?.playlistId
					);
				}
			},
			deletePlaylistCollection: {
				type: 'li',
				title: '从我的歌单中移除',
				hide: !playlistStore.collectionPlaylist.find(
					item => item.playlistId == payload?.[0].playlistId
				),
				callback: () =>
					menuFunctions.deletePlaylistCollection?.call(null, [
						payload[0]?.playlistId
					])
			},
			// musicId 和 playlistId
			deleteMusicFromPlaylist: {
				type: 'li',
				title: '从当前歌单中移除歌曲',
				callback: () => {
					console.log('🦃🦃payload', payload);
					menuFunctions.deleteMusicFromPlaylist?.call(null, {
						musicId: payload[0]?.musicId,
						playlistId: payload[1]?.playlistId
					});
				}
			},
			addMusicToMyPlaylist: {
				type: 'li',
				title: '添加至我的歌单',
				callback: () => {
					console.log('🦃🦃payload', payload);
					menuStore.requestData.musicId = payload[0].musicId;
					menuStore.showSelectPlaylist = true;
				}
			}
		} as MenuTemplates;
		const contextMenuList = [];
		for (const key in menuTemplates) {
			if (Object.prototype.hasOwnProperty.call(menuTemplates, key)) {
				const element = menuTemplates[key];
				if (options[key]) {
					contextMenuList.push(element);
				}
			}
		}
		menuStore.openContextMenu({ event: e, args: payload, contextMenuList });
	};
	return { openContextMenu, menuFunctions };
};
