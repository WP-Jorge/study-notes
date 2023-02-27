<script setup lang="ts">
import { DownloadItemInfo } from '@/store/download';
import { useLocalMusicStore } from '@/store/localMusic';
import { Music } from '@/networks/music';
import { getFormatTime } from '@/utils/mathUtil';
import { MessageType } from '@/globals/GlobalValues';
import { getAssetsFileUrl, getMusicLevel, getRawLyric } from '@/utils/fileUtil';
import { Album } from '@/networks/album';
import { Singer } from '@/networks/singer';
import { useContextMenu } from '@/components/common/ContextMenu/hooks/useContextMenu';
const localMusicStore = useLocalMusicStore();

const ipcRenderer = window.ipcRenderer;
const { readDir, isDir, getMusicInfo, parsePath } = window.electronApis;

const selectMusicDir = () => {
	ipcRenderer.send(MessageType.OPEN_DIR);
};

const getLocalMusic = async (dir: string) => {
	const musics = [] as string[];
	function isMusic(filename: string) {
		const musicType = ['flac', 'mp3', 'wav', 'ape', 'aac', 'ogg'];
		const filenameArr = filename.split('.');
		return musicType.includes(
			filenameArr[filenameArr.length - 1].toLowerCase()
		);
	}
	async function getAllMusicFromDir(dir: string) {
		const dirFiles = await readDir(dir);
		for (const filename of dirFiles) {
			const filepath = dir + '/' + filename;
			const flag = await isDir(filepath);
			if (flag) {
				await getAllMusicFromDir(filepath);
			} else {
				if (isMusic(filepath)) {
					musics.push(filepath);
					// await getMusicInfo(filepath, (musicInfo: any) => {
					// 	musics.push(musicInfo);
					// });
				}
			}
		}
	}
	async function readMusicInfo(musicPaths: string[]) {
		localMusicStore.localMusicList = [];
		for (let i = 0; i < musicPaths.length; i += 3) {
			const musicPathsTemp = [] as string[];
			for (const musicPath of musicPaths.slice(i, i + 3)) {
				musicPathsTemp.push(getMusicInfo(musicPath));
			}
			const rawLocalMusicList = [...(await Promise.all(musicPathsTemp))];
			console.log('🦃🦃rawLocalMusicList', rawLocalMusicList);
			const formatedLocalMusicList = formatMusics(rawLocalMusicList);
			localMusicStore.localMusicList.push(...formatedLocalMusicList);
			localStorage.setItem(
				'localMusicList',
				JSON.stringify(localMusicStore.localMusicList)
			);
		}
	}
	function formatMusics(rawLocalMusicList: any[]) {
		const formatedMusics = [] as Music[];
		rawLocalMusicList.map(item => {
			const music = {} as Music;

			music.musicId = parseInt(Math.random() * 1000000 + '') + '';

			music.album = {} as Album;
			music.album.albumName = item.tags?.album;
			music.album.albumPic = item.img;

			music.bitrate = item.bit_rate;
			music.duration = item.duration;
			music.level = getMusicLevel(item.bit_rate);
			music.lyric = getRawLyric(item.tags?.lyric ?? '');
			music.musicTitle = item.tags?.title ?? parsePath(item.filename).name;
			music.local = true;

			music.singers = [] as Singer[];
			const singerNames = item.tags?.artist?.split(',') ?? [];
			for (const singerName of singerNames) {
				const singer = {} as Singer;
				singer.singerName = singerName;
				music.singers.push(singer);
			}

			music.musicUrl = item.filename;

			music.downloadItemInfo = {} as DownloadItemInfo;
			music.downloadItemInfo.localPath = item.filename;

			formatedMusics.push(music);
		});
		return formatedMusics;
	}
	await getAllMusicFromDir(dir);
	await readMusicInfo(musics);
};
// getLocalMusic(localMusicStore.musicDir);

const contextMneu = useContextMenu({
	playMusic: true,
	openfolder: true,
	addMusicToPlaylist: true,
	removeFromList: true
});

const open = (row: Music, cloumn: any, e: PointerEvent) => {
	contextMneu.openContextMenu(e, row, localMusicStore.localMusicList);
};

ipcRenderer.on(MessageType.OPEN_DIR, (e: any, msg: any) => {
	if (msg) {
		localMusicStore.musicDir = msg;
		localStorage.setItem('musicDir', msg);
		getLocalMusic(msg);
	}
});
</script>
<template>
	<div class="local-music">
		<el-input
			v-model="localMusicStore.musicDir"
			disabled
			class="music-dir-input"
			size="large"
			placeholder="请选择本地目录" />
		<el-button @click="selectMusicDir">选择本地目录</el-button>
		<el-button @click="getLocalMusic(localMusicStore.musicDir)">
			重新扫描
		</el-button>
		<SimpleContainer title="本地音乐">
			<template #content>
				<el-table
					:data="localMusicStore.localMusicList"
					@row-contextmenu="open"
					@row-dblclick="contextMneu.menuFunctions.playMusic">
					<el-table-column #default="{ row: music }" label="封面" :width="80">
						<div class="music-img">
							<img
								:src="
									music.album.albumPic
										? music.album.albumPic
										: getAssetsFileUrl('images', '音符.jpg')
								"
								:alt="music.musicTitle" />
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
.local-music {
	.music-dir-input {
		margin-right: 10px;
		width: 250px;
		height: 32px;
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
