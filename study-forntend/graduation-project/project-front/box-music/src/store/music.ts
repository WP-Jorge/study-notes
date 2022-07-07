import { Music } from '@/networks/music';
import { defineStore } from 'pinia';
import { GlobalValues, PlayTypes, ResourceType } from '@/globals/GlobalValues';
import { getResourceUrl } from '@/utils/fileUtil';
import { Singer } from '@/networks/singer';
import { Category } from '@/networks/category';
import { ChartType } from '@/pages/discovery/Chart/components/ChartSelectorCard/index.vue';
import { usePlayOrderChange } from '@/components/content/MusicBar/components/MusicBarCenter/usePlayOrderChange';

export const useMusicStore = defineStore('music', {
	state: () => {
		return {
			musicList: [] as Music[],
			playMusic: {
				audio: null as unknown as HTMLAudioElement,
				music: {} as Music,
				play: false,
				currentTime: 0,
				controls: false,
				showSlider: false,
				progress: 0,
				currentIndex: 0,
				isDragging: false,
				muted: localStorage.getItem('muted') === 'true' ? true : false,
				volume: parseFloat(
					localStorage.getItem('volume') ??
						localStorage.getItem('volume') ??
						'0.1'
				),
				tempVolume: parseFloat(localStorage.getItem('tempVolume') ?? '0.1'),
				playTypeIndex: parseInt(localStorage.getItem('playTypeIndex') ?? '0'),
				playOrders: [] as number[],
				shouldChangeMusic: true
			},
			category: {} as Category,
			chartType: {} as ChartType
		};
	},
	getters: {
		isRandom: state => {
			return (
				PlayTypes[state.playMusic.playTypeIndex] === GlobalValues.PLAY_RANDOM
			);
		}
	},
	actions: {
		setMusic(music: Music) {
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
				if (!item.singerPic.startsWith('http')) {
					item.singerPic = getResourceUrl(
						item.singerPic,
						ResourceType.SINGER_PICTURE
					);
				}
			});
			this.playMusic.music = music;
			nextTick(() => {
				if (this.playMusic.audio) {
					this.playMusic.audio.play();
					this.playMusic.play = true;
				}
			});
		},
		setMusicList(musicList: Music[], flag?: boolean) {
			const playOrderChange = usePlayOrderChange();
			if (!flag) {
				this.musicList = musicList;
				if (musicList.length) {
					this.setMusic(musicList[0]);
					playOrderChange();
				} else {
					this.setMusic({} as Music);
				}
				return;
			}
			this.musicList.push(...musicList);
			playOrderChange();
		}
	}
});
