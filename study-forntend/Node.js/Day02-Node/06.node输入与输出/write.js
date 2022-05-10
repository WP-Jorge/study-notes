let fs = require('fs')

function writefs(path, content) {
	return new Promise((resolve, reject) => {
		fs.writeFile(path, content, {flag: 'a', encoding: 'utf-8'}, err => {
			if (err) {
				reject(err)
			} else {
				resolve(err)
			}
		})
	})
}
module.exports = {writefs}