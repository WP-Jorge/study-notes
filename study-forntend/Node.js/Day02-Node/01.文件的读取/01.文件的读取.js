// 导入模块
let fs = require('fs')

// node读写文件的同步与异步方式

// 同步
// 开始读文件,第一个参数：文件路径，第二个参数(可以不写)：读取文件操作对象，flag:读取方式，encoding：读取编码
let content = fs.readFileSync('hello.txt', {flag: 'r', encoding: 'utf-8'})
console.log(content.toString());

// 异步
// 异步读取文件，第一个参数：文件地址，第二个参数（可以不写）：文件读取方式，如上，第三个参数：回调函数
fs.readFile('hello.txt', (err, data) => {
	if (err) {
		console.log('读取失败');
	} else {
		console.log(data.toString());
	}
})

// 自行封装成promise
function fsRead(path) {
	return new Promise((resolve, reject) => {
		fs.readFile(path, (err, data) => {
			if (err) {
				reject(err)
			} else {
				resolve(data)
			}
		})
	})
}

fsRead('hello.txt').then(res => {
	console.log(res.toString());
}).catch(err => {
	console.log('读取失败');
})

async function ReadList() {
	let file2 = await fsRead('hello.txt')
	let file3 = await fsRead(file2)
	let file3Content = await fsRead(file3)
	console.log(file3Content.toString());
}

ReadList()