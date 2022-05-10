let fs = require('fs')

function writefs(path, content) {
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

function fsDir(path) {
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

function fsRead(path) {
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

module.exports = {writefs, fsDir}