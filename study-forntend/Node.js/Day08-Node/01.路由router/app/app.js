
let express = require('express')

let app = express()

// 1.字符串的路由
app.get('/', (req, res) => {
	res.send('这是首页')
})

// 2.类字符串的正则模式
// 例如：匹配两个路径abcd或者acd
app.get('/ab?cd', (req, res) => {
	res.send('这是abcd或者是acd')
})
// 路径：/ab+cd => /abcd  /abbbbbbbbbbbcd
// 路径：/ab*cd，必须以ab开头，cd结尾，中间任意

// 3.以正则匹配路径模式
app.get(/\/a\d{10,}/, (req, res) => {
	res.send('新闻页面')
})

// 4.动态路由
// 可以有多个
app.get('/news/:cataoryid/a:newsid', (req, res) => {
	// 会将请求参数放在req.params里面
	let params = req.params
	res.send('动态路由匹配新闻页面newsid：' + params.newsid + '; cataoryid：' + params.cataoryid)
})

// 5.可以使用n个函数
app.get('/news/:newsid', (req, res, next) => {
	// 处理请求数据
	req.localhost = '127.0.0.1'
	// 进入下一个函数
	next()
}, (req, res, params) => {
	// 发送请求
	res.send('多个函数新闻页面newsid：' + req.params.newsid + '; localhost：' + req.localhost)
})


app.listen(8080, () => {
	console.log('服务器启动完成：http://localhost:8080');
})

module.exports = app