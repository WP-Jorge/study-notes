import { GlobalValues, PlayTypes } from '@/globals/GlobalValues';
import { useMusicStore } from '@/store/music';

export const useIsPlayOne = () => {
	const musicStore = useMusicStore();
	const isPlayOne = computed(
		() =>
			PlayTypes[musicStore.playMusic.playTypeIndex] === GlobalValues.PLAY_ONE
	);
	return isPlayOne;
};
