const express = require('express')
const sqlQuery = require('./myMysql')
const path = require('path')
const cookieParser = require('cookie-parser');
const session = require('express-session')

const bookRouter = require('./routes/booksRouter')
const loginRouter = require('./routes/loginRouter')
const registerRouter = require('./routes/registerRouter')
const uploadRouter = require('./routes/uploadRouter')
const downloadRouter = require('./routes/downloadRouter')

let app = express()
// 使用模板来渲染页面
let ejs = require('ejs')
// 将模板引擎与express应用相关联
app.set('views', 'views') // 设置视图的对应位置
app.set('view engine', 'ejs') // 设置默认的模板引擎
app.set('ejs', ejs.__express) // 定义模板引擎

app.use(session({
	secret: Math.random().toString(),
	resave: true,
	cookie: {
		maxAge: 7 * 24 * 60 * 60 * 1000
	},
	saveUninitialized: true
}))
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser('secret'));
app.use(express.static(path.join(__dirname, 'public')))

app.get('/', async (req, res) => {
	let cid = 0
	let page = 1
	let sql1 = 'select id, bookname, bookimg, author, book.cataory from book limit 0, 28'
	let result1 = await sqlQuery(sql1)
	
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
	res.render('bookIndex', options)
})


app.use('/books', bookRouter)
app.use('/login', loginRouter)
app.use('/register', registerRouter)
app.use('/imgUpload', uploadRouter)
app.use('/download', downloadRouter)


// 设置分类页面的路由
// app.get('/cataory/:cid', async (req, res) => {
// 	let sql = 'select id, bookname, bookimg, author, book.cataory from cataory, book where cataory.cataory = book.cataory and cid = ? limit 0, 28'
// 	let cid = req.params.cid
// 	let result = await sqlQuery(sql, [cid])
// 	let options = {
// 		books: Array.from(result),
// 		cataory: await getCataory()
// 	}
// 	res.render('bookIndex', options)
// })

// 设置搜索路由
app.get('/search/:keyword/page/:pid', async (req, res) => {
	let keyword = req.params.keyword
	let page = parseInt(req.params.pid)
	let sql = 'select id, bookname, bookimg, author, book.cataory from book where bookname like "%' + keyword + '%" or author like "%' + keyword + '%" limit ?, 28'
	let result1 = await sqlQuery(sql, [(page - 1) * 28])
	// 获取总页数
	let sql2 = 'select count(distinct bookname) as num from cataory, book where cataory.cataory = book.cataory and bookname like "%' + keyword + '%" or author like "%' + keyword + '%"'
	let result2 = await sqlQuery(sql2)
	let totalPage = Math.ceil(result2[0].num / 28)
	// console.log(result2[0].num);
	console.log(totalPage);
	// 设置分页的起始点
	let startPage = (page - 4) < 1 ? 1 : page - 4
	let endPage = (page + 5) > totalPage ? totalPage : page + 5
	let options = {
		books: Array.from(result1),
		cataory: await getCataory(),
		totalPage,
		currentPage: page,
		startPage,
		endPage,
		keyword
	}
	res.render('searchIndex', options)
})

// 分类分页
app.get('/cataory/:cid/page/:pid', async (req, res) => {
	let cid = req.params.cid
	let page = parseInt(req.params.pid)
	let sql1 = ''
	let result1
	if (req.params.cid === '0') {
		sql1 = 'select id, bookname, bookimg, author, book.cataory from cataory, book where cataory.cataory = book.cataory limit ?, 28'
		result1 = await sqlQuery(sql1, [(page - 1) * 28])
		// 获取总页数
		var sql2 = 'select count(id) as num from book'
		
	} else {
		sql1 = 'select id, bookname, bookimg, author, book.cataory from cataory, book where cataory.cataory = book.cataory and cid = ? limit ?, 28'
		result1 = await sqlQuery(sql1, [cid, (page - 1) * 28])
		// 获取总页数
		var sql2 = 'select count(id) as num from cataory, book where cataory.cataory = book.cataory and cid = ?'
	}
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
	res.render('bookIndex', options)
})

async function getCataory() {
	// 获取所有分类
	let sql = 'select * from cataory'
	let result = await sqlQuery(sql)
	return Array.from(result)
}

module.exports = app