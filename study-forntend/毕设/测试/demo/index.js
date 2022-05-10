let axios = require('axios');
let fs = require('fs');
let path = require('path');
let puppeteer = require('puppeteer');
let write = require('../write');

(async function () {
	// 音乐网站
	// let musicUrl = 'http://tool.liumingye.cn/music/?page=searchPage';
	let musicUrl = 'https://tool.liumingye.cn/music/?page=audioPage&type=YQD&name=%E5%8F%8C%E7%AC%99';
	// 开发配置
	let devOptions = {
		headless: false,
		slowMo: 30,
		args: ['--start-maximized'],
		defaultViewport: {
			width: 1000,
			height: 600
		}
	};
	// 生产配置
	let options = {
		headless: true,
		slowMo: 100
	};
	// 开启一个浏览器
	let boswer = await puppeteer.launch(devOptions);
	// 开启一个页面
	let page = await boswer.newPage();
	// 前往目标页面
	await page.goto(musicUrl);
	// // 选中搜索类别
	// await page.select('#type', 'YQD');
	// // 聚焦搜索框
	// let input = await page.$('#input');
	// await input.focus();
	// // 输入关键字
	// await page.keyboard.type('双笙');
	// // 点击搜索

	// page.click('.input-group-append:nth-last-child(1)')
	// const response = await Promise.all([
	// 	page.waitForNavigation(),
	// ]);
	// console.log(response);
	// 下载歌曲
	// 获取所有歌曲的li
	let lis = await page.$$('#player .aplayer-list ol li');
	await page.$eval(`#main > div.main-view > div.goto-top`, item => {
		item.style.display = 'none';
	});
	let musics = [];
	for (let index = 0; index < lis.length; index++) {
		try {
			await page.hover(`#player .aplayer-list ol li:nth-child(${index + 1})`);
			await page.click(`#player .aplayer-list ol li:nth-child(${index + 1}) span:nth-child(1)`);
			let res = await getAllMusicInfo();
			console.log(res);
			let musicInfo = {};
			musicInfo['searchWord'] = '双笙';
			musicInfo['music'] = res;
			musics.push(musicInfo)
			await page.click(`#m-download > div > div > div.modal-footer > button`);
		} catch (error) {}
	}
	await write.fsWrite('./musics/musics.json', JSON.stringify(musics, null, 4));

	async function getAllMusicInfo() {
		// let items = await page.$$('.input-group mb-2');
		let valueIds = ['name', 'pic', 'url_m4a', 'url_128', 'url_320', 'url_flac', 'url_ape', 'url_dsd', 'url_lrc'];
		let music = {};
		for (let i = 0; i < valueIds.length; i++) {
			let key = await page.$eval(
				`#m-download > div > div > div.modal-body > div:nth-child(${i + 1}) > div.input-group-prepend > label`,
				item => {
					return item.innerText;
				}
			);
			let value = await page.$eval(`#${valueIds[i]}`, item => {
				return item.value;
			});
			if (value) {
				music[key] = value;
			}
		}
		return music;
		// let music = {};
		// let key = await page.$eval(
		// 	`#m-download > div > div > div.modal-body > div:nth-child(1) > div.input-group-prepend > label`,
		// 	item => {
		// 		return item.innerText;
		// 	}
		// );
		// let value = await page.$eval(`#name`, item => {
		// 	return item.value;
		// });
		// music[key] = value;

		// key = await page.$eval(`#m-download > div > div > div.modal-body > div:nth-child(2) > div.input-group-prepend > label`, item => {
		// 	return item.innerText;
		// });
		// value = await page.$eval(`#pic`, item => {
		// 	return item.value;
		// });
		// music[key] = value;

		// key = await page.$eval(`#m-download > div > div > div.modal-body > div:nth-child(3) > div.input-group-prepend > label`, item => {
		// 	return item.innerText;
		// });
		// value = await page.$eval(`#url_m4a`, item => {
		// 	return item.value;
		// });
		// music[key] = value;

		// key = await page.$eval(`#m-download > div > div > div.modal-body > div:nth-child(4) > div.input-group-prepend > label`, item => {
		// 	return item.innerText;
		// });
		// value = await page.$eval(`#url_320`, item => {
		// 	return item.value;
		// });
		// music[key] = value;

		// key = await page.$eval(`#m-download > div > div > div.modal-body > div:nth-child(5) > div.input-group-prepend > label`, item => {
		// 	return item.innerText;
		// });
		// value = await page.$eval(`#url_flac`, item => {
		// 	return item.value;
		// });
		// music[key] = value;

		// key = await page.$eval(`#m-download > div > div > div.modal-body > div:nth-child(6) > div.input-group-prepend > label`, item => {
		// 	return item.innerText;
		// });
		// value = await page.$eval(`#url_ape`, item => {
		// 	return item.value;
		// });
		// music[key] = value;

		// key = await page.$eval(`#m-download > div > div > div.modal-body > div:nth-child(7) > div.input-group-prepend > label`, item => {
		// 	return item.innerText;
		// });
		// value = await page.$eval(`#url_lrc`, item => {
		// 	return item.value;
		// });
		// music[key] = value;
	}

	// async function getAllMusicInfo() {
	// 	await page.waitForSelector('.modal-dialog .modal-content .modal-body .mb-2');
	// 	let musicDiv = await page.$$eval('.modal-dialog .modal-content .modal-body .mb-2', items => {
	// 		console.log(items);
	// 		// let musicInfos = [];
	// 		// items.forEach(async (item, index) => {
	// 		// 	let info = {};
	// 		// 	let musicTitle = await page.$eval(`.modal-dialog .modal-content .modal-body .mb-2:nth-child(${index + 1})`, item => {
	// 		// 		info[item.firstChild.firstChild.innerText] = item.firstChild.nextSibling.value;
	// 		// 	});
	// 		// 	musicInfos.push(info);
	// 		// });
	// 		// return musicInfos;
	// 	});
	// 	console.log(musicDiv);
	// }
})();

function delay(delay) {
	return new Promise((resolve, reject) => {
		setTimeout(() => {
			resolve();
		}, delay);
	});
}
