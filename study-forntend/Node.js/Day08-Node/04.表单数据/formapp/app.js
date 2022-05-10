var express = require('express');
var path = require('path');
var app = express();
var sqlQuery = require('./myMysql')

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');
app.use(express.static(path.join(__dirname, 'public')));
// 解析post提交的数据,没有这个就不能获取到post提交的数据
app.use(express.urlencoded())

// 首页搜索
app.get('/', (req, res) => {
	res.render('index')
})

// 自己封装提取get方式的表单数据key
app.get('/search', (req, res) => {
	// 提取问好后面字符串
	let queryStr = req.url.split('?')[1]
	// 对表单提交的键值对进行分割
	let result = queryStr.split('&')
	// 设置一个query对象，将键值对以对象的属性的形式进行保存
	let query = {}
	result.forEach((item, i) => {
		// 对键值对进行分割提取
		let key = item.split('=')[0]
		// 将值转码（部分特殊符号不行转码）
		let value = decodeURI(item.split('=')[1])
		query[key] = value
	})
	console.log();
	res.send(query)
})

// 使用express自带的get获取数据方式
app.get('/search1', (req, res) => {
	console.log(req.query);
	res.send(req.query)
})

// 使用post
app.post('/search1', (req, res) => {
	console.log(req.body);
	res.send(req.body)
})

// 登录
app.get('/login', (req, res) => {
	res.render('login')
})

app.post('/login', async (req, res) => {
	// 获取用户名密码
	let username = req.body.username
	let password = req.body.password
	// 查询数据库
	let sql = 'select count(id) as count from user where username = ? and password = ?'
	let arr = [username, password]
	let result = await sqlQuery(sql, arr)
	if (result[0].count === 0) {
		res.send('登录失败')
	} else {
		res.send('登录成功，欢迎：' + username)
	}
})

module.exports = app;
