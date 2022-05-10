let app = require('express')()
let server = require('http').Server(app)
let io = require('socket.io')(server)

server.listen(3000, () => {
	console.log('服务器启动成功');
})

app.get('/', (req, res) => {
	res.sendFile(__dirname + '/index.html')
})

io.on('connection', socket => {
	socket.emit('news', { hello: 'world' })
	socket.on('hehe', data => {
		console.log(data);
	})
})