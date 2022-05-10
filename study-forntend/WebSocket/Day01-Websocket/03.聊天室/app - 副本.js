const ws = require('nodejs-websocket')

// 记录当前在线用户总数
let count = 0

// 每个连接服务器的用户都有一个conn对象
const server = ws.createServer(conn => {
	console.log('新用户连接');
	count++
	conn.userName = `用户${count}`
	// 1、告诉所有用户 有人进入聊天室
	broadcast(`${conn.userName}进入了聊天室`)
	// 接收用户的数据时触发
	conn.on('text', data => {
		// 2、接收到某个用户信息的时候 告诉所有用户发送的内容是什么
		broadcast(data)
	})
	// 关闭连接时触发
	conn.on('close', data => {
		console.log('关闭连接');
		count--
		broadcast(`${conn.userName}离开了聊天室`)
		// 3、告诉所有用户，有人离开了聊天室
	})
	// 发生异常时触发
	conn.on('error', data => {
		console.log('发生异常');
	})
})

// 广播， 给所有用户发送消息
function broadcast(msg) {
	// server.connnections: 表示所有用户
	server.connections.forEach(item => {
		item.send(msg)
	})
}

server.listen(3000, () => {
	console.log('服务启动 服务地址：ws://localhost:3000');
})