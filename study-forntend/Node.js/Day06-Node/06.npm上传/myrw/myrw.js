let fs = require('fs')

function write(path, content) {
	return new Promise((resolve, reject) => {
		fs.writeFile(path, content, {flag: 'a', encoding: 'utf-8'}, err => {
			if (err) {
				reject(err)
			} else {
				resolve(err)
			}
		})
	})
}

function mkdir(path) {
	return new Promise((resolve, reject) => {
		fs.mkdir(path, err => {
			if (err) {
				reject(err)
			} else {
				resolve('创建成功')
			}
		})
	})
}

function read(path) {
	return new Promise((resolve, reject) => {
		fs.readFile(path, (err, data) => {
			if (err) {
				reject(err)
			} else {
				resolve(data)
			}
		})
	})
}

function rename(oldPath, newPath) {
	return new Promise((resolve, reject) => {
		fs.rename(oldPath, newPath, err => {
			if (err) {
				reject(err)
			} else {
				resolve('rename success')
			}
		})
	})
}

function readdir(path, options) {
	return new Promise((resolve, reject) => {
		fs.readdir(path, options, (err, files) => {
			if(err) {
				reject(err)
			} else {
				resolve(files)
			}
		})
	})
}

module.exports = {write, read, mkdir, rename, readdir}