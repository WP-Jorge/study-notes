import { Music } from '@/networks/music';
import { defineStore } from 'pinia';

export const useLocalMusicStore = defineStore('localMusic', {
	state: () => {
		return {
			musicDir: localStorage.getItem('musicDir') ?? '',
			localMusicList: JSON.parse(
				localStorage.getItem('localMusicList') ?? '[]'
			) as Music[]
		};
	}
});
