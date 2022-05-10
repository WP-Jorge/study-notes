const http = require('http')
const path = require('path')
const url = require('url')
const fs = require('fs')

class MyApp {
	constructor() {
	    let app = {}
		this.server = http.createServer()
		this.reqEvent = {}
		this.server.on('request', (req, res) => {
			res.render = render
			res.getContentType = getContentType
			res.fs = fs
			let pathObj = path.parse(req.url)
			pathObj = path.parse(req.url)
			// console.log(pathObj);
			if (pathObj.dir in this.reqEvent) {
				res.pathObj = pathObj
				this.reqEvent[pathObj.dir](req, res)
			} else {
				res.setHeader('Content-Type', 'text/html; charset=utf-8')
				res.end('<h1>404页面找不到</h1>')
			}
		})
	}
	on(url, fn) {
		this.reqEvent[url] = fn
	}
	run(port, callback) {
		this.server.listen(port, callback)
	}
}

function render(options, path) {
	fs.readFile(path, {encoding: 'utf-8'}, (err, data) => {
		if (err) {
			console.log(err);
		} else {
			// 匹配循环的变量并替换内容
			data = replaceArr(data, options)
			
			// 匹配普通的变量，并替换内容
			data = replaceVar(data, options)
			
			this.end(data)
		}
	})
}

function getContentType(extName) {
	switch (extName) {
		case ".jpg":
			return "image/jpeg";
		case ".html":
			return "text/html;charset=utf-8";
		case ".js":
			return "text/javascript;charset=utf-8";
		case ".json":
			return "text/json;charset=utf-8";
		case ".gif":
			return "image/gif";
		case ".css":
			return "text/css"
	}
}

function replaceVar(data, options) {
	let reg = /\{\{(.*?)\}\}/igs
	let result
	while(result = reg.exec(data)) {
		let strKey = result[1].trim()
		let strValue = eval('options.' + strKey)
		data = data.replace(result[0], strValue)
	}
	return data
}

function replaceArr(data, options) {
	reg = /\{\% for \{(.*?)\} \%\}(.*?)\{\% endfor \%\}/igs
	while (result = reg.exec(data)) {
		let strKey = result[1].trim()
		// 通过key值获取数组
		let strValueArr = options[strKey]
		let listStr = ''
		strValueArr.forEach((item, i) => {
			// 替换每一个内容里的变量
			listStr = listStr + replaceVar(result[2], {item})
		})
		data = data.replace(result[0], listStr)
	}
	return data
}

module.exports = MyApp