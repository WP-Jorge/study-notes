var express = require('express');
var path = require('path');
let mallRouter = require('./routes/mall')
let apiRouter = require('./routes/api')

var app = express();

// mall路由
app.use('/mall', mallRouter)
// api路由
app.use('/api', apiRouter)

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

app.use(express.urlencoded({ extended: false }));
app.use(express.static(path.join(__dirname, 'public')));

// 添加中间件，可以有多个函数
app.use((req, res, next) => {
	console.log('访问任何页面，此函数都会被调用');
	res.addNum = ((a, b) => {
		return a + b
	})
	next()
})

app.get('/', (req, res) => {
	console.log(res.addNum(1, 2));
	res.send('这是首页')
})

module.exports = app;
