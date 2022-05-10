var express = require('express');
var crypto = require('crypto')
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

// 设置cookie
router.get('/setcookie', (req, res) => {
	// 设置基础的cookie，有效期默认一个会话，浏览器关闭就失效
	// res.cookie('isLogin', 'true')
	// 设置过期时间maxAge:100s
	// 不让前端人员拿到cookie httpOnly:true  (设置后前端人员不能看到或者获取到cookie)
	// res.cookie('isLogin', 'true', {maxAge: 100000, httpOnly:true})
	// 设置加密操作signed:true 开启加密
	res.cookie('isLogin', 'true', {signed: true})
	res.send('cookie设置成功')
})

// 后台页面
router.get('/admin', (req, res) => {
	if (req.cookies.isLogin === 'true') {
		res.send('登录成功')
	} else {
		res.send('登录失败')
	}
})

// 对加密的cookie进行解码
router.get('/adminSecret', (req, res) => {
	// console.log(req.signedCookies);
	
	if (req.signedCookies.isLogin === 'true') {
		res.send('登录成功, 加密cookie')
	} else {
		res.send('登录失败, 加密cookie')
	}
})

// 定义加密算法
let secretCookie = {}
function setSecretCookie(str, secretStr) {
	secretCookie[secretStr] = str
}
function getSecretCookie(secretStr) {
	return secretCookie[secretStr]
}
// 加密函数
function jiami(str) {
	// 1.导入node中的crypto
	// 创建一个md5的加密算法
	let sf = crypto.createHash('md5')
	// 加密字符串
	let randomNum = Math.random()
	sf.update(str + '' + randomNum)
	// 将二进制加密数据以字符串输出
	let content = sf.digest('hex')
	return content
}
// md5加密方式
router.get('/secret', (req, res) => {
	res.send(jiami('123456'))
})

// 自己定义加密cookie
router.get('/setAppSecret', (req, res) => {
	let str = '123456'
	let secretStr = jiami(str)
	res.cookie('register', secretStr)
	// 将加密的明文与密文储存起来
	setSecretCookie(str, secretStr)
	res.send('cookie加密成功')
})
// 获取自己加密的cookie
router.get('/getAppSecret', (req, res) => {
	// 获取加密后的密文
	let secretStr = req.cookies.register
	let content = getSecretCookie(secretStr)
	res.send(secretStr + ' 解密后的内容：' + content)
})

module.exports = router;
