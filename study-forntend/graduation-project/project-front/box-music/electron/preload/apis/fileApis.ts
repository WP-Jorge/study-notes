import { shell } from 'electron';
import fs from 'fs';
const http = require('http');
import path from 'path';

export const deleteFile = (filepath: string) => {
	return new Promise((resolve, reject) => {
		fs.unlink(filepath, err => {
			if (err) {
				reject(err);
			}
			resolve(true);
		});
	});
};

export const openFolder = (filepath: string) => {
	shell.openPath(filepath.replace(path.basename(filepath), ''));
};

export const readDir = (dirname: string) => {
	return new Promise((resolve, reject) => {
		fs.readdir(dirname, (err, files) => {
			if (err) {
				reject(err);
			}
			resolve(files);
		});
	});
};

export const isDir = (filepath: string) => {
	return new Promise((resolve, reject) => {
		fs.stat(filepath, (err, stat) => {
			if (err) {
				reject(err);
			}
			resolve(stat.isDirectory());
		});
	});
};

export const parsePath = (pathStr = '') => {
	return path.parse(pathStr);
};

export const image2Base64 = (url: string) =>
	Buffer.from(url, 'binary').toString('base64');

export const downloadFile = (url: string, dir: string, filename: string) => {
	const filePath = path.join(dir, filename);

	return new Promise((resolve, reject) => {
		http
			.get(url, (res: any) => {
				const file = fs.createWriteStream(`${filePath}`);
				res.pipe(file);
				file.on('finish', () => {
					file.close();
					return resolve(filePath);
				});
			})
			.on('error', (err: Error) => {
				return reject(err.message);
			});
	});
};
