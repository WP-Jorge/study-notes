import { MessageType } from '../globalValues/MessageType';
import {
	app,
	BrowserWindow,
	CreateInterruptedDownloadOptions,
	DownloadItem,
	Event,
	ipcMain,
	session
} from 'electron';

import Store from 'electron-store';
import { Music } from '../globalValues/Type';
import { getMd5 } from '../preload/apis/utilApis';
import { GlobalURL } from '../globalValues/GlobalURL';

interface InterruptedDownloadOptions extends CreateInterruptedDownloadOptions {
	music: Music;
}

interface InterruptedDownloadOptionsList {
	[x: string]: InterruptedDownloadOptions;
}

const store = new Store();
console.log('🦃🦃store.getPath()', store.path);
export interface DownloadItemInfo {
	state: string;
	speed: number;
	progress: number;
	receivedBytes: number;
	totalBytes: number;
	lastModifiedTime: string;
	musicId: string;
	localPath: string;
}
// interface MusicListObject {
// 	[x: string]: Music;
// }
// interface DownloadItemType {
// 	[x: string | number]: DownloadItem;
// }
// type DeleteMapType = {
// 	[x: string]: boolean;
// };
interface DownloadListenerType {
	[s: string]: (
		event: Electron.Event,
		item: Electron.DownloadItem,
		webContents: Electron.WebContents
	) => void;
}
// const downloadItems = {} as DownloadItemType;
// const musicList = {} as MusicListObject;
store.onDidChange('downloadCacheList', newValue => {
	console.log('🦃🦃downloadCacheList', newValue);
});
const downloadTempPath = store.get(
	'downloadTempPath',
	GlobalURL.CLIENT_DOWNLOAD_PATH
);
const listeners = {} as DownloadListenerType;
let downloadCacheList = store.get(
	'downloadCacheList',
	{}
) as InterruptedDownloadOptionsList;
export const registerDownloadListener = (mainWindow: BrowserWindow) => {
	app.on('window-all-closed', function () {
		if (process.platform !== 'darwin') {
			for (const key in downloadCacheList) {
				if (Object.prototype.hasOwnProperty.call(downloadCacheList, key)) {
					const element = downloadCacheList[key];
					element.music.downloadItemInfo.state = 'paused';
				}
			}
			store.set('downloadCacheList', downloadCacheList);
			mainWindow.destroy();
			process.kill(process.pid, 'SIGKILL');
		}
	});
	ipcMain.on(MessageType.DOWNLOAD_START, (e, music: Music) => {
		const musicSign = getMd5(music.musicUrl);
		console.log('🦃🦃musicSign', musicSign);
		if (!listeners[musicSign]) {
			listeners[musicSign] = (event: Event, item: DownloadItem) => {
				if (!music || getMd5(item.getURL()) !== musicSign) {
					return;
				}
				const path = `${downloadTempPath}${item.getFilename()}`;
				// const path = `C:\\Users\\Admin\\Desktop\\temp\\${item.getFilename()}`;
				item.setSavePath(path);
				const downloadItemInfo = {} as DownloadItemInfo;
				// 记录上一次下载的字节数据
				let prevReceivedBytes = 0;
				const updateListener = (e: Event, state: string) => {
					if (state === 'interrupted') {
						console.log('下载已经中断，可以恢复');
						item.resume();
					} else if (state === 'progressing') {
						if (item.isPaused()) {
							console.log('暂停下载');
						}
						getDownloadInfoItem(
							item,
							downloadItemInfo,
							music.musicId as string,
							prevReceivedBytes
						);
						prevReceivedBytes = item.getReceivedBytes();
						downloadCacheList[musicSign] = getDownloadCache(
							item,
							music,
							downloadItemInfo
						);
						!mainWindow.isDestroyed() &&
							mainWindow.setProgressBar(downloadItemInfo.progress);
						!mainWindow.isDestroyed() &&
							mainWindow.webContents.send(
								`${MessageType.DOWNLOAD_UPDATE}-${musicSign}`,
								downloadItemInfo
							);
					}
				};
				const doneListener = (e: Event, state: string) => {
					// 通知渲染进程，更新下载状态
					!mainWindow.isDestroyed() && mainWindow.setProgressBar(-1);
					if (state === 'interrupted') {
						console.log('下载已经中断，无法恢复');
					} else if (state === 'cancelled') {
						console.log('下载取消');
					} else {
						console.log('🦃🦃state', state);
						getDownloadInfoItem(
							item,
							downloadItemInfo,
							music.musicId as string,
							undefined,
							path
						);
						!mainWindow.isDestroyed() &&
							mainWindow.webContents.send(
								`${MessageType.DOWNLOAD_FINISH}-${musicSign}`,
								downloadItemInfo
							);
					}
					removeListeners(item, musicSign);
				};
				ipcMain.on(`${MessageType.DOWNLOAD_PAUSE}-${musicSign}`, () => {
					item.pause();
				});
				ipcMain.on(`${MessageType.DOWNLOAD_RESUME}-${musicSign}`, () => {
					if (item.canResume()) {
						item.resume();
					}
				});
				ipcMain.on(`${MessageType.DOWNLOAD_CANCEL}-${musicSign}`, () => {
					item.cancel();
				});
				item.on('updated', updateListener);
				item.once('done', doneListener);
			};
		}
		session.defaultSession.on(MessageType.WILL_DOWNLOAD, listeners[musicSign]);
		if (downloadCacheList && downloadCacheList[musicSign]) {
			session.defaultSession.createInterruptedDownload(
				downloadCacheList[musicSign]
			);
			return;
		}
		session.defaultSession.downloadURL(music.musicUrl);
	});
	ipcMain.on(MessageType.DOWNLOAD_RESTART, () => {
		restartDownloadCache(mainWindow);
	});
};

