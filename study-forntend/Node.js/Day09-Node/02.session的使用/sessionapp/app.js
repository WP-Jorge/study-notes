var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
// 先安装session模块npm i express-session -S
// 再导入session
var session = require('express-session')

var indexRouter = require('./routes/index');
var usersRouter = require('./routes/users');
var sessionRouter = require('./routes/session')

var app = express();

// 视图的设置
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

// 设置express中间件
// 使用session中间件
// 可以进行session配置
app.use(session({
	// 添加盐
	secret: Math.random().toString(),
	// 强制保存session
	resave: true,
	// 默认单个会话有效
	cookie: {
		maxAge: 7 * 24 * 60 * 60 * 1000 // 设置有效期一周
	},
	// 初始化session设置
	saveUninitialized: true
}))
// 设置输出开发时的日志
// app.use(logger('dev'));
// 解析json数据
app.use(express.json());
// 解析req.body的数据
app.use(express.urlencoded({ extended: false }));
// cookie的解析，设置cookie加密secret：加密，开启后可以自由开启cookie加密或者不加密
app.use(cookieParser('secret'));
// 处理静态文件，如果访问静态文件就去静态文件夹中去取静态文件，然后渲染出来
app.use(express.static(path.join(__dirname, 'public')));

// 如果没有静态文件就去访问下面的路由
app.use('/', indexRouter);
app.use('/users', usersRouter);
// session相关的路由匹配
app.use('/session', sessionRouter)

// catch 404 and forward to error handler
// 设置404页面的中间件
// 如果上面的路由都没有匹配到那就报404错误，进入404页面
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler
// 处理错误的中间件
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
