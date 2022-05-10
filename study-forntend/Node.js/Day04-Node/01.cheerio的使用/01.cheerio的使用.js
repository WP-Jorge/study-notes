// 导入
// 作用获取html文本，内容的获取跟jquery一样
const cheerio = require('cheerio')
const axios = require('axios')
const path = require('path')
const fs = require('fs')
const url = require('url')

let httpUrl = 'https://www.fabiaoqing.com/bqb/lists/page/1.html'
let baseUrl = 'https://www.fabiaoqing.com'

// 将延迟函数封装成promise对象
function wait(milliSecondes) {
	return new Promise((resolve, reject) => {
		setTimeout(() => {
			resolve('成功执行延迟函数，延迟 ' + milliSecondes + ' 秒')
		}, milliSecondes)
	})
}


axios.get(httpUrl).then(res => {
	// 使用cheerio解析html文档
	let $ = cheerio.load(res.data)
	$('.bqba').each((i, element) => {
		// 获取所有子表情包的跟页面链接
		let imgPath = baseUrl + $(element).attr('href')
		let title = $(element).attr('title')
		let reg = /([^\/\\"<>\?\*|]{0,})/igs
		title = reg.exec(title)[1]
		fs.mkdir('./imgs/' + title, err => {
			if (err) {
				console.log('\n创建文件夹失败:' + title + '\n');
			}
		})
		parsePage(imgPath, title)
	})
})

async function parsePage(url, title) {
	try {
		let res = await axios.get(url).catch(err => {
			console.log('失败链接' + url)
		})
		let $ = cheerio.load(res.data)
		// console.log($('.bqbppdetail'));
		$('.bqbppdetail').each(async function(i, item) {
			let imgUrl = $(item).attr('data-original')
			// console.log(imgUrl);
			let extName = path.extname(imgUrl)
			// 图片路径
			let imgPath = `./imgs/${title}/${title}-${i}${extName}`
			// 创建图片写入流
			let ws = fs.createWriteStream(imgPath)
			// console.log('阿斯顿马拉喀什大美女绿卡');
			let ress = await axios.get(imgUrl, {
				responseType: 'stream'
			})
			// console.log(ress);
			// console.log(11111);
			console.log(imgPath)
			ress.data.pipe(ws)
		})
	}catch(e) {
		// console.log(e)
	}
}


function req(url) {
	return new Promise((resolve, reject) => {
		axios.get(url).then(res => {
			console.log('\n正确' + url + '\n');
			resolve(res)
		}).catch(err => {
			console.log('\n错误' + url + '\n');
			reject()
		})
	})
}
