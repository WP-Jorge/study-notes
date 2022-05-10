var express = require('express');
var sqlQuery = require('../myMysql')
var crypto = require('crypto')
var router = express.Router();

/* GET users listing. */
router.get('/', function(req, res, next) {
  res.render('register')
});

// 接收注册
router.post('/', async (req, res) => {
	// 获取表单提交的邮箱、密码、用户名
	let email = req.body.email
	let password = req.body.password
	password = jiami(password)
	let username = req.body.username
	// 判断邮箱是否已经被注册，如果已经注册将不再注册
	let sql = 'select * from user where email = ?'
	let result = await sqlQuery(sql, [email])
	if (result.length != 0) {
		// 邮箱已经注册
		res.render('info', {
			title: '注册失败',
			content: '邮箱已被注册，可直接进行登录',
			href: '/register',
			hrefText: '注册页'
		})
	} else {
		// 此邮箱尚未注册，可以进行注册
		sql = 'insert into user values(null, ?, ?, ?)'
		result = await sqlQuery(sql, [username, email, password])
		res.render('info', {
			title: '注册成功',
			content: '注册成功，即将进入登录页面',
			href: '/login',
			hrefText: '登录页'
		})
	}
})

function jiami(str) {
	let salt = 'salt'
	let obj = crypto.createHash('md5')
	str += salt
	obj.update(str)
	return obj.digest('hex')
}

module.exports = router;
