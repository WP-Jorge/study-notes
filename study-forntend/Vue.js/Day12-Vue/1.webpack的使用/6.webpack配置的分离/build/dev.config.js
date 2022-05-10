// 开发时依赖
const path = require('path')
const VueLoaderPlugin = require('vue-loader/lib/plugin') // vue插件
const webpackMerge = require('webpack-merge')
const baseConfig = require('./base.config.js')

module.exports = webpackMerge.merge(baseConfig, {
	// "dev": "webpack-dev-server --open chrome --contentBase src"
	//--open:键入 npm run dev 后自动打开浏览器 后边可添加参数指定打开浏览器
	//--port：指定打开端口号
	// chrome以谷歌浏览器打开
	//--contentBase：设置默认打开地址的根路径
	//--hot：局部刷新  只在css样式中起效 webpack4.0后自动打开
	// "dev": "webpack-dev-server --open chrome --port 3000 --contentBase src --hot"
	devServer: {
		contentBase: path.join(__dirname, '../dist'), // 服务的跟文件夹
		inline: true ,// 开启实时监听
		open: true ,// 启动 npm run dev 自动打开浏览器
	},
	plugins: [
		new VueLoaderPlugin(),
	]
})