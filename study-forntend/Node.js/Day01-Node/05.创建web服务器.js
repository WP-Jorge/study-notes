// 在Node中专门提供了一个核心模块: http
// http这个模块的职责就是帮你创建编写服务器的

// 1.加载http核心模块
let http = require('http');

// 2.使用http.createServer() 方法创建一个web服务器 返回一个server实例
let server = http.createServer((req, res) => {
	// 加入设置字符集，解决乱码
	// req.writeHead(200, {
	// 	"Content-Type": "text/html;charset=utf-8"
	// });
	res.writeHead(200, {
		"Content-Type": "text/html;charset=utf-8"
	});
});

// 3.服务器要干嘛？
/**
 * 	提供服务
 * 	接收请求
 * 	处理请求
 * 	给出反馈（发送响应）
 */

// 4.接收请求,注册require事件，当客户端发送请求过来，会自动触发服务器的require请求事件，然后执行回调函数
// 4.1.request需要接收两个参数
// 	require		请求对象
// 		可以回去客户端的请求信息
// 	response	响应对象
// 		可以用来发送响应消息
server.on('request', (req, res) => {
	// console.log(req);
	console.log('收到客户端的请求！请求路径是：' + req.url); // 收到客户端的请求！请求路径是：/
	
	// response对象有一个方法: write 可以用来给客户端发送响应数据
	// write可以使用多次，但是最后一定要使用end 来结束响应，否则客户端会一直等待
	res.write('你好Node'); // 不加字符集中文会乱码
	
	// 告诉客户端服务器响应结束
	res.end();
});

// 5.绑定端口号，启动服务器
server.listen(3000, () => {
	console.log('服务器启动成功！');
});