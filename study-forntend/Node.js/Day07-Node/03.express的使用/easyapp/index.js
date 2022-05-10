// 导入express
const express = require('express')
// 实例化express、
let app = express()

// 通过get方式请求
app.get('/', (req, res) => {
	res.send('<h1>我是首页</h1>')
})

// 运行实例
app.listen(3000, () => {
	console.log('服务器启动完成：http://localhost:3000');
})