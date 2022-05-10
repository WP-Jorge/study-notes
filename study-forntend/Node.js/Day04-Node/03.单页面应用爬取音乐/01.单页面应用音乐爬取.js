// 目标：下载音乐
// 1.获取音乐相关信息，通过音乐相关信息获取mp3地址
// 2.如何获取大量音乐信息，通过获取音乐列表
// 3.通过音乐分类页获取音乐列表

const axios = require('axios')
const fs = require('fs')
const path = require('path')

let timer = null
let i = 1

timer = setInterval(() => {
	console.log(i);
	getPage(i++)
	if (i >= 30) {
		clearInterval(timer)
	}
}, 1000)



async function getPage(pageNum) {
	let httpUrl = 'http://www.app-echo.com/api/recommend/sound-day?page=' + pageNum
	let res = await axios.get(httpUrl)
	// console.log(res.data);
	res.data.list.forEach((item, i) => {
		let title = item.sound.name
		let mp3Url = item.sound.source
		let fileName = path.parse(mp3Url).name

		let content = `${title},${mp3Url},${fileName}\n`
		// fs.writeFile('./music.txt', content, {
		// 	flag: 'a',
		// 	encoding: 'utf-8'
		// }, () => {
		// 	// console.log('写入完成:' + title);
		// })
		download(mp3Url, fileName)
		// console.log(title)
		// console.log(path.parse(mp3Url));
		// console.log(mp3Url);
	})
}

async function download(url, fileName) {
	// console.log(url);
	// let res = await axios.get(url, {responseType: 'stream'})
	try {
		axios.get(url, {
			responseType: 'stream'
		}).then(res => {
			let ws = fs.createWriteStream('./mp3/' + fileName + '.mp3')
			res.data.pipe(ws)
			console.log('成功')
		}).catch(err => console.log('失败'))
	} catch (e) {
		//TODO handle the exception
		console.log(e);
	}

	// res.data.on('close', () => {
	// 	ws.close()
	// })
}
