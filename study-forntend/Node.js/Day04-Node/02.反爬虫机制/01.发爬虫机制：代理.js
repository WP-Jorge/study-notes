const axios = require('axios')

let httpUrl = 'https://www.fabiaoqing.com/bqb/detail/id/54330.html'

// 使用代理，防止同一ip被禁
let options = {
	proxy: {
		host: '58.22.177.192',
		port: 9999
	}
}

axios.get(httpUrl, options).then(res => {
	console.log(res.data);
})