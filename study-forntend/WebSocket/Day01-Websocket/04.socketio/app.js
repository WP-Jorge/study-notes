// 创建http服务器
let http = require('http')
let fs = require('fs')
let app = http.createServer()

app.on('request', (req, res) => {
	fs.readFile(__dirname + '/index.html', (err, data) => {
		if (err) {
			res.writeHead(500)
			return res.end('Error loading index.html')
		}
		res.writeHead(200)
		res.end(data)
	})
})

app.listen(3000, () => {
	console.log('服务器启动成功');
})

// 要放在app下面
let io = require('socket.io')(app)

// 监听用户连接的事件
// socket: 表示用户的连接
// socket.emit：表示触发某个事件 如果需要给浏览器发送数据，就要触发浏览器注册的哪个事件
// socket.on: 表示注册某个时事件 如果我们需要获取浏览器的数据，就需要注册一个事件，等待浏览器触发
io.on('connection', socket => {
	console.log('连接新用户');
	// socket.emit 方法表示给浏览器发送数据
	// 参数一：事件名字
	// socket.emit('send', { name: 'zs' })
	
	// 参数一：事件名：任意
	// 参数二: 获取到的数据
	socket.on('hehe', data => {
		console.log(data);
		socket.emit('send', data)
	})
	
})