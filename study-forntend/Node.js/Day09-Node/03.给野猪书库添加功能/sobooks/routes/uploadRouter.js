var express = require('express');
var fs = require('fs')

// 引入上传模块
const multer = require('multer')
// 配置上传对象
// 可以设置文件存放路径，以及限制{dest: '文件存放位置', limit: {filesize: 文件大小 单位kb 如1024*1024*20为20M, files: 文件最大上传数量}}
let  upload = multer({dest: './public/upload'})

var router = express.Router();

/* GET users listing. */
router.get('/', function(req, res, next) {
  res.render('uploadimg')
});

// 处理上传的POST请求
// 上传一张图片.upload.single(上传文件的名称，表单里面的name)
// 多个文件上传使用.upload.array(文件名称数组[, 最大数量])
router.post('/', upload.single('imgfile'), (req, res) => {
	// 重命名文件
	let oldPath = req.file.destination + '/' + req.file.filename
	let newPath = req.file.destination + '/' + req.file.filename + req.file.originalname
	fs.rename(oldPath, newPath, () => {
		console.log('重命名成功');
	})
	// res.send('<h1>上传成功</h1><img src="/upload/' + req.file.filename + req.file.originalname +'"/>')
	res.json({
		state: 200,
		imgUrl: '/upload/' + req.file.filename + req.file.originalname
	})
})

router.get('/ajax', (req, res) => {
	res.render('uploadajax')
})

module.exports = router;
