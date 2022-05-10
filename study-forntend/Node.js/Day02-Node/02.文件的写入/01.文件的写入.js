let fs = require('fs')

// 如果文件不存在，则创建一个文件
// 文件的写入，第一个参数：文件的位置，第二个参数：写入的内容，第三个参数：写入配置（可以省略），第三个参数：回调函数
// w：覆盖，a：追加
// 由于是异步操作，所以写入顺序不一定，可以采用promise封装，采用async+awaite方式                                                                                                                                                                                                                                                                                                                                                                       
fs.writeFile('test.txt', '晚上吃啥啊\n', {flag: 'a', encoding: 'utf-8'}, err => {
	if (err) {
		console.log('写入失败');
	} else {
		console.log('写入成功');
	}
})

// 使用primise+async+await实现封装
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

async function writeList() {
	await writefs('晚饭.txt', '红烧肉\n')
	await writefs('晚饭.txt', '牛排\n')
	await writefs('晚饭.txt', '黄焖排骨\n')
}

writeList()

module.exports = {writefs}