const express = require('express')
const sqlQuery = require('./myMysql')
let app = express()
// 使用模板来渲染页面
let ejs = require('ejs')
// 将模板引擎与express应用相关联
app.set('views', 'views') // 设置视图的对应位置
app.set('view engine', 'ejs') // 设置默认的模板引擎
app.set('ejs', ejs.__express) // 定义模板引擎

/*
	<% '脚本' 标签，用于流程控制，无输出。
	<%_ 删除其前面的空格符
	<%= 输出数据到模板（输出是转义 HTML 标签）
	<%- 输出非转义的数据到模板
	<%# 注释标签，不执行、不输出内容
	<%% 输出字符串 '<%'
	%> 一般结束标签
	-%> 删除紧随其后的换行符
	_%> 将结束标签后面的空格符删除
*/

app.get('/', async (req, res) => {
	// 插入变量
	let options = {
		title: '野猪书库',
		articleTitle: '<h1>文章标题</h1>'
	}
	res.render('index', options)
})
app.get('/tj', async (req, res) => {
	// 条件显示
	let options = {
		username: '小明',
		gender: '男'
	}
	res.render('condition', options)
	
})
app.get('/xh', async (req, res) => {
	// 循环
	let stars = ['蔡徐坤', '坤坤', '牛顿', '欧几里得']
	options = {
		stars
	}
	res.render('xh', options)
})

module.exports = app