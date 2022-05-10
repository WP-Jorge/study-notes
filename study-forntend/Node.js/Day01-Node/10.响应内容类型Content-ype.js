// require
// 端口号
var http = require('http')

var server = http.createServer()

server.on('request', (req, res) => {
	// 在服务器中默认发的是utf-8的内容，但是浏览器不知道你发的是utf-8内容，所以会按照操作系统当前的编码来解析，此电脑默认编码gbk，所以发生乱码
	// 解决方法就是告诉浏览器我用的是什么编码
	// 在http协议中，Content-typr就是告诉对方我给你发送的数据是什么类型的
	
	let url = req.url
	if (url === '/plain') {
		
		// text/plain 普通文本
		
		res.setHeader('Content-type', 'text/plain; charset=utf-8')
		// res.end('hello node')
		res.end('巴拉巴拉')
	} else if (url === '/html') {
		
		// text/html html文本
		
		res.setHeader('Content-type', 'text/html; charset=utf-8')
		res.end('<p>hello你好</p>')
	}
})

server.listen(3000, () => {
	console.log('服务器已开启');
})