export enum GlobalValues {
	WINDOW_SCREEN = 'window-screen',
	WINDOW_FULLSCREEN = 'window-fullscreen',
	WINDOW_FULLSCREEN_EXIT = 'window-fullscreen-exit',
	WINDOW_MIN = 'window-min',
	WINDOW_MAX = 'window-max',
	WINDOW_CHANGE = 'window-change',
	WINDOW_CLOSE = 'window-close',
	PLAY_RECYCLE = 'play-recycle',
	PLAY_ONE = 'play-one',
	PLAY_RANDOM = 'play-random'
}

export const playTypes = [
	GlobalValues.PLAY_RECYCLE,
	GlobalValues.PLAY_ONE,
	GlobalValues.PLAY_RANDOM
];
