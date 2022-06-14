// const fs = require('fs');
// const path = require('path');
// const axios = require('axios');

// const downloadImgApi = imgPath => {
// 	return axios.get(imgPath, { responseType: 'arraybuffer' });
// };

// const parseToBase64 = imgUrl => {
// 	console.log(imgUrl);
// 	return new Promise(async resolve => {
// 		let res = await downloadImgApi(imgUrl);
// 		// console.log(new Buffer.from(res.data, 'binary').toString('base64'));
// 		resolve(
// 			'data:image/png;base64,' +
// 				new Buffer.from(res.data, 'binary').toString('base64')
// 		);
// 	});
// };

const isFileNameRight = (fileName: string) => {
	let illegalChars = [`"`, '<', '>', '\\', '/', ':', '*', '?', '|'];
	let fileNameList = fileName.split('');
	return fileNameList.filter(item => illegalChars.includes(item)).length === 0;
};

async function test() {
	let str = '123123asdasd';
	console.log(isFileNameRight(str));
}

test();
