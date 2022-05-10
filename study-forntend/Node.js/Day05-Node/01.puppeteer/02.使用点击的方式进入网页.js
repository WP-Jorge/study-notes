const puppeteer = require('puppeteer')

async function test() {
	let options = {
		headless: false,
		defaultViewport: {
			width: 1400,
			height: 800
		},
		// 设置每个步骤的毫秒数
		slowMo: 250
	}
	let brower = await puppeteer.launch(options)
	let page = await brower.newPage()
	await page.goto('https://www.dytt8.net/index.htm')
	// 获取页面对象
	// 通过点击跳转页面
	// let elementHandle = await page.$$('.contain li a')
	// elementHandle[2].click()
	
	// 通过表单输入进行搜索
	let inputELe = await page.$('.formhue')
	// 让光标进入输入框
	await inputELe.focus()
	// 往输入框输入内容
	await page.keyboard.type('蝙蝠侠')
	await page.$eval('.bd3rl>.search', element => {
		element.addEventListener('click', e => {
			e.cancelBubble = true
		})
	})
	// 点击按钮
	let btnEle = await page.$('.search input[name="Submit"]')
	await btnEle.click()
}

test()