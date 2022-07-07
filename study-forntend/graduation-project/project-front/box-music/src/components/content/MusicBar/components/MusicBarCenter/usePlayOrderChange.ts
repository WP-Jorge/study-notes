import { useMusicStore } from '@/store/music';
import { shuffle } from '@/utils/baseUtil';

export const usePlayOrderChange = () => {
	const musicStore = useMusicStore();
	const playOrdersChange = () => {
		musicStore.playMusic.shouldChangeMusic = false;
		if (musicStore.isRandom) {
			let changedOrder = shuffle(musicStore.musicList.length);
			musicStore.playMusic.playOrders = changedOrder;
			return;
		}
		musicStore.playMusic.playOrders = musicStore.musicList.map(
			(_, index) => index
		);
	};
	return playOrdersChange;
};
