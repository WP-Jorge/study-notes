let fs = require('fs')

// 1.创建写入流
// 语法：fs.createWriteStream(文件路径, [可选的配置操作])
let ws = fs.createWriteStream('hello.txt', {flags: 'a', encoding: 'utf-8'})
console.log(ws);

// 2.1监听文件打开事件
ws.on('open', () => {
	console.log('文件已经打开');
})
// 2.2监听文件已经准备好状态
ws.on('ready', () => {
	console.log('文件写入已准备状态');
})
// 2.3监听文件关闭事件
ws.on('close', () => {
	console.log('文件已经关闭');
})

// 3.写入内容
ws.write('你好世界', err => {
	if (err) {
		console.log(写入失败);
	} else {
		console.log('写入成功');
	}
})

// 4.关闭
ws.end(() => console.log('文件写入关闭'))