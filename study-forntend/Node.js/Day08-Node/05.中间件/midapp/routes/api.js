var express = require('express');
var mysql = require('mysql')
var sqlQuery = require('../myMysql')
// 实例化一个路由模块，此路由相当于一个小的app实例
// 提供前端ajax请求的接口
let api = express.Router()

// 允许前端跨域请求的中间件
api.use((req, res, next) => {
	// 开启跨域
	res.append('Access-Control-Allow-Origin', '*')
	// 允许所有的访问
	res.append('Access-Control-Allow-Content-Type', '*')
	next()
})

// 提供什么分类下的第N页数据
api.get('/book/cataory/:cid/page/:pid', async (req, res) => {
	let cid = req.params.cid
	let page = parseInt(req.params.pid)
	let sql1 = ''
	let result1
	if (req.params.cid === '0') {
		sql1 = 'select id, bookname, bookimg, author, book.cataory from cataory, book where cataory.cataory = book.cataory limit ?, 28'
		result1 = await sqlQuery(sql1, [(page - 1) * 28])
		// 获取总页数
		let sql2 = 'select count(id) as num from book'
		let result2 = await sqlQuery(sql2, [cid])
		let totalPage = Math.ceil(result2[0].num / 28)
		// 设置分页的起始点
		let startPage = (page - 4) < 1 ? 1 : page - 4
		let endPage = (page + 5) > totalPage ? totalPage : page + 5
		let options = {
			books: Array.from(result1),
			cataory: await getCataory(),
			totalPage,
			currentPage: page,
			cid,
			startPage,
			endPage
		}
		res.json(options)
	} else {
		sql1 = 'select id, bookname, bookimg, author, book.cataory from cataory, book where cataory.cataory = book.cataory and cid = ? limit ?, 28'
		result1 = await sqlQuery(sql1, [cid, (page - 1) * 28])
		// 获取总页数
		let sql2 = 'select count(id) as num from cataory, book where cataory.cataory = book.cataory and cid = ?'
		let result2 = await sqlQuery(sql2, [cid])
		let totalPage = Math.ceil(result2[0].num / 28)
		// 设置分页的起始点
		let startPage = (page - 4) < 1 ? 1 : page - 4
		let endPage = (page + 5) > totalPage ? totalPage : page + 5
		let options = {
			books: Array.from(result1),
			cataory: await getCataory(),
			totalPage,
			currentPage: page,
			cid,
			startPage,
			endPage
		}
		res.json(options)
	}
})

async function getCataory() {
	// 获取所有分类
	let sql = 'select * from cataory'
	let result = await sqlQuery(sql)
	return Array.from(result)
}

module.exports = api