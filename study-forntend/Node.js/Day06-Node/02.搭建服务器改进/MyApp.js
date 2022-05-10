const http = require('http')
const path = require('path')
const url = require('url')

class MyApp {
	constructor() {
	    let app = {}
		this.server = http.createServer()
		this.reqEvent = {}
		this.server.on('request', (req, res) => {
			let pathObj = path.parse(req.url)
			pathObj = path.parse(req.url)
			if (pathObj.dir in this.reqEvent) {
				req.pathObj = pathObj
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

module.exports = MyApp