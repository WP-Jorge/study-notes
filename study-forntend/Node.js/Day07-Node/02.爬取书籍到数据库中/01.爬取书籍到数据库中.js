const mysql = require('mysql')
const axios = require('axios')
const cheerio = require('cheerio')

let page = 1
let count = 1
let options = {
	host: 'localhost',
	port: '3306',
	user: 'root',
	password: '111111',
	database: 'book'
}
let con = mysql.createConnection(options)
con.connect()

// 获取第N哥页面所有书籍的链接
async function getPageUrl(num) {
	try{
		let httpUrl = 'https://sobooks.cc/page/' + num
		let res = await axios.get(httpUrl)
		// console.log(res.data)
		let $ = cheerio.load(res.data)
		$('#cardslist .card-item .thumb-img.focus>a').each((i, item) => {
			let href = $(item).attr('href')
			getBookInfo(href)
			// console.log(href)
			// 根据地址访问书籍详情页
			
		})
		
	}catch(e){
		console.log(e);
	}
}

async function getBookInfo(href) {
	try{
		console.log('正在爬取：' + href);
		let res = await axios.get(href)
		let $ = cheerio.load(res.data)
		// 书籍图片
		let bookImg = $('.bookpic img').attr('src')
		// 书籍名称
		let bookname = $('.bookinfo li:nth-child(1)').text().substring(3)
		// 书籍作者
		let author = $('.bookinfo li:nth-child(2)').text().substring(3)
		// 格式
		let type = $('.bookinfo li:nth-child(3)').text().substring(3)
		// 标签
		let tag = $('.bookinfo li:nth-child(4)').text().substring(3)
		// 出版时间
		let pubtime = $('.bookinfo li:nth-child(5)').text().substring(3)
		// 出版时间
		let score = $('.bookinfo li:nth-child(6) b').attr('class').split(' ')[1]
		let reg = /dbpf(.*?)$/igs
		score = reg.exec(score)[1]
		// 分类
		let cataory = $('#mute-category a').text().trim()
		// 作者简介
		// let abrief = $('.article-content>p:nth-of-type(4)') ? $('.article-content>p:nth-of-type(4)').text() : null
		// 图书简介
		// let bbrief = $('.article-content>p:nth-of-type(1)') ? $('.article-content>p:nth-of-type(1)').text() : null
		let desLength = $('.article-content>p') ? $('.article-content>p').length : 0
		let des = null
		if (desLength != 0) {
			des = ''
			for (let i = 0; i < desLength; i++) {
				let desStr = await $('.article-content>p:nth-of-type('+ (i + 1) + ')').text() + '\n'
				des = des + desStr
			}
		}
		let arr = [href, bookname, bookImg, author, type, tag, pubtime, score, cataory, des]
		// console.log(arr)
		// 插入数据库
		let sql = 'insert into book values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)'
		con.query(sql, arr, (err, res) => {
			if (err) {
				console.log('爬取：' + href + '失败');
			} else {
				console.log('爬取：' + href + '成功');
			}
		})
	}catch(e){
		console.log('出现问题');
	}
}

// async function getDw(href) {
// 	let da = {
		
// 	}
// 	let res = await axios.post(href, {data: {e_secret_key: '885570'}})
// 	// console.log(dw)
// 	let $ = cheerio.load(res.data)
// 	let secret = $('body > section > div.content-wrap > div > article > div.e-secret').text()
// 	console.log(secret);
	
// }
let timer = setInterval(() => {
	if (page < 20) {
		getPageUrl(page++)
	} else {
		clearInterval(timer)
	}
}, 200)
// getBookInfo('https://sobooks.cc/books/17613.html')
// getDw('https://sobooks.cc/books/17613.html')
