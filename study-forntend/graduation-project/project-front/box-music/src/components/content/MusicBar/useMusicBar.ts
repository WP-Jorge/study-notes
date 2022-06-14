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
	const { setShowMusicDetail } = systemStore;
	const { showMusicDetail } = storeToRefs(systemStore);
	const { setMusic, setPlay, setCurrentTime } = musicStore;
	const { music, play, musicList, currentTime, audio } =
		storeToRefs(musicStore);
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
		(audio.value as unknown as HTMLAudioElement).volume = musicOptions.volume;
	};

	const playHandle = () => {
		(audio.value as unknown as HTMLAudioElement).muted = musicOptions.muted;
		(audio.value as unknown as HTMLAudioElement).volume = musicOptions.volume;
		setPlay(!play.value);
		play.value
			? (audio.value as unknown as HTMLAudioElement).play()
			: (audio.value as unknown as HTMLAudioElement).pause();
	};

	const preHandle = () => {
		const tempMusic =
			musicList.value[musicOptions.playOrders[musicOptions.currentIndex]];
		playOrdersChange();
		setPlay(true);
		musicOptions.currentIndex =
			musicOptions.currentIndex <= 0
				? musicList.value.length - 1
				: (musicOptions.currentIndex - 1) % musicList.value.length;
		if (
			tempMusic.musicId ===
			musicList.value[musicOptions.playOrders[musicOptions.currentIndex]]
				.musicId
		) {
			preHandle();
			return;
		}
		nextTick(() => (audio.value as unknown as HTMLAudioElement).play());
	};

	const nextHandle = () => {
		const tempMusic =
			musicList.value[musicOptions.playOrders[musicOptions.currentIndex]];
		playOrdersChange();
		setPlay(true);
		musicOptions.currentIndex =
			(musicOptions.currentIndex + 1) % musicList.value.length;
		if (
			tempMusic.musicId ===
			musicList.value[musicOptions.playOrders[musicOptions.currentIndex]]
				.musicId
		) {
			nextHandle();
			return;
		}
		nextTick(() => (audio.value as unknown as HTMLAudioElement).play());
	};

	const progressChange = debounce(() => {
		console.log(musicOptions.progress);
		setCurrentTime(
			(musicList.value[musicOptions.playOrders[musicOptions.currentIndex]]
				.duration as number) * musicOptions.progress
		);
		(audio.value as unknown as HTMLAudioElement).currentTime =
			currentTime.value;
		isDragging.value = false;
	}, 20);

	const playTypeChange = () => {
		let playTypeIndex =
			(musicOptions.playTypeIndex + 1) % (musicList.value.length - 1);
		localStorage.setItem('playTypeIndex', JSON.stringify(playTypeIndex));
		musicOptions.shouldsetPlayOrders =
			playTypes[playTypeIndex] === GlobalValues.PLAY_RANDOM;
		musicOptions.playTypeIndex = playTypeIndex;
	};

	const playOrdersChange = () => {
		let tempArr = musicList.value.map((_, index) => index);
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
		setCurrentTime((audio.value as unknown as HTMLAudioElement).currentTime);
		if (!isDragging.value) {
			musicOptions.progress =
				currentTime.value /
				(musicList.value[musicOptions.playOrders[musicOptions.currentIndex]]
					.duration as number);
		}
	};
	const ended = () => {
		nextHandle();
	};

	const volumeClick = (flag: boolean) => {
		musicOptions.muted = flag;
		(audio.value as unknown as HTMLAudioElement).muted = musicOptions.muted;
		localStorage.setItem('muted', JSON.stringify(flag));
		if (flag) {
			musicOptions.volume = 0;
			localStorage.setItem('volume', JSON.stringify(0));
			return;
		}
		musicOptions.volume = musicOptions.tempVolume;
		(audio.value as unknown as HTMLAudioElement).volume =
			musicOptions.tempVolume;
		localStorage.setItem('volume', JSON.stringify(musicOptions.tempVolume));
	};

	const volumeChange = (volume: number) => {
		musicOptions.volume = volume;
		localStorage.setItem('volume', JSON.stringify(volume));
		musicOptions.tempVolume = volume;
		localStorage.setItem('tempVolume', JSON.stringify(volume));
		(audio.value as unknown as HTMLAudioElement).volume = volume;
		musicOptions.muted = volume === 0;
		localStorage.setItem('muted', JSON.stringify(volume === 0));
	};

	const playlistClick = (item: Music) => {
		setMusic(item);
		nextTick(() => {
			(audio.value as unknown as HTMLAudioElement).play();
			setPlay(true);
		});
	};

	const musicPicClick = () => {
		setShowMusicDetail(!showMusicDetail.value);
	};

	playOrdersChange();

	onMounted(() => {
		initMusicBar();
	});

	const singers = computed(() => {
		return music.value.singers
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
		setMusic(
			musicList.value[musicOptions.playOrders[musicOptions.currentIndex]]
		);
	});
	onMounted(() => {
		watchEffect(() => {
			play.value ? audio.value.play() : audio.value.pause();
		});
	});
	return {
		musicOptions,
		audio,
		singers,
		isPlayRecycle,
		isPlayOne,
		isPlayRandom,
		musicList,
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
