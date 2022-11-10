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
		case ResourceType.USER_PICTURE:
			return (
				import.meta.env.VITE_BASE_URL +
				import.meta.env.VITE_USER_PICTURES +
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

export const getFileFromUrl = (
	url: string,
	imgElement: HTMLImageElement,
	cb: (file: File) => any
) => {
	function getBase64Image(img: HTMLImageElement) {
		let canvas = document.createElement('canvas');
		canvas.width = img.width;
		canvas.height = img.height;
		let ctx = canvas.getContext('2d') as CanvasRenderingContext2D;
		ctx.drawImage(img, 0, 0, img.width, img.height);
		let ext = img.src.substring(img.src.lastIndexOf('.') + 1).toLowerCase();
		let dataURL = canvas.toDataURL('image/' + ext);

		return dataURL;
	}

	let image = new Image();
	image.src = url;
	image.setAttribute('crossOrigin', 'Anonymous');
	image.onload = function () {
		let base64 = getBase64Image(image);
		imgElement.src = base64;
		//转换base64到file
		let file = btof(base64, 'userAvator');
		cb(file);
	};

	function btof(data: string, fileName: string) {
		const dataArr = data.split(',');
		const byteString = atob(dataArr[1]);

		const options: FilePropertyBag = {
			type: 'image/jpeg',
			endings: 'native'
		};
		const u8Arr = new Uint8Array(byteString.length);
		for (let i = 0; i < byteString.length; i++) {
			u8Arr[i] = byteString.charCodeAt(i);
		}
		return new File([u8Arr], fileName + '.jpg', options);
	}
};
