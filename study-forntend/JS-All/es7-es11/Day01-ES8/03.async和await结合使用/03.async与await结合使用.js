// 1.引入fs
const fs = require('fs')

// 读取【为学】
function readWeixue() {
	return new Promise((resolve, reject) => {
		fs.readFile('./src/为学.txt', (err, data) => {
			if (err) {
				// 失败
				reject(err)
			} else {
				// 成功
				resolve(data)
			}
		})
	})
}

// 读取【插秧诗】
function readChayangshi() {
	return new Promise((resolve, reject) => {
		fs.readFile('./src/插秧诗.txt', (err, data) => {
			if (err) {
				// 失败
				reject(err)
			} else {
				// 成功
				resolve(data)
			}
		})
	})
}

// 读取【观书有感】
function readGuanshuyougan() {
	return new Promise((resolve, reject) => {
		fs.readFile('./src/观书有感.txt', (err, data) => {
			if (err) {
				// 失败
				reject(err)
			} else {
				// 成功
				resolve(data)
			}
		})
	})
}

// 声明一个 async 函数
async function main() {
	// 获取为学
	let weixue = await readWeixue()

	// 获取插秧诗C
	let chayangshi = await readChayangshi()

	// 获取观书有感
	let guanshuyougan = await readGuanshuyougan()

	console.log(weixue.toString())
	console.log(chayangshi.toString())
	console.log(guanshuyougan.toString())
}

main()
