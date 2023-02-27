import { MessageType, ResourceType } from '@/globals/GlobalValues';
import { Music } from '@/networks/music';
import { Singer } from '@/networks/singer';
import { getResourceUrl } from '@/utils/fileUtil';
import { IpcRenderer, IpcRendererEvent } from 'electron';
import { ElMessage } from 'element-plus';
import { defineStore } from 'pinia';
import { GlobalURL } from '../../electron/globalValues/GlobalURL';
const ipcRenderer = window.ipcRenderer as IpcRenderer;
const electronApis = window.electronApis;
const {
	writeinMusicInfo,
	getData,
	getStore,
	clearStore,
	deleteFile,
	downloadFile
} = window.electronApis;
export interface DownloadItemInfo {
	state: string;
	speed: number;
	progress: number;
	receivedBytes: number;
	totalBytes: number;
	lastModifiedTime: string;
	localPath: string;
}

export type MessageChannelType = {
	[x: string]: MessageChannel;
};

export const useDownloadStore = defineStore('download', {
	state: () => {
		return {
			// waitQueue: getData('waitQueue', []) as Music[],
			downloadQueue: getData('downloadQueue', []) as Music[],
			downloadHistoryList: getData('downloadHistoryList', []) as Music[],
			isAllPaused: false,
			// downloadTempMusic: {} as Music,
			// pauseQueue: [] as Music[],
			store: getStore(),
			messageChannelTypes: {} as MessageChannelType,
			downloadPath: GlobalURL.CLIENT_DOWNLOAD_PATH,
			downloadTempPath: getData(
				'downloadTempPath',
				GlobalURL.CLIENT_DOWNLOAD_TEMP_PATH
			)
		};
	},
	/**
	 * 类似组件的 methods，封装业务逻辑，修改 state
	 */
	actions: {
		sendMsgToMain(messageType: string, msgs?: any) {
			ipcRenderer.send(messageType, JSON.parse(JSON.stringify(msgs ?? '')));
		},
		async invokeMsgToMain(messageType: string, msgs?: any) {
			return await ipcRenderer.invoke(messageType, msgs);
		},
		getFormatMusic(music: Music) {
			if (!music.musicUrl.startsWith('http')) {
				music.musicUrl = getResourceUrl(music.musicUrl, ResourceType.MUSIC);
			}
			music.musicUrl = encodeURI(music.musicUrl);
			if (!music.album.albumPic.startsWith('http')) {
				music.album.albumPic = getResourceUrl(
					music.album.albumPic,
					ResourceType.ALBUM_PICTURE
				);
			} else {
				music.album.albumPic = encodeURI(music.album.albumPic);
			}
			music.singers.map((item: Singer) => {
				if (!item.singerPic.startsWith('http')) {
					item.singerPic = getResourceUrl(
						item.singerPic,
						ResourceType.SINGER_PICTURE
					);
				}
				item.singerPic = encodeURI(item.singerPic);
			});
			if (music.downloadItemInfo) {
				return music;
			}
			const downloadItemInfo = {} as DownloadItemInfo;
			downloadItemInfo.speed = 0;
			downloadItemInfo.state = MessageType.DOWNLOAD_WAIT;
			downloadItemInfo.receivedBytes = 0;
			downloadItemInfo.totalBytes = music.size as number;
			downloadItemInfo.progress = 0;
			music.downloadItemInfo = downloadItemInfo;
			return music;
		},
		downloadable(music: Music) {
			return (
				!this.downloadQueue.find(item => item.musicId === music.musicId) &&
				!this.downloadHistoryList.find(item => item.musicId === music.musicId)
			);
		},
		addToWaitQueue(music: Music) {
			// this.downloadTempMusic = music;
			if (!this.downloadable(music)) {
				return console.log('添加失败，音乐已下载');
			}
			// this.waitQueue.push(this.getFormatMusic(music));
			console.log('🦃🦃getData(waitQueue)', getData('waitQueue'));
		},
		startAll() {
			this.downloadQueue.map(item => {
				this.resumeOne(item);
			});
		},
		pauseAll() {
			this.downloadQueue.map(item => {
				this.pauseOne(item);
			});
		},
		cancelAll() {
			// const formatMusics = [] as Music[];
			this.downloadQueue.map(item => {
				const formatMusic = this.getFormatMusic(item);
				const musicSign = electronApis.getMd5(formatMusic.musicUrl);
				this.sendMsgToMain(`${MessageType.DOWNLOAD_CANCEL}-${musicSign}`);
				removeListeners(musicSign);
			});
			this.downloadQueue.splice(0);
		},
		startDownloadOne(music: Music) {
			// this.downloadTempMusic = music;
			if (!this.downloadable(music)) {
				return ElMessage.warning('添加失败，音乐已下载');
			}
			ElMessage.success('已添加至下载列表');
			startDownloadOne(music);
		},
		pauseOne(music: Music) {
			if (music.downloadItemInfo?.state === 'paused') {
				return;
			}
			let formatMusic = this.getFormatMusic(music);
			const musicSign = electronApis.getMd5(formatMusic.musicUrl);
			this.sendMsgToMain(`${MessageType.DOWNLOAD_PAUSE}-${musicSign}`);
		},
		resumeOne(music: Music) {
			let formatMusic = this.getFormatMusic(music);
			const musicSign = electronApis.getMd5(formatMusic.musicUrl);
			this.sendMsgToMain(`${MessageType.DOWNLOAD_RESUME}-${musicSign}`);
		},
		cancelOne(music: Music) {
			let formatMusic = this.getFormatMusic(music);
			const musicSign = electronApis.getMd5(formatMusic.musicUrl);
			this.sendMsgToMain(`${MessageType.DOWNLOAD_CANCEL}-${musicSign}`);
			let index = this.downloadQueue.findIndex(
				item => item.musicId === music.musicId
			);
			if (index !== -1) {
				this.downloadQueue.splice(index, 1);
			}
			removeListeners(musicSign);
		},
		removeFromHistoryList(music: Music) {
			const index = this.downloadHistoryList.findIndex(
				item => item.musicId === music.musicId
			);
			this.downloadHistoryList.splice(index, 1);
		},
		async removeFromComputer(music: Music, flag?: boolean) {
			try {
				let isMusicDeleted = await deleteFile(
					music.downloadItemInfo?.localPath
				);
				let isPictureDeleted = await deleteFile(music.album.albumPic);
				!flag && this.removeFromHistoryList(music);
				return isMusicDeleted && isPictureDeleted;
			} catch (e) {
				console.error('🦃🦃e', e);
				return false;
			}
		},
		async removeFile(filepath: string) {
			try {
				let isFileDeleted = await deleteFile(filepath);
				return isFileDeleted;
			} catch (e) {
				console.error('🦃🦃e', e);
				return false;
			}
		},
		clearHistroyList() {
			// this.downloadHistoryList.map(item => {
			// 	this.removeFromHistoryList(item);
			// });
			for (let i = this.downloadHistoryList.length - 1; i >= 0; i--) {
				const item = this.downloadHistoryList[i];
				this.removeFromHistoryList(item);
			}
		},
		async clearHistoryListAndComputerFiles() {
			try {
				for (let i = this.downloadHistoryList.length - 1; i >= 0; i--) {
					const item = this.downloadHistoryList[i];
					await this.removeFromComputer(item);
				}
				return true;
			} catch (e) {
				console.log('🦃🦃e', e);
				return false;
			}
		},
		clearStore() {
			clearStore();
		}
	}
});

const removeAllListeners = () => {
	// ipcRenderer.removeAllListeners();
	// ipcRenderer.removeAllListeners(MessageType.DOWNLOAD_UPDATE);
	// ipcRenderer.removeAllListeners(MessageType.DOWNLOAD_PAUSE);
	// ipcRenderer.removeAllListeners(MessageType.DOWNLOAD_FINISH);
};

ipcRenderer.on(
	MessageType.DOWNLOAD_RESTART,
	(event: IpcRendererEvent, musics: Music[]) => {
		for (const music of musics) {
			startDownloadOne(music);
		}
	}
);
ipcRenderer.send(MessageType.DOWNLOAD_RESTART);

const startDownloadOne = (music: Music) => {
	const downloadStore = useDownloadStore();
	let formatMusic = downloadStore.getFormatMusic(music);
	const musicSign = electronApis.getMd5(formatMusic.musicUrl);
	downloadStore.downloadQueue.push(formatMusic);
	downloadStore.sendMsgToMain(MessageType.DOWNLOAD_START, formatMusic);
	ipcRenderer.on(
		`${MessageType.DOWNLOAD_UPDATE}-${musicSign}`,
		(event: IpcRendererEvent, msg: any) => {
			const downloadMusic = downloadStore.downloadQueue.find(
				item => item.musicId === msg.musicId
			);
			console.log('🦃🦃msg', msg);
			downloadMusic && (downloadMusic.downloadItemInfo = msg);
		}
	);
	ipcRenderer.on(
		`${MessageType.DOWNLOAD_FINISH}-${musicSign}`,
		(event: IpcRendererEvent, msg: any) => {
			const music = downloadStore.downloadQueue.find(
				item => item.musicId === msg.musicId
			);
			if (!music) {
				return;
			}
			const downloadMusic = music;
			downloadMusic.downloadItemInfo = msg;
			downloadMusic.local = true;
			if (downloadMusic.downloadItemInfo?.state !== 'completed') {
				return;
			}

			writeinMusicInfo(
				downloadStore.downloadTempPath +
					downloadMusic.musicId +
					'.' +
					downloadMusic.musicFormat,
				downloadStore.downloadPath,
				JSON.parse(JSON.stringify(downloadMusic)),
				() => {
					(downloadMusic.downloadItemInfo as DownloadItemInfo).state =
						'writing';
				},
				() => {
					(downloadMusic.downloadItemInfo as DownloadItemInfo).state =
						'writing';
				},
				async () => {
					(downloadMusic.downloadItemInfo as DownloadItemInfo).state = 'writed';
					const index = downloadStore.downloadQueue.findIndex(
						item => item.musicId === msg.musicId
					);
					const deleteItems = downloadStore.downloadQueue.splice(index, 1);
					for (const music of deleteItems) {
						music.album.albumPic = await downloadFile(
							music.album.albumPic,
							GlobalURL.CLIENT_BASE + GlobalURL.CLIENT_DOWNLOAD_PICTURES,
							music.musicTitle + '.jpg'
						);
					}
					downloadStore.downloadHistoryList.unshift(...deleteItems);
					removeListeners(musicSign);
					downloadStore.removeFile(
						downloadMusic.downloadItemInfo?.localPath as string
					);
					(downloadMusic.downloadItemInfo as DownloadItemInfo).localPath =
						downloadStore.downloadPath +
						downloadMusic.musicTitle +
						'.' +
						downloadMusic.musicFormat;
				}
			);
		}
	);
};

// const watcher = () => {
// 	const downloadStore = useDownloadStore();
// 	const downloadHistoryListChange = () => {
// 		downloadStore.$subscribe(
// 			async () => {
// 				const tempMusics = JSON.parse(
// 					JSON.stringify(downloadStore.downloadHistoryList)
// 				);
// 				setData('downloadHistoryList', JSON.parse(JSON.stringify(tempMusics)));
// 			},
// 			{ detached: true, deep: true }
// 		);
// 	};
// 	downloadHistoryListChange();
// };

const removeListeners = (musicSign: string) => {
	ipcRenderer.removeAllListeners(`${MessageType.DOWNLOAD_UPDATE}-${musicSign}`);
	ipcRenderer.removeAllListeners(`${MessageType.DOWNLOAD_FINISH}-${musicSign}`);
};

removeAllListeners();
// watcher();
