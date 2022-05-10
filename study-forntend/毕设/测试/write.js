let fs = require('fs');
let path = require('path');

function fsWrite(filePath, content) {
	return new Promise(async (resolve, reject) => {
		await fsdirSync(path.dirname(filePath));
		fs.writeFile(filePath, content, { flag: 'a', encoding: 'utf-8' }, err => {
			if (err) {
				reject(err);
			} else {
				resolve(err);
			}
		});
	});
}

// 递归创建目录 同步方法
function fsdirSync(dirname) {
	return new Promise((resolve, reject) => {
		if (fs.existsSync(dirname)) {
			resolve('创建成功');
		} else {
			if (fsdirSync(path.dirname(dirname))) {
				fs.mkdirSync(dirname);
				resolve('创建成功');
			}
		}
	})
}

function fsRead(filePath) {
	return new Promise((resolve, reject) => {
		fs.readFile(filePath, (err, data) => {
			if (err) {
				reject(err);
			} else {
				resolve(data);
			}
		});
	});
}

module.exports = { fsWrite, fsRead, fsdirSync };
