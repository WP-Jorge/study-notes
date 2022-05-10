const MyApp = require('./MyApp')

let app = new MyApp()

app.on('/', (req, res) => {
	res.setHeader('Content-Type', 'text/html; charset=utf-8')
	res.end('这是首页')
})

app.on('/gnxw', (req, res) => {
	res.setHeader('Content-Type', 'text/html; charset=utf-8')
	if (req.pathObj.base === 'index.html') {
		res.end('这是国内新闻首页')
	} else {
		res.end('这是国内新闻其他页面')
	}
})

app.run(80, () => {
	console.log('启动服务器：http://127.0.0.1');
})