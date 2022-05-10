const axios = require('axios');

const downloadFileApi = filePath => {
	return axios.get(filePath, {responseType: 'stream'});
};

const downloadImgApi = imgPath => {
	return axios.get(imgPath, { responseType: 'arraybuffer' });
};

module.exports = {
	downloadFileApi,
	downloadImgApi
};