import axios from './index';

export const downloadFileApi = (filePath: string) => {
	return axios.get(filePath, { responseType: 'stream' });
};

export const downloadImgApi = (imgPath: string) => {
	return axios.get(imgPath, { responseType: 'arraybuffer' });
};
