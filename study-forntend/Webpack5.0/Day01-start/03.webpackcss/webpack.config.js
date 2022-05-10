/**
 * webpack的配置文件
 */
const { resolve } = require('path')
const MiniCssExtractPlugin = require('mini-css-extract-plugin')

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
			{
				// css
				test: /\.css$/i,
				// use 中的 loader 加载顺序是从下到上，从后到前
				use: [
					// ③ style-loader 将 js 中的样式挂载到 <style> 标签中
					// 'style-loader',
					
					// ③ 替换style-plugin，将css打包到独立的文件中
					MiniCssExtractPlugin.loader,
					
					// ② css-loader 按照CommonJS规范，将样式文件输出到js中
					'css-loader',
					
					// ① 通过 postcss-loader 给样式属性添加浏览器前缀
					'postcss-loader'
				]
			}, {
				// less
				test: /\.less$/i,
				// use 中的 loader 加载顺序是从下到上，从后到前
				use: [
					// ④ style-loader 将 js 中的样式挂载到 <style> 标签中
					// 'style-loader',
					
					// ④ 替换style-plugin，将css打包到独立的文件中
					MiniCssExtractPlugin.loader,
					
					// ③ css-loader 按照CommonJS规范，将样式文件输出到js中
					'css-loader',
					
					// ② 通过 postcss-loader 给样式属性添加浏览器前缀
					'postcss-loader',
					
					// ① 将less转换成普通的css
					'less-loader'
				]
			}
		]
	},
	
	// 开发服务器
	devServer: {
	
	},
	
	// 插件的配置
	plugins: [
		// 实例化插件
		new MiniCssExtractPlugin({
			// 可以传入配置信息
			
			// 打包之后的文件位置
			// [name] 表示原来的名字
			filename: 'css/[name].css'
		})
		
	]
}