let axios = require('axios')
let fs = require('fs')
let {writefs, fsDir} = require('./write.js')

let httpUrl = 'https://www.1905.com/vod/list/n_1/o3p1.html'

// 1.获取其实页面的所有分类
async function getClassUrl() {
	let res = await req(httpUrl)
	// res = res.data
	// console.log(res);
	
	// 解析正文内容
	// 获取分类
	let reg1 = /<span class="search-index-L">类型(.*?)<div class="grid-12x">/igs
	let result1 = reg1.exec(res.data)[1]
	// console.log(result1);
	
	// 获取分类及url
	let reg2 = /<a href="javascript\:void\(0\);" onclick="location\.href='(.*?)';return false;" .*?>(.*?)<\/a>/igs
	let result2
	let arrClass = []
	while(result2 = reg2.exec(result1)) {
		if (result2[2] !== '全部') {
			let obj = {
				className: result2[2],
				url: result2[1]
			}
			arrClass.push(obj)
			await fsDir('./movies/' + result2[2])
			getMovies(obj.url, result2[2])
			// console.log(result2[1]);
		}
	}
	// console.log(arrClass);
}

function req(url) {
	return new Promise((resolve, reject) => {
		axios.get(url).then(res => {
			resolve(res)
		}).catch(err => {
			reject(err)
		})
	})
}

// 2.获取分类里的电影链接
// 3.根据电影链接获取电影的详细信息
// 通过分类获取页面中的电影链接
async function getMovies(url, moviesType) {
	let res = await req(url)
	let reg = /<a class="pic-pack-outer" target="_blank" href="(.*?)".*?><img/igs
	var result
	var arrList = []
	// console.log(res.data);
	while(result = reg.exec(res.data)) {
		arrList.push(result[1])
		parsePage(result[1], moviesType)
		// console.log(result[1]);
	}
	// console.log(moviesType);
	// console.log(url);
	// console.log(arrList);
}

// 
async function parsePage(url, moviesType) {
	let res = await req(url)
	
	let reg = /playerBox-info-cnName">(.*?)<\/h1>.*?id="playerBoxIntroCon">(.*?)<a.*?导演.*?target="\_blank" title="(.*?)" data-hrefexp/igs
	let result = reg.exec(res.data)
	// console.log(result[3]);
	let movie = {
		name: result[1] ? result[1] : null,
		des: result[2] ? result[2] : null,
		author: result[3] ? result[3] : null,
		movieUrl: url,
		moviesType
	}
	let jsonMovie = JSON.stringify(movie)
	writefs('./movies/' + moviesType + '/' + movie.name + '.json', jsonMovie)
	// console.log(movie);
}

getClassUrl()