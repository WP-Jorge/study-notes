/**
 * webpack的配置文件
 */
const { resolve } = require('path')

module.exports = {
	// 打包模式
	mode: 'production', // development 开发模式，production 生产模式
	
	// 入口文件
	entry: './src/index.js',
	
	// 出口文件
	output: {
		// 输出目录（输出目录必须是绝对路径）
		path: resolve(__dirname, 'dist'), // 当前变量下的dist目录
		
		// 输出文件的名称
		filename: 'main.js'
	},
	
	// 模块配置
	module: {
		rules: [
			// 指定多个配置规则
		]
	},
	
	// 开发服务器
	devServer: {
	
	},
	
	// 插件的配置
	plugins: [
	
	]
}