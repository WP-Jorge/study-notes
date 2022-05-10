let http = require('http')
let fs = require('fs')
let server = http.createServer()
server.on('request', (req, res) => {
	res.setHeader('Content-type', 'text/html; charset=utf-8')
	let url = req.url
	if (url === '/') {
		let html = fs.readFile('./11.html', (err, data) => {
			if (err) {
				console.log('错误');
			} else {
				res.end(data)
			}
		})
	} else {
		res.end('拉美人')
	}
})
server.listen(3000, () => {
	console.log('服务器开启。。。');
})