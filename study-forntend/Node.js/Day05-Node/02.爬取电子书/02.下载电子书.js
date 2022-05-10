const puppeteer = require('puppeteer')
const axios = require('axios')
const url = require('url')
const fs = require('fs')
const readline = require('readline')
const {fsRead, fsWrite} = require('./write')

let httpUrl = 'https://sobooks.cc/';
let downloadBaseUrl = 'https://306t.com';

(async function() {
	let debugOptions = {
		headless: false,
		defaultViewport: {
			width: 1400,
			height: 800
		},
		// 设置每个步骤的毫秒数
		slowMo: 250
	}
	let options = {
		headless: true
	}
	let broswer = await puppeteer.launch(options)
	
	let bookList = await parseJSON('./books.json')
	let i = 0
	
	async function downloadBook() {
		if (i >= bookList.length) {
			return '下载完成'
		}
		let page = await broswer.newPage()
		let bookObj = bookList[i++]
		await page.goto(bookObj.href)
		// 由于a链接是通过js渲染出来的，而不是页面请求回来就有的内容，需要等待
		await page.waitForSelector('#table_files tbody tr:first-child td:nth-child(2) a')
		let elementA = await page.$('#table_files tbody tr:first-child td:nth-child(2) a')
		let name = await page.$eval('#table_files tbody tr:first-child td:nth-child(2) a', ele => ele.innerHTML)
		let extendName = name.split('.')
		extendName = '.' + extendName[extendName.length - 1]
		await elementA.click()
		let href = await elementA.getProperty('href')
		href = href._remoteObject.value
		// console.log(href._remoteObject.value);
		await bookLinkPage(href, bookObj.title, extendName)
		
	}
	
	async function bookLinkPage(linkUrl, title, extendName) {
		let page = await broswer.newPage()
		// console.log(linkUrl);
		await page.goto(linkUrl)
		await page.waitForSelector('.btn.btn-outline-secondary.fs--1')
		let elementBtn = await page.$('.btn.btn-outline-secondary.fs--1')
		await elementBtn.click()
		
		// 拦截下载链接，进行axios下载
		await page.setRequestInterception(true)
		// 监听请求事件，并对请求进行拦截
		page.on('request', async interceptedRequest => {
			// 通过url模块对请求的地址进行解析
			let urlObj = url.parse(interceptedRequest.url())
			// console.log(urlObj.hostname);
			if (urlObj.hostname === '199-cmcc-aa.tv002.com') {
				// console.log('截获地址：' + urlObj.href);
				let ws = fs.createWriteStream('./books/' + title + extendName)
				axios.get(urlObj.href, {responseType: 'stream'}).then(async res => {
					console.log('开始下载书籍：' + title);
					res.data.pipe(ws)
					ws.on('close', async () => {
						console.log('书籍下载完成：' + title);
						// 下载完成后继续下载下一本书
						await downloadBook()
						page.close()
					})
				})
				// await wait(i++ * 5000)
				
				
				await interceptedRequest.abort()
			} else {
				interceptedRequest.continue()
			}
		})
		
		// page.on('requestfinished', req => {
		// 	console.log('下载完成：' + req.url());
		// })
	}
	
	downloadBook()
})()

// 截取谷歌请求函数
async function intercepted(page) {
	await page.setRequestInterception(true)
	// 监听请求事件，并对请求进行拦截
	page.on('request', interceptedRequest => {
		// 通过url模块对请求的地址进行解析
		let urlObj = url.parse(interceptedRequest.url())
		if (urlObj.hostname.indexOf('google') != -1) {
			// 如果是谷歌请求，丢弃
			interceptedRequest.abort()
		} else {
			interceptedRequest.continue()
		}
	})
}

// 延迟函数
function wait(milliSecondes) {
	return new Promise((resolve, reject) => {
		setTimeout(() => {
			resolve('成功执行延迟函数，延迟：' + milliSecondes + '秒')
		}, milliSecondes)
	})
}

async function parseJSON(path) {
	// 读取文本内容
	let fRead = fs.createReadStream('./books.json')
	let ci = readline.createInterface({
		input: fRead
		// output: process.stdout
	})
	let bookList = []
	return new Promise(resolve => {
		ci.on('line', line => {
			line = JSON.parse(line)
			bookList.push(line)
		})
		ci.on('close', () => {
			resolve(bookList)
		})
	})
}


// downloadBook()
// console.log(parseJSON('./books.json').then(res => console.log(res)))
// async function print() {
// 	await parseJSON('./books.json').then(res => console.log(res))
// }
// print()