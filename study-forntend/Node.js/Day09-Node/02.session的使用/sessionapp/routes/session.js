var express = require('express');
var router = express.Router();

/* GET users listing. */
router.get('/', function(req, res, next) {
	res.send('respond with a resource');
});

// 设置session
router.get('/setsession', (req, res) => {
	// 登录之后，要获取user的姓名，vip等级，是否登录
	req.session.isLogin = true
	req.session.username = '小明'
	req.session.vipLevel = 5
	// 可以再这里设置过期时间
	req.session.cookie.maxAge = 100000
	res.send('登录状态已经设置到session中')
})

// 注销session
router.get('/loginout', (req, res) => {
	req.session.destroy(() => {
		console.log('退出登录');
	})
	res.send('退出登录')
})

// 获取session
router.get('/getsession', (req, res) => {
	console.log(req.session);
	if (req.session.isLogin === true) {
		res.send('欢迎等级为：' + req.session.vipLevel + ' 的 ' + req.session.username + ' 用户')
	} else {
		res.send('尚未登陆')
	}
})

module.exports = router;
