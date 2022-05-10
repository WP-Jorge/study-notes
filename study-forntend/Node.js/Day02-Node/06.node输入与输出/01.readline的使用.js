// 导入写入的包
let { writefs } = require('./write.js')

// 导入readline包
let readline = require('readline')

// 实例化接口对象
let r1 = readline.createInterface({
	// 输入输出都是在终端输入输出
	input: process.stdin,
	output: process.stdout
})

// 设置r1，提问事件
// r1.question("今晚吃啥？", answer => {
// 	console.log('答复：' + answer);
// 	r1.close()
// })

// 使用异步操作
function lcQuestion(title) {
	return new Promise((resolve, reject) => {
		r1.question(title, answer => {
			resolve(answer)
		})
	})
}

async function createPackage() {
	let name = await lcQuestion('您的包名是啥？')
	let des = await lcQuestion('您的描述是什么？')
	let main = await lcQuestion('您的主程序入口文件是什么？')
	let author = await lcQuestion('作者是谁？')
	
	let content = `{
		"name": "${name}",
		"des": "${des}",
		"main": "${main}",
		"author": "${author}"
	}`
	console.log(content);
	await writefs('./package.json', content)
	r1.close()
}

createPackage()

r1.on('close', () => {
	process.exit(0)
})