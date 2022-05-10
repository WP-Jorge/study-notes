var express = require('express');
var sqlQuery = require('../myMysql')
var crypto = require('crypto')
var router = express.Router();
/* GET users listing. */
router.get('/', function(req, res, next) {
  res.render('login')
});

// 获取登录数据
router.post('/', async (req, res) => {
	// 根据提交的邮箱和密码判断是否是正确的账号密码
	let sql = 'select * from user where email = ? and password = ?'
	let arr = [req.body.email, jiami(req.body.password)]
	let result = await sqlQuery(sql, arr)
	if (result.length != 0) {
		// 登录成功
		user = result[0]
		req.session.username = user.username
		res.render('info', {
			title: '登录成功',
			content: '登陆成功，即将进入首页',
			href: '/',
			hrefText: '首页'
		})
	} else {
		res.render('info', {
			title: '登录失败',
			content: '登陆失败，账号或密码错误，即将进入登录页',
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
