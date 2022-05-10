var express = require('express');
var router = express.Router();
const sqlQuery = require('../myMysql')

// 进入详情页必须登录
// 1.引入cookie相关模块
// 2.引入一个判断是否登录的中间件
function isLoginMid(req, res, next) {
	if (!req.session.username) {
		// 未登录
		res.render('info', {
			title: '未登录',
			content: '尚未登陆，请进入登录页面登录',
			href: '/login',
			hrefText: '登录页'
		})
	} else {
		// 已登录继续访问
		next()
	}
}
// 3.登录界面

/* GET users listing. */
router.get('/:bookid', isLoginMid, async (req, res) => {
	let sql = 'select * from book where id = ?'
	let bookid = req.params.bookid
	let result = await sqlQuery(sql, [bookid])
	let options = {
		book: result[0],
		cataory: await getCataory()
	}
	res.render('bookInfo', options)
})

// 注销session
router.get('/out/loginout', (req, res) => {
	req.session.destroy(() => {
		console.log('退出登录');
	})
	res.send('退出登录')
})

async function getCataory() {
	// 获取所有分类
	let sql = 'select * from cataory'
	let result = await sqlQuery(sql)
	return Array.from(result)
}

module.exports = router;
