const express = require('express')
const sqlQuery = require('./myMysql')
let app = express()

app.get('/', async (req, res) => {
	// 将数据库中前28本书获取出来
	let sql = 'select id, bookname, bookimg, author, cataory from book limit 0, 28'
	let result = await sqlQuery(sql)
	// let resJson = JSON.stringify(Array.from(result))
	// res.send(resJson)
	
	// 直接将数据封装成json发送出去
	res.json(result)
})
app.get('/xiaoshuowenxue', async (req, res) => {
	let sql = 'select id, bookname, bookimg, author, cataory from book where cataory = "小说文学" limit 0, 28'
	let result = await sqlQuery(sql)
	res.json(result)
})
app.get('/books/:bookid', async (req, res) => {
	let sql = 'select * from book where id = ?'
	let bookid = req.params.bookid
	let result = await sqlQuery(sql, [bookid])
	res.json(result)
})

module.exports = app