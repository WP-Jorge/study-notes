var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');

var indexRouter = require('./routes/index');
var usersRouter = require('./routes/users');

var app = express();

// 视图的设置
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

// 设置express中间件
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
