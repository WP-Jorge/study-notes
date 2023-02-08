import { Music } from '@/networks/music';
import { defineStore } from 'pinia';
import { GlobalValues, PlayTypes, ResourceType } from '@/globals/GlobalValues';
import { getResourceUrl } from '@/utils/fileUtil';
import { Singer } from '@/networks/singer';
import { Category } from '@/networks/category';
import { ChartType } from '@/pages/discovery/Chart/components/ChartSelectorCard/index.vue';
import { usePlayOrderChange } from '@/components/content/MusicBar/components/MusicBarCenter/usePlayOrderChange';
import {
	addMusicToCollectionApi,
	deleteCollectionApi,
	getCollectionApi
} from '@/networks/collection';
import { ResponseType } from '@/globals/ResponseType';
import { ElMessage } from 'element-plus';

export const useMusicStore = defineStore('music', {
	state: () => {
		return {
			musicList: JSON.parse(
				localStorage.getItem('musicList') ?? '[]'
			) as Music[],
			playMusic: {
				audio: null as unknown as HTMLAudioElement,
				music: JSON.parse(localStorage.getItem('music') ?? '{}') as Music,
				play: false,
				currentTime: 0,
				controls: false,
				showSlider: false,
				progress: 0,
				currentIndex: JSON.parse(localStorage.getItem('currentIndex') ?? '0'),
				isDragging: false,
				muted: localStorage.getItem('muted') === 'true' ? true : false,
				volume: parseFloat(localStorage.getItem('volume') ?? '0.1'),
				tempVolume: parseFloat(localStorage.getItem('tempVolume') ?? '0.1'),
				playTypeIndex: parseInt(localStorage.getItem('playTypeIndex') ?? '0'),
				playOrders: [] as number[],
				shouldChangeMusic: true
			},
			category: {} as Category,
			chartType: {} as ChartType,
			recentPlayMusics: JSON.parse(
				localStorage.getItem('recentPlayMusicList') ?? '[]'
			) as Music[],
			favoriteMusics: [] as Music[]
		};
	},
	getters: {
		isRandom: state => {
			return (
				PlayTypes[state.playMusic.playTypeIndex] === GlobalValues.PLAY_RANDOM
			);
		},
		recentPalyMusicList: state => {
			const map = {} as any;
			const res = state.recentPlayMusics
				.filter(item => {
					if (item.musicId && !map[item.musicId as string]) {
						map[item.musicId as string] = true;
						return item;
					}
				})
				.slice(0, 100);
			localStorage.setItem('recentPlayMusicList', JSON.stringify(res));
			return res;
		}
	},
	actions: {
		setMusic(music: Music, isEntity?: boolean) {
			if (!music) {
				return;
			}
			if (isEntity) {
				this.playMusic.music = music;
				localStorage.setItem('music', JSON.stringify(music));
				return;
			}
			console.log('🦃🦃music', music);
			if (!music.local) {
				if (!music.musicUrl.startsWith('http')) {
					music.musicUrl = getResourceUrl(music.musicUrl, ResourceType.MUSIC);
				}
				if (!music.album.albumPic.startsWith('http')) {
					music.album.albumPic = getResourceUrl(
						music.album.albumPic,
						ResourceType.ALBUM_PICTURE
					);
				}
				music.singers.map((item: Singer) => {
					if (item.singerPic && !item.singerPic.startsWith('http')) {
						item.singerPic = getResourceUrl(
							item.singerPic,
							ResourceType.SINGER_PICTURE
						);
					}
				});
			}
			this.playMusic.music = music;
			localStorage.setItem('music', JSON.stringify(music));
			localStorage.setItem(
				'currentIndex',
				JSON.stringify(this.playMusic.currentIndex)
			);
			nextTick(() => {
				if (this.playMusic.audio) {
					this.playMusic.audio.play();
					this.playMusic.play = true;
				}
			});
		},
		setMusicList(musicList: Music[], playNow?: boolean) {
			const playOrderChange = usePlayOrderChange();
			if (!playNow) {
				this.musicList = musicList;
				if (musicList.length) {
					this.setMusic(musicList[0]);
					playOrderChange();
				} else {
					this.setMusic({} as Music, true);
				}
				localStorage.setItem('musicList', JSON.stringify(this.musicList));
				return;
			}
			musicList.forEach(music => {
				const beforeMusicCount = !!this.musicList.length;
				if (!this.musicList.find(item => item.musicId === music.musicId)) {
					this.musicList.push(music);
				}
				if (!beforeMusicCount) {
					this.setMusic(musicList[0]);
				}
				localStorage.setItem('musicList', JSON.stringify(this.musicList));
			});
			playOrderChange();
		},
		async getCollection() {
			const res = await getCollectionApi();
			console.log('🦃🦃res', res);
			if (res.data && res.data.type === ResponseType.SUCCESS) {
				res.data.pageList.map((item: Music) => {
					item.album.albumPic = getResourceUrl(
						item.album.albumPic,
						ResourceType.ALBUM_PICTURE
					);
					return item;
				});
				this.favoriteMusics = res.data.pageList;
			}
		},
		async addCollection(musics?: Music[]) {
			console.log('🦃🦃musics', musics);
			let requestParam = [this.playMusic.music.musicId] as string[];
			if (musics) {
				requestParam =
					(musics.map(item => {
						if (item.musicId) {
							return item.musicId;
						}
					}) as string[]) ?? [];
			}
			const res = await addMusicToCollectionApi(requestParam);
			// console.log('🦃🦃res', res);
			if (res.data && res.data.type === ResponseType.SUCCESS) {
				ElMessage.success(res.data.msg);
				this.getCollection();
			} else {
				ElMessage.error(res.data.msg);
			}
		},
		async deleteCollection(musics?: Music[]) {
			console.log('🦃🦃musics', musics);
			let requestParam = [this.playMusic.music.musicId] as string[];
			if (musics) {
				requestParam =
					(musics.map(item => {
						if (item.musicId) {
							return item.musicId;
						}
					}) as string[]) ?? [];
			}
			const res = await deleteCollectionApi(requestParam);
			// console.log('🦃🦃res', res);
			if (res.data && res.data.type === ResponseType.SUCCESS) {
				ElMessage.success(res.data.msg);
				this.getCollection();
			} else {
				ElMessage.error(res.data.msg);
			}
		}
	}
});
