const path = require('path');

const Koa = require('koa');
const KoaBody = require('koa-body');
const KoaStatic = require('koa-static');
const Parameter = require('koa-parameter');

const router = require('../router');
const errorHandler = require('./errorHandler')

let app = new Koa();
app.silent = true;
app.use(KoaBody({
	multipart: true, // 开启文件上传
	formidable: { // 文件上传配置
		uploadDir: path.join(__dirname, '../upload'), // 文件上传地址
		keepExtensions: true // 是否保存文件扩展名
	}
}));
app.use(KoaStatic(path.join(__dirname, '../upload')));
app.use(Parameter(app));
app.use(router.routes()).use(router.allowedMethods()); // 开启其他不支持的请求方式提示

app.on('error', errorHandler);

module.exports = app;