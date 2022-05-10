// 4.要想直接敲webpack打包修改后的文件，需要以下配置

const path = require('path')

module.exports = {
	entry: './src/main.js',
	output: {
		// 需要动态获取地址路劲
		// 需要导入const path = require('path')模块
		path: path.join(__dirname, './dist'), // path.join(__dirname, './dist')路径拼接 join可以改成resolve
		filename: 'bundle.js'
	}
	
}