const http = require('http');

const serve = http.createServer((request, response) => {
	response.setHeader('Content-Type', 'application/json;charset=UTF-8');
	response.setHeader('Access-Control-Allow-Origin', '*');
	response.setHeader('Access-Control-Allow-Methods', '*');
	let url = request.url;
	console.log(url);
	if (url === '/getUserInfo') {
		let token = request.headers['authorization'];
		if (!token) {
			response.writeHead(401, {});
			setTimeout(() => {
				response.end(
					JSON.stringify({
						message: '请登录！'
					})
				);
			}, 1000);
		} else {
			setTimeout(() => {
				response.end(
					JSON.stringify({
						userInfo: {
							userName: '张三',
							roles: ['user', 'admin']
						}
					})
				);
			}, 1000);
		}
	} else if (url === '/login') {
		response.writeHead(200, {
			Authorization: 'asdjskdjad218asu8d89asd-ijsdasd98as09d787asd7-hasd89sa7d98asd7a8ds68'
		});
		setTimeout(() => {
			response.end(
				JSON.stringify({
					msg: '登录成功！',
					userInfo: {
						userName: '张三',
						roles: ['user', 'admin']
					}
				})
			);
		}, 1000);
	}
});

serve;

serve.listen(8081, () => {
	console.log('服务启动成功：http://localhost:8081');
});
