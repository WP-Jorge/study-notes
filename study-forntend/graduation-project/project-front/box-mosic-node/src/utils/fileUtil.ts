import fs from 'fs';
import path from 'path';

import { downloadFileApi } from '../network/file.network';

const isFileExisted = (filepath: string, filename: string) => {
	return new Promise(resolve => {
		let fullPath = path.join(filepath, filename);
		console.log('文件是否存在：' + fullPath);
		fs.access(fullPath, (err: any) => {
			if (err) {
				resolve(false);
			}
			resolve(true);
		});
	});
};

const mkdir = (dirpath: string, dirname: string) => {
	return new Promise(resolve => {
		let fullPath = path.join(dirpath, dirname);
		console.log('创建目录：' + fullPath);
		fs.mkdir(fullPath, (err: any) => {
			if (err) {
				resolve(false);
			}
			console.log('创建目录完成：' + fullPath);
			resolve(true);
		});
	});
};

const mkdirSavely = (dirpath: string, dirname: string) => {
	return new Promise(async resolve => {
		let isExit = await isFileExisted(dirpath, dirname);
		if (!isExit) {
			resolve(await mkdir(dirpath, dirname));
		}
		resolve(false);
	});
};

const writeFile = (
	filePath: string,
	fileName: string,
	data: string,
	option = { flag: 'w+' }
) => {
	return new Promise(resolve => {
		let fullPath = path.join(filePath, fileName);
		console.log('创建歌词：' + fileName);
		fs.writeFile(fullPath, data, option, (err: any) => {
			if (err) {
				resolve(false);
			}
			console.log('创建歌词完成：' + fileName);
			resolve(true);
		});
	});
};

const downloadFile = (fileUrl: string, filePath: string, fileName: string) => {
	return new Promise(async resolve => {
		try {
			let fullPath = path.join(filePath, fileName);
			let writeStream = fs.createWriteStream(fullPath);
			let res = await downloadFileApi(fileUrl);
			res.data.pipe(writeStream);
			let timer = setTimeout(() => {
				console.log('下载超时：' + fileName);
				resolve(false);
			}, 120000);
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

export { isFileExisted, mkdir, writeFile, downloadFile, mkdirSavely };
