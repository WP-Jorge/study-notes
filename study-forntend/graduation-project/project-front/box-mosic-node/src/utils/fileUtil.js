const fs = require('fs');
const path = require('path');

const { downloadFileApi, downloadImgApi } = require('../network/file.network');

const isFileExisted = (filePath, fileName) => {
	return new Promise(resolve => {
		let fullPath = path.join(filePath, fileName);
		console.log('文件是否存在：' + fullPath);
		fs.access(fullPath, err => {
			if (err) {
				resolve(false);
			}
			resolve(true);
		});
	});
};

const mkdir = (dirPath, dirName) => {
	return new Promise(resolve => {
		let fullPath = path.join(dirPath, dirName);
		console.log('创建目录：' + fullPath);
		fs.mkdir(fullPath, err => {
			if (err) {
				resolve(false);
			}
			console.log('创建目录完成：' + fullPath);
			resolve(true);
		});
	});
};

const writeFile = (filePath, fileName, data, option = { flag: 'w+' }) => {
	return new Promise(resolve => {
		let fullPath = path.join(filePath, fileName);
		console.log('创建歌词：' + fileName);
		fs.writeFile(fullPath, data, option, err => {
			if (err) {
				resolve(false);
			}
			console.log('创建歌词完成：' + fileName);
			resolve(true);
		});
	});
};

const downloadFile = (filePath, fileName, fileUrl) => {
	return new Promise(async resolve => {
		try {
			let fullPath = path.join(filePath, fileName);
			let writeStream = fs.createWriteStream(fullPath);
			let res = await downloadFileApi(fileUrl);
			res.data.pipe(writeStream);
			let timer = setTimeout(() => {
				console.log('下载超时：' + fileName);
				resolve(false);
			}, 60000);
			console.log('开始下载：' + fileName);
			writeStream.on('finish', async () => {
				console.log('下载完成：' + fileName);
				clearTimeout(timer);
				resolve(true);
			});
			writeStream.on('error', () => {
				console.log('下载失败');
				clearTimeout(timer);
				resolve(false);
			});
		} catch (err) {
			console.log('文件下载出错：' + err);
			resolve(false);
		}
	});
};

module.exports = {
	isFileExisted,
	mkdir,
	writeFile,
	downloadFile
};
