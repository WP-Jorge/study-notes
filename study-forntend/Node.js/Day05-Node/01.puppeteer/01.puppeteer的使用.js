const puppeteer = require('puppeteer')

// 配置登录浏览器
async function test() {
	// puppeteer.launch()实力开启浏览器，可以传入options对象，可以配置为无界面浏览器，也可以配置为有界面浏览器
	// 无界面浏览器性能更高，有界面一般用于调试开发
	// {headless: fasle}：打开浏览器界面
	let options = {
		// 设置为有界面
		headless: false,
		// 设置浏览器默认视口宽高
		defaultViewport: {
			width: 1400,
			height: 800
		}
	}
	let brower = await puppeteer.launch(options)
	// 打开新页面
	let page = await brower.newPage()
	// 访问页面
	await page.goto('https://www.dytt8.net/index.htm')
	// 截屏
	// await page.screenshot({path: 'screenshot.png'})
	// 获取页面内容
	// $$eval()可以是我们的回调函数运行在浏览器中，并且可以通过浏览器的方式进行输出
	let elements = await page.$$eval('.contain li a', element => {
		// console.log(element);
		let eles = []
		element.forEach((item, i) => {
			// console.log(item.innerText);
			let eleObj = {}
			if (item.getAttribute('href') !== '#') {
				eleObj = {
					href: item.getAttribute('href'),
					text: item.innerText
				}
				eles.push(eleObj)
			}
			// console.log(eleObj);
		})  
		return eles
	})
	// 浏览器的内容可以监听控制台的输出
	// page.on('console', (...args) => {
	// 	console.log(args);
	// })
	
	// 去国内
	let gnPage = await brower.newPage()
	await gnPage.goto(elements[2].href)
	
	// console.log(elements);
}

test()