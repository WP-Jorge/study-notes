const WebSocket = require('ws')
const fileUtils = require('../utils/file_utils')
const path = require('path')
// 创建websocket服务端对象，绑定端口号9998
const wss = new WebSocket.Server({
	port: 9998
})

// 服务端开启了监听
module.exports.listen = () => {
	// client: 代表的是客户端的连接socket对象
	wss.on('connection', client => {
		console.log('有客户端连接成功了')
		// 对客户端的连接对象进行message事件的监听
		// msg: 由服务端发给客户端的数据
		client.on('message', async msg => {
			console.log('客户端发送数据给服务端：' + msg)
			let payload = JSON.parse(msg)
			const action = payload.action
			if (action === 'getData') {
				let filePath = '../data/' + payload.chartName + '.json'
				// payload.chartName // trend seller map rank hot stock
				filePath = path.join(__dirname, filePath)
				const ret = await fileUtils.getFileJsonData(filePath)
				// 需要在服务端获取到数据的基础上 添加一个data字段
				// data所对应的值就是某个json文件的内容
				payload.data = ret
				client.send(JSON.stringify(payload))
			} else {
				// 原封不动的将接收到的数据转发给每个处于连接的客户端
				// wss.cients // 所有连接的客户端
				wss.clients.forEach(client => {
					client.send(msg)
				})
			}
			// 由服务端往客户端发送数据
			// client.send('hello socket from backend000000000000')
		})
	})
}