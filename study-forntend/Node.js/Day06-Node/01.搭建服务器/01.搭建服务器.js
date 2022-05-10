const http = require('http')

// 创建server服务器对象
let server = http.createServer()

// 监听对当前服务器对象的请求
server.on('/', (req, res) => {
	// 设置编码
	res.setHeader('Content-Type', 'text/html; charset=utf-8')
	
	// 当服务器被请求时，会触发请求事件，并传入请求对象和响应对象
	
	// 根据不同路径显示不同内容
	if (req.url === '/') {
		res.end('<h1>首页</h1><img src="https://www.google.com.hk/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png">')
	} else if (req.url === '/gnxw') {
		res.end('<h1>国内新闻</h1>')
	} else if (req.url === '/ylxw') {
		res.end('<h1>娱乐新闻</h1>')
	} else {
		res.end('<h1>404 Not Found</h1>')
	}
})

// 服务器监听的端口号
server.listen(3000, () => {
	// 启动监听端口号成功时触发
	console.log('服务器启动成功');
})