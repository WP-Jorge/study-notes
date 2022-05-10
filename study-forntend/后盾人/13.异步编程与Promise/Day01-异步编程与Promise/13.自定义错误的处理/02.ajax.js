class ParamError extends Error {
	constructor(msg) {
		super(msg)
		this.name = 'ParamError'
	}
}

class HttpError extends Error {
	constructor(msg) {
		super(msg)
		this.name = 'HttpError'
	}
}

function ajax(url) {
	return new Promise((resolve, reject) => {
		if (!/^http/.test(url)) {
			throw new ParamError('请求地址格式错误')
		}
		let xhr = new XMLHttpRequest()
		xhr.open('get', url)
		xhr.send()
		xhr.onload = function () {
			if (JSON.parse(this.response).code == 404) {
				console.log('asd')
				reject(new HttpError('城市不存在'))
			} else if (this.status === 200) {
				resolve(JSON.parse(this.response))
			} else {
				reject('加载失败')
			}
		}
		xhr.onerror = function () {
			reject(this)
		}
	})
}
