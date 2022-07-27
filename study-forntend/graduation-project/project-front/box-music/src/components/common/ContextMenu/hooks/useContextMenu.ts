import { ResponseType } from '@/globals/ResponseType';
import { getMusicsByPlaylistIdPageApi, Music } from '@/networks/music';
import { Playlist } from '@/networks/playlist';
import { useMusicStore } from '@/store/music';
import { useContextMenuStore } from '@/store/contextMenu';
import { useDownloadStore } from '@/store/download';
import { ContextMenuType } from '@/globals/GlobalValues';
import { ContextMenuItem } from '@/store/contextMenu';
import { Singer } from '@/networks/singer';
const musicStore = useMusicStore();
const menuStore = useContextMenuStore();
const downloadStore = useDownloadStore();
const electronApis = window.electronApis;

export interface ContextMenuOptions {
	contextMenuType: ContextMenuType;
	playPlaylist?: boolean;
	addToPlaylist?: boolean;
	downloadOne?: boolean;
	pauseOne?: boolean;
	resumeOne?: boolean;
	cancelOne?: boolean;
	removeFromHistoryList?: boolean;
	removeFromComputer?: boolean;
	openfolder?: boolean;
	[x: string]: any;
}
interface MenuTemplates {
	[x: string]: ContextMenuItem;
}

interface menuFunction {
	contextMenuType: ContextMenuType;
	callback: (payload: Music | Singer | Playlist) => any;
}

interface menuFunctions {
	[x: string]: menuFunction;
}

const contextMenuOptions = {
	contextMenu: ContextMenuType.MUSIC,
	downloadOne: true
};

export const useContextMenu = (options: ContextMenuOptions) => {
	options = { ...contextMenuOptions, ...options };
	const menuFunctions = {
		playPlaylist: {
			contextMenuType: ContextMenuType.PLAYLIST,
			callback: async (playlist: Playlist) => {
				let res = await getMusicsByPlaylistIdPageApi(
					1,
					-1,
					playlist.playlistId
				);
				if (res && res.data.type === ResponseType.SUCCESS) {
					musicStore.setMusicList(res.data.pageList);
				}
			}
		},
		addToPlaylist: {
			contextMenuType: ContextMenuType.PLAYLIST,
			callback: async (playlist: Playlist) => {
				let res = await getMusicsByPlaylistIdPageApi(
					1,
					-1,
					playlist.playlistId
				);
				if (res && res.data.type === ResponseType.SUCCESS) {
					musicStore.setMusicList(res.data.pageList, true);
				}
			}
		},
		downloadOne: {
			contextMenuType: ContextMenuType.MUSIC,
			callback: (music: Music) => {
				downloadStore.startDownloadOne(music);
			}
		},
		pauseOne: {
			contextMenuType: ContextMenuType.MUSIC,
			callback: (music: Music) => {
				downloadStore.pauseOne(music);
			}
		},
		resumeOne: {
			contextMenuType: ContextMenuType.MUSIC,
			callback: (music: Music) => {
				downloadStore.pauseOne(music);
			}
		},
		cancelOne: {
			contextMenuType: ContextMenuType.MUSIC,
			callback: (music: Music) => {
				downloadStore.cancelOne(music);
			}
		},
		removeFromHistoryList: {
			contextMenuType: ContextMenuType.MUSIC,
			callback: (music: Music) => {
				downloadStore.removeFromHistoryList(music);
			}
		},
		removeFromComputer: {
			contextMenuType: ContextMenuType.MUSIC,
			callback: (music: Music) => {
				downloadStore.removeFromComputer(music);
			}
		},
		openfolder: {
			contextMenuType: ContextMenuType.MUSIC,
			callback: (music: Music) => {
				electronApis.openfolder(music);
			}
		}
	} as menuFunctions;
	const openContextMenu = (
		e: PointerEvent,
		payload: Music | Playlist | Singer
	) => {
		const menuTemplates = {
			playPlaylist: {
				type: 'li',
				title: '播放歌单',
				callback: () => menuFunctions.playPlaylist.callback
			},
			addToPlaylist: {
				type: 'li',
				title: '添加至播放列表',
				callback: () => menuFunctions.addToPlaylist.callback
			},
			downloadOne: {
				type: 'li',
				title: '下载',
				disabled: !downloadStore.downloadable(payload as Music),
				callback: () => menuFunctions.downloadOne.callback
			},
			resumeOne: {
				type: 'li',
				title: '继续下载',
				disabled: payload.downloadItemInfo?.state === 'progressing',
				callback: () => menuFunctions.resumeOne.callback
			},
			pauseOne: {
				type: 'li',
				title: '暂停',
				disabled: payload.downloadItemInfo?.state === 'paused',
				callback: () => menuFunctions.pauseOne.callback
			},
			cancelOne: {
				type: 'li',
				title: '从下载列表中移除',
				callback: () => menuFunctions.cancelOne.callback
			},
			removeFromHistoryList: {
				type: 'li',
				title: '从完成列表中移除',
				callback: () => menuFunctions.removeFromHistoryList.callback
			},
			removeFromComputer: {
				type: 'li',
				title: '从完成列表中移除并删除本地文件',
				callback: () => menuFunctions.removeFromComputer.callback
			},
			openfolder: {
				type: 'li',
				title: '打开文件所在文件夹',
				callback: () => menuFunctions.openfolder.callback
			}
		} as MenuTemplates;
		const contextMenuList = [];
		for (const key in menuTemplates) {
			if (Object.prototype.hasOwnProperty.call(menuTemplates, key)) {
				const element = menuTemplates[key];
				console.log('🦃🦃element', element);
				console.log('🦃🦃options[key]', options[key]);
				console.log(
					'🦃🦃menuFunctions[key].contextMenuType',
					menuFunctions[key].contextMenuType
				);
				if (
					options[key] &&
					menuFunctions[key].contextMenuType === options['contextMenuType']
				) {
					contextMenuList.push(element);
				}
			}
		}
		menuStore.openContextMenu({ event: e, args: payload, contextMenuList });
	};
	return { openContextMenu, menuFunctions };
};
