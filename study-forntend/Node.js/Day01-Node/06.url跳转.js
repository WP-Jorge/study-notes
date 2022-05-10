let http = require('http');

let server = http.createServer((req, res) => {
	// req.writeHead(200, {
	// 	"Content-Type": "text/html;charset=utf-8"
	// });
	res.writeHead(200, {
		"Content-Type": "text/html;charset=utf-8"
	});
});

server.on('request', (req, res) => {
	// end内的参数时返回给客户端的数据
	console.log('收到请求了，路径为：' + req.url);
	
	let url = req.url;
	
	if (url === '/') {
		res.end('index page');
	} else if (url === '/login') {
		res.end('login page');
	} else if (url === '/regist') {
		res.end('regist page');
	} else if(url === '/product') {
		let products = [
			{
				name: '苹果',
				price: 1111
			},
			{
				name: '黑凤梨',
				price: 2222
			},
			{
				name: '蓝莓',
				price: 3333
			}
		]
		res.end(JSON.stringify(products));
	} else {
		res.end('error page');
	}
	
	// 响应内容只能是字符串、二进制
	// 不能是
	// 数字
	// 对象
	// 数组
	// 布尔值
	
	// 这样直接输出文字＋结束
	// res.end('你好');
});

server.listen(3001, () => {
	console.log('服务器启动了');
});