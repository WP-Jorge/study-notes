export enum GlobalValues {
	PLAY_RECYCLE = 'play-recycle',
	PLAY_ONE = 'play-one',
	PLAY_RANDOM = 'play-random'
}

export enum MessageType {
	WINDOW_SCREEN = 'window-screen',
	WINDOW_FULLSCREEN = 'window-fullscreen',
	WINDOW_FULLSCREEN_EXIT = 'window-fullscreen-exit',
	WINDOW_MIN = 'window-min',
	WINDOW_MAX = 'window-max',
	WINDOW_CHANGE = 'window-change',
	WINDOW_CLOSE = 'window-close',
	DOWNLOAD_WAIT = 'download-wait',
	DOWNLOAD_START = 'download-start',
	DOWNLOAD_PAUSE = 'download-pause',
	DOWNLOAD_RESUME = 'download-resume',
	DOWNLOAD_CANCEL = 'download-cancel',
	DOWNLOAD_FINISH = 'download-finish',
	DOWNLOAD_UPDATE = 'download-update',
	DOWNLOAD_RESTART = 'download-restart',
	DOWNLOAD_EXIT = 'download-exit',
	DOWNLOAD_EXIT_FORCE = 'download-exit-force',
	WILL_DOWNLOAD = 'will-download',
	WRITEIN_UPDATE = 'writein-update',
	WRITEIN_FINISH = 'writein-finish',
	OPEN_DIR = 'open-dir'
}

export enum DownloadState {
	PAUSED = 'paused',
	COMPLETED = 'completed',
	INTERRUPTED = 'interrupted',
	CANCELLED = 'cancelled',
	PROGRESSING = 'progressing'
}

export const PlayTypes = [
	GlobalValues.PLAY_RECYCLE,
	GlobalValues.PLAY_ONE,
	GlobalValues.PLAY_RANDOM
];

export enum ResourceType {
	MUSIC = 'music',
	MUSIC_PICTURE = 'musicPicture',
	ALBUM_PICTURE = 'albumPicture',
	CATEGORY_PICTURE = 'categoryPicture',
	SINGER_PICTURE = 'singerPicture',
	USER_PICTURE = 'userPicture',
	PLAYLIST_PICTURE = 'playlistPicture'
}

export enum ContextMenuType {
	MUSIC = 0,
	PLAYLIST = 1,
	SINGER = 2
}