const getDownloadInfoItem = (
	item: DownloadItem,
	downloadItemInfo: DownloadItemInfo,
	musicId: string,
	prevReceivedBytes?: number,
	localPath?: string
) => {
	const receivedBytes = item.getReceivedBytes();
	let speed = 0;
	if (prevReceivedBytes !== undefined && prevReceivedBytes !== null) {
		speed = receivedBytes - prevReceivedBytes;
	}
	const progress = receivedBytes / item.getTotalBytes();
	let state = '';
	if (item.getState() === 'completed') {
		state = 'completed';
	} else {
		state = item.isPaused() ? 'paused' : item.getState();
	}
	downloadItemInfo.state = state;
	downloadItemInfo.speed = speed > 0 ? speed : 0;
	downloadItemInfo.receivedBytes = receivedBytes;
	downloadItemInfo.progress = progress;
	downloadItemInfo.totalBytes = item.getTotalBytes();
	downloadItemInfo.lastModifiedTime = item.getLastModifiedTime();
	downloadItemInfo.musicId = musicId;
	localPath && (downloadItemInfo.localPath = localPath);
};

const getDownloadCache = (
	item: DownloadItem,
	music: Music,
	downloadItemInfo: DownloadItemInfo
) => {
	const downloadCache = {} as InterruptedDownloadOptions;
	downloadCache.path = item.getSavePath();
	downloadCache.urlChain = item.getURLChain();
	downloadCache.offset = item.getReceivedBytes();
	downloadCache.length = item.getTotalBytes();
	downloadCache.lastModified = item.getLastModifiedTime();
	downloadCache.eTag = item.getETag();
	downloadCache.startTime = item.getStartTime();
	music.downloadItemInfo = downloadItemInfo;
	downloadCache.music = music;
	return downloadCache;
};

const removeListeners = (item: DownloadItem, musicSign: string) => {
	console.log('delete');
	delete downloadCacheList[musicSign];
	item.removeAllListeners();
	session.defaultSession.off('will-download', listeners[musicSign]);
	ipcMain.removeAllListeners(`${MessageType.DOWNLOAD_PAUSE}-${musicSign}`);
	ipcMain.removeAllListeners(`${MessageType.DOWNLOAD_CANCEL}-${musicSign}`);
	ipcMain.removeAllListeners(`${MessageType.DOWNLOAD_RESUME}-${musicSign}`);
};

const restartDownloadCache = (mainWindow: BrowserWindow) => {
	const musics = [] as Music[];
	for (const key in downloadCacheList) {
		if (Object.prototype.hasOwnProperty.call(downloadCacheList, key)) {
			const item = downloadCacheList[key] as InterruptedDownloadOptions;
			musics.push(item.music);
		}
	}
	mainWindow.webContents.send(MessageType.DOWNLOAD_RESTART, musics);
	for (const key in downloadCacheList) {
		if (Object.prototype.hasOwnProperty.call(downloadCacheList, key)) {
			const item = downloadCacheList[key] as InterruptedDownloadOptions;
			session.defaultSession.createInterruptedDownload(item);
		}
	}
};
