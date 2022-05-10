// 1、导入nodejs-websocket包
const ws = require('nodejs-websocket')
const PORT = 3000

// 2、创建一个server
// 2.1、处理用户请求

// 每次只要有用户连接，函数就会执行，会给连接的用户创建一个connect对象
const server = ws.createServer(connect => {
	console.log('有用户连接服务器');
	
	// text 监听用户发送的信息
	connect.on('text', data => {
		console.log('接收用户发来的数据：', data);
		// 给用户一个响应数据
		// 对用户发送的数据：把小写转化为大写，并且拼接一点内容
		connect.send(data.toUpperCase() + '  转化为大写')
	})
	
	// close 关闭事件，只要用户连接断开了close事件就会触发
	connect.on('close', () => {
		console.log('连接断开了');
	})
	
	// error 注册一个error事件 用来处理用户错误信息
	connect.on('error', () => {
		console.log('用户连接异常');
	})
})

// 3、监听
server.listen(PORT, () => {
	console.log('websocket服务启动成功，服务地址：ws://locakhost:' + PORT);
})

