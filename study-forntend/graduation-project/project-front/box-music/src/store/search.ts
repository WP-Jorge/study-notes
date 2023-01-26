import { Music } from '@/networks/music';
import { defineStore } from 'pinia';

export const useSearchStore = defineStore('search', {
	state: () => {
		return {
			keyword: '',
			searchMusicList: [] as Music[],
			getMusicList: null as unknown as () => any
		};
	}
});
