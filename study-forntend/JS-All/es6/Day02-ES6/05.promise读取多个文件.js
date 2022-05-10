// 引入fs 模块
let fs = require('fs')

// 回调地狱
// fs.readFile('./resources/01文本.txt', (err1, data1) => {
// 	fs.readFile('./resources/02文本.txt', (err2, data2) => {
// 		fs.readFile('./resources/03文本.txt', (err3, data3) => {
// 			let result = data1 + data2 + data3
// 			console.log(result)
// 		})
// 	})
// })

// 使用primose 实现
let p = new Promise((resolve, reject) => {
	fs.readFile('./resources/01文本.txt', (err, data) => {
		if (err) {
			reject(err)
		}
		resolve(data)
	})
})
	.then(res => {
		return new Promise((resolve, reject) => {
			fs.readFile('./resources/02文本.txt', (err, data) => {
				if (err) {
					reject(err)
				}
				resolve([res, data])
			})
		})
	})
	.then(res => {
		return new Promise((resolve, reject) => {
			fs.readFile('./resources/03文本.txt', (err, data) => {
				if (err) {
					reject(err)
				}
				res.push(data)
				resolve(res.join(''))
			})
		})
	})
	.then(res => {
		console.log(res)
	})
