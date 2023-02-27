import { useMusicStore } from '@/store/music';
import { usePlayOrderChange } from './usePlayOrderChange';

export const useNextHandle = () => {
	const playOrderChange = usePlayOrderChange();
	const musicStore = useMusicStore();
	const nextHandle = () => {
		if (
			musicStore.playMusic?.playOrders.length !== musicStore.musicList?.length
		) {
			playOrderChange();
		}
		musicStore.playMusic.shouldChangeMusic = true;
		const tempMusic = musicStore.playMusic.music;
		musicStore.playMusic.play = true;
		musicStore.playMusic.currentIndex =
			(musicStore.playMusic.currentIndex + 1) % musicStore.musicList.length;
		if (
			musicStore.musicList.length > 1 &&
			tempMusic.musicId ===
				musicStore.musicList[
					musicStore.playMusic.playOrders[musicStore.playMusic.currentIndex]
				].musicId
		) {
			nextHandle();
			return;
		}
		nextTick(() => {
			musicStore.recentPlayMusics.unshift(musicStore.playMusic.music);
			(musicStore.playMusic.audio as unknown as HTMLAudioElement).play();
		});
	};
	return nextHandle;
};
