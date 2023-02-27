import { debounce, shuffle } from '@/utils/baseUtil';
import { useMusicStore } from '@/store/music';
import { useSystemStore } from '@/store/system';
import { GlobalValues, playTypes } from '@/globals/GlobalValues';
import { Music } from '@/networks/music';
import { Singer } from '@/networks/singer';
import { storeToRefs } from 'pinia';

export const useMusicBar = () => {
	const musicStore = useMusicStore();
	const systemStore = useSystemStore();
	const { showMusicDetail } = storeToRefs(systemStore);
	const isDragging = ref(false);
	const musicOptions = reactive({
		controls: false,
		showSlider: false,
		progress: 0,
		currentIndex: 0,
		isRandom: false,
		playOrders: [] as number[],
		muted: localStorage.getItem('muted') === 'true' ? true : false,
		volume: parseFloat(
			localStorage.getItem('volume') ?? localStorage.getItem('volume') ?? '0.1'
		),
		tempVolume: parseFloat(localStorage.getItem('tempVolume') ?? '0.1'),
		playTypeIndex: parseInt(localStorage.getItem('playTypeIndex') ?? '0'),
		shouldsetPlayOrders:
			playTypes[parseInt(localStorage.getItem('playTypeIndex') ?? '0')] ===
			GlobalValues.PLAY_RANDOM
	});

	const initMusicBar = () => {
		(musicStore.audio as unknown as HTMLAudioElement).volume =
			musicOptions.volume;
	};

	const playHandle = () => {
		(musicStore.audio as unknown as HTMLAudioElement).muted =
			musicOptions.muted;
		(musicStore.audio as unknown as HTMLAudioElement).volume =
			musicOptions.volume;
		musicStore.play = !musicStore.play;
		musicStore.play
			? (musicStore.audio as unknown as HTMLAudioElement).play()
			: (musicStore.audio as unknown as HTMLAudioElement).pause();
	};

	const preHandle = () => {
		const tempMusic =
			musicStore.musicList[musicOptions.playOrders[musicOptions.currentIndex]];
		playOrdersChange();
		musicStore.play = true;
		musicOptions.currentIndex =
			musicOptions.currentIndex <= 0
				? musicStore.musicList.length - 1
				: (musicOptions.currentIndex - 1) % musicStore.musicList.length;
		if (
			tempMusic.musicId ===
			musicStore.musicList[musicOptions.playOrders[musicOptions.currentIndex]]
				.musicId
		) {
			preHandle();
			return;
		}
		nextTick(() => (musicStore.audio as unknown as HTMLAudioElement).play());
	};

	const nextHandle = () => {
		console.log('🦃🦃1', 1);
		const tempMusic =
			musicStore.musicList[musicOptions.playOrders[musicOptions.currentIndex]];
		playOrdersChange();
		musicStore.play = true;
		musicOptions.currentIndex =
			(musicOptions.currentIndex + 1) % musicStore.musicList.length;
		if (
			tempMusic.musicId ===
			musicStore.musicList[musicOptions.playOrders[musicOptions.currentIndex]]
				.musicId
		) {
			nextHandle();
			return;
		}
		nextTick(() => (musicStore.audio as unknown as HTMLAudioElement).play());
	};

	const progressChange = debounce(() => {
		console.log(musicOptions.progress);
		musicStore.currentTime =
			(musicStore.musicList[musicOptions.playOrders[musicOptions.currentIndex]]
				.duration as number) * musicOptions.progress;
		(musicStore.audio as unknown as HTMLAudioElement).currentTime =
			musicStore.currentTime;
		isDragging.value = false;
	}, 20);

	const playTypeChange = () => {
		let playTypeIndex =
			(musicOptions.playTypeIndex + 1) % (musicStore.musicList.length - 1);
		localStorage.setItem('playTypeIndex', JSON.stringify(playTypeIndex));
		musicOptions.shouldsetPlayOrders =
			playTypes[playTypeIndex] === GlobalValues.PLAY_RANDOM;
		musicOptions.playTypeIndex = playTypeIndex;
	};

	const playOrdersChange = () => {
		let tempArr = musicStore.musicList.map((_, index) => index);
		if (musicOptions.shouldsetPlayOrders) {
			if (!musicOptions.isRandom) {
				musicOptions.playOrders = shuffle(tempArr);
				musicOptions.isRandom = true;
			}
			return;
		}
		musicOptions.playOrders = tempArr;
		musicOptions.isRandom = false;
		musicOptions.shouldsetPlayOrders = false;
	};

	const timeUpdate = () => {
		musicStore.currentTime = (
			musicStore.audio as unknown as HTMLAudioElement
		).currentTime;
		if (!isDragging.value) {
			musicOptions.progress =
				musicStore.currentTime /
				(musicStore.musicList[
					musicOptions.playOrders[musicOptions.currentIndex]
				].duration as number);
		}
	};
	const ended = () => {
		nextHandle();
	};

	const volumeClick = (flag: boolean) => {
		musicOptions.muted = flag;
		(musicStore.audio as unknown as HTMLAudioElement).muted =
			musicOptions.muted;
		localStorage.setItem('muted', JSON.stringify(flag));
		if (flag) {
			musicOptions.volume = 0;
			localStorage.setItem('volume', JSON.stringify(0));
			return;
		}
		musicOptions.volume = musicOptions.tempVolume;
		(musicStore.audio as unknown as HTMLAudioElement).volume =
			musicOptions.tempVolume;
		localStorage.setItem('volume', JSON.stringify(musicOptions.tempVolume));
	};

	const volumeChange = (volume: number) => {
		musicOptions.volume = volume;
		localStorage.setItem('volume', JSON.stringify(volume));
		musicOptions.tempVolume = volume;
		localStorage.setItem('tempVolume', JSON.stringify(volume));
		(musicStore.audio as unknown as HTMLAudioElement).volume = volume;
		musicOptions.muted = volume === 0;
		localStorage.setItem('muted', JSON.stringify(volume === 0));
	};

	const playlistClick = (item: Music) => {
		musicStore.music = item;
		nextTick(() => {
			(musicStore.audio as unknown as HTMLAudioElement).play();
			musicStore.play = true;
		});
	};

	const musicPicClick = () => {
		showMusicDetail.value = !showMusicDetail.value;
	};

	playOrdersChange();

	onMounted(() => {
		initMusicBar();
	});

	const singers = computed(() => {
		return musicStore.music.singers
			.map((item: Singer) => item.singerName)
			.join(' / ');
	});

	const isPlayRecycle = computed(
		() => playTypes[musicOptions.playTypeIndex] === GlobalValues.PLAY_RECYCLE
	);
	const isPlayOne = computed(
		() => playTypes[musicOptions.playTypeIndex] === GlobalValues.PLAY_ONE
	);
	const isPlayRandom = computed(
		() => playTypes[musicOptions.playTypeIndex] === GlobalValues.PLAY_RANDOM
	);

	watchEffect(() => {
		musicStore.music =
			musicStore.musicList[musicOptions.playOrders[musicOptions.currentIndex]];
	});
	onMounted(() => {
		watchEffect(() => {
			musicStore.play ? musicStore.audio.play() : musicStore.audio.pause();
		});
	});
	return {
		musicOptions,
		singers,
		isPlayRecycle,
		isPlayOne,
		isPlayRandom,
		isDragging,
		initMusicBar,
		playHandle,
		preHandle,
		nextHandle,
		progressChange,
		playTypeChange,
		timeUpdate,
		ended,
		volumeChange,
		volumeClick,
		playlistClick,
		musicPicClick
	};
};
