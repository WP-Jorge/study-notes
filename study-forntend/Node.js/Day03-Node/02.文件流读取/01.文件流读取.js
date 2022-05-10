let fs = require('fs')

// 1.创建读取流
// 语法：fs.createReadStream(路径, [可选的配置项])
let rs = fs.createReadStream('01.png', {flags: 'r'})
// console.log(rs)

// 2.1监听文件打开事件
rs.on('open', () => console.log('读取文件打开'))

// 2.2每一次数据流入完成
rs.on('data', chunk => {
	console.log('单批数据流入:' + chunk.length)
	console.log('------------------------');
	console.log(chunk)
	console.log('------------------------')
})

// 2.3监听文件关闭
rs.on('close', () => console.log('读取文件已经关闭'))

// 