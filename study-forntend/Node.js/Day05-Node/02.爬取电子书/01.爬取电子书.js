const puppeteer = require('puppeteer')
const axios = require('axios')
const url = require('url')
const fs = require('fs')

let httpUrl = 'https://sobooks.cc/';

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
	
	async function getAllNum() {
		let page = await broswer.newPage()
		await page.setDefaultNavigationTimeout(0);
		await page.goto(httpUrl)
		
		// 截取谷歌请求
		intercepted(page)
		
		// 设置选择器，获取总页数
		let pageNum = await page.$eval('.pagination li:last-child span', e => {
			// console.log(e.innerHTML);
			let text = e.innerHTML
			text = text.substring(1, text.length - 2).trim()
			return text
		})
		page.close()
		return pageNum
	}
	let pageNum = await getAllNum()
	console.log('总页数：' + pageNum);
	
	async function pageList(num) {
		let pageListUrl = 'https://sobooks.cc/page/' + num
		let page = await broswer.newPage()
		await page.setDefaultNavigationTimeout(0);
		
		// 截取谷歌请求
		intercepted(page)
		
		// 访问列表地址
		await page.goto(pageListUrl)
		let arrList = await page.$$eval('.card .card-item .thumb-img>a', elements => {
			let arr = []
			elements.forEach((item, i) => {
				let obj = {
					href: item.getAttribute('href'),
					title: item.getAttribute('title')
				}
				arr.push(obj)
			})
			// console.log(arr);
			return arr
		})
		page.close()
		
		// 通过获取的数组的地址和标题去请求书籍的详情页
		arrList.forEach(async (item ,i) => {
			await wait(100 * i)
			getPageInfo(item)
		})
	}
	
	async function getPageInfo(pageObj) {
		try{
			let page = await broswer.newPage()
			await page.setDefaultNavigationTimeout(0);
			await page.goto(pageObj.href)
			
			// 截取谷歌请求
			intercepted(page)
			
			if (page.$('.euc-y-i') !== null) {
				// 通过表单输入进行暗号输入
				let inputEle = await page.$('.euc-y-i')
				// 聚焦输入框
				await inputEle.focus()
				// 输入暗号
				await page.keyboard.type('885570')
				// 按下提交
				let btnEle = await page.$('.euc-y-s')
				await btnEle.focus()
				await btnEle.click()
			} 
			await page.waitForNavigation({waitUntil: 'networkidle0'})
			let eleA = await page.$('.e-secret b a:first-child')
			let aHref = await eleA.getProperty('href')
			aHref = aHref._remoteObject.value.split('?url=')[1]
			// console.log(aHref)
			let content = {
				title: pageObj.title,
				href: aHref
			}
			content = JSON.stringify(content) + '\n'
			fs.writeFile('./books.json', content, {flag: 'a'}, () => {
				console.log('写入完成：' + pageObj.title)
				page.close()
			})
		}catch(e){
			console.log('写入失败：' + pageObj.title);
		}
	}
	pageList(303)
})()

// 截取谷歌请求函数
async function intercepted(page) {
	await page.setRequestInterception(true)
	// 监听请求事件，并对请求进行拦截
	page.on('request', interceptedRequest => {
		// 通过url模块对请求的地址进行解析
		let urlObj = url.parse(interceptedRequest.url())
		if (urlObj.hostname === 'googleads.g.doubleclick.net') {
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

// 目标：获取https://sobooks.cc/所有电子书书名和链接

// 进入网站，获取整个网站所有列表页的页数

// 获取列表页所有的链接

// 进入每个电子书的详情页获取下载电子书的网盘地址

// 将获取的数据保存到book.txt文档里面
