const http = require('http');
const url = require('url');
const util = require('util');

let server = http.createServer();

server.on('request', async (req, res) => {
	res.setHeader('Content-Type', 'application/json');
	res.setHeader('Access-Control-Allow-Origin', '*');
	res.setHeader('Access-Control-Allow-Method', '*');
	res.setHeader('Access-Control-Allow-Headers', '*');
	if (req.method.toLowerCase() === 'get') {
		let params = url.parse(req.url, true).query;
		res.end(JSON.stringify(params));
	} else {
		if (req.headers['content-type'] === 'application/x-www-form-urlencoded') {
			let result = {};
			let data = '';
			let reg = /name="(.*?)"\s+(.*?)\s+--/gis;
			await req.on('data', chuck => {
				data += chuck.toString();
			});
			let temp;
			while ((temp = reg.exec(data))) {
				result[temp[1]] = temp[2];
			}
			res.end(JSON.stringify(result));
		} else {
			let data = '';
			await req.on('data', chuck => {
				data += chuck.toString();
			});
			res.end(data);
		}
	}
});

server.listen(8080, () => {
	console.log('服务器启动成功：localhost:8080');
});
