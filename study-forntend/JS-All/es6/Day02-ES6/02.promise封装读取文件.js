// 1.引入 fs 模块
let fs = require('fs')

// 2.调用方法读取文件
// fs.readFile('./resources/文本.txt', (err, data) => {
// 	// 如果失败就抛出异常
// 	if (err) {
// 		throw err
// 	}
// 	// 没有错误则输出内容
// 	console.log(data.toString())
// })

// 3.使用promiss封装
let p = new Promise((resolve, reject) => {
	fs.readFile('./resources/文本.txt', (err, data) => {
		// 失败
		if (err) reject(err)
		// 成功
		resolve(data)
	})
})
p.then(value => {
	console.log(value.toString())
}).catch(err => {
	console.log(err)
})
