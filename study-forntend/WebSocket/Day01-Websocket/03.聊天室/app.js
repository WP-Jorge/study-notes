const ws = require('nodejs-websocket')

/*
	分析：
		消息不应该是一个简单的字符串
		这个消息应该是一个对象
		type: 消息类型
			0：表示进入聊天室的消息
			1：用户离开聊天室的消息
			2：正常的聊天消息
		msg：消息的内容
		time: 聊天的具体时间
 */

// 消息类型
const TYPE_ENTER = 0
const TYPE_LEAVE = 1
const TYPE_MSG = 2

// 记录当前在线用户总数
let count = 0

// 每个连接服务器的用户都有一个conn对象
const server = ws.createServer(conn => {
	console.log('新用户连接');
	count++
	conn.userName = `用户${count}`
	// 1、告诉所有用户 有人进入聊天室
	broadcast({
		type: TYPE_ENTER,
		msg: `${conn.userName}进入了聊天室`,
		time: getNowTime()
	})
	// 接收用户的数据时触发
	conn.on('text', data => {
		// 2、接收到某个用户信息的时候 告诉所有用户发送的内容是什么
		broadcast({
			type: TYPE_MSG,
			msg: data,
			time: getNowTime()
		})
	})
	// 关闭连接时触发
	conn.on('close', data => {
		console.log('关闭连接');
		count--
		broadcast({
			type: TYPE_LEAVE,
			msg: `${conn.userName}离开了聊天室`,
			time: getNowTime()
		})
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
		item.send(JSON.stringify(msg))
	})
}

// 获取当前时间
function getNowTime() {
	return new Date().toLocaleString('cn', {hour12:false})
}

server.listen(3000, () => {
	console.log('服务启动 服务地址：ws://localhost:3000');
})