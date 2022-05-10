var express = require('express');
// 实例化一个路由模块，此路由相当于一个小的app实例
// 商城首页
let router = express.Router()

router.use((req, res, next) => {
	console.log('判断是否是商城用户');
	next()
})

router.get('/', (req, res) => {
	res.send('商城首页')
})
router.get('/list', (req, res) => {
	res.send('商城列表页')
})

module.exports = router