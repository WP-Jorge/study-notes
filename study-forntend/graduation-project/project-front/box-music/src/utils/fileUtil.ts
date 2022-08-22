import { ResourceType } from '@/globals/GlobalValues';

export const getAssetsImageUrl = (filename: string) => {
	const path = `/src/assets/images/${filename}`;
	const modules = import.meta.globEager('/src/assets/images/*');
	return modules[path].default;
};

export const getAssetsAudioUrl = (filename: string) => {
	const path = `/src/assets/audios/${filename}`;
	const modules = import.meta.globEager('/src/assets/audios/*');
	return modules[path].default;
};

export const getAssetsFileUrl = (dirname: string, filename: string) => {
	switch (dirname) {
		case 'images':
			return getAssetsImageUrl(filename);
		case 'audios':
			return getAssetsAudioUrl(filename);
	}
};

export const getResourceUrl = (
	resourceUrl: string,
	resourceType = ResourceType.MUSIC
) => {
	switch (resourceType) {
		case ResourceType.MUSIC:
			return (
				import.meta.env.VITE_BASE_URL +
				import.meta.env.VITE_MUSICS +
				resourceUrl
			);
		case ResourceType.ALBUM_PICTURE:
			return (
				import.meta.env.VITE_BASE_URL +
				import.meta.env.VITE_ALBUM_PICTURES +
				resourceUrl
			);
		case ResourceType.PLAYLIST_PICTURE:
			return (
				import.meta.env.VITE_BASE_URL +
				import.meta.env.VITE_PLAYLIST_PICTURES +
				resourceUrl
			);
		case ResourceType.SINGER_PICTURE:
			return (
				import.meta.env.VITE_BASE_URL +
				import.meta.env.VITE_SINGER_PICTURES +
				resourceUrl
			);
		case ResourceType.CATEGORY_PICTURE:
			return (
				import.meta.env.VITE_BASE_URL +
				import.meta.env.VITE_CATEGORY_PICTURES +
				resourceUrl
			);
		default:
			return (
				import.meta.env.VITE_BASE_URL +
				import.meta.env.VITE_MUSICS +
				resourceUrl
			);
	}
};

export const formatLyric = (lyric: string) => {
	return lyric
		.replace(/\r\n/g, '<rn>')
		.replace(/\n/g, '<n>')
		.replace(/\s/g, '<s>');
};

export const getMusicLevel = (br = 0) => {
	if (br <= 64000) {
		return '流畅';
	}
	if (br <= 128000) {
		return '高品';
	}
	if (br <= 640000) {
		return '超高品';
	}
	if (br > 640000) {
		return '无损';
	}
};

export const getRawLyric = (lyric = '') => {
	return lyric
		.replaceAll('<rn>', '\r\n')
		.replaceAll('<n>', '\n')
		.replaceAll('<s>', ' ');
};
