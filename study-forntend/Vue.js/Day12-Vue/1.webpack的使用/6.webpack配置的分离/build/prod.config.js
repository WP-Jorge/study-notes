// 引入插件
const VueLoaderPlugin = require('vue-loader/lib/plugin') // vue插件
const webpack = require('webpack') //访问内置的插件
const HtmlWebpackPlugin = require('html-webpack-plugin') // cnpm i html-webpack-plugin -D
const webpackMerge = require('webpack-merge')
const baseConfig = require('./base.config.js')

module.exports = webpackMerge.merge(baseConfig, {
	plugins: [
		new VueLoaderPlugin(),
		new webpack.BannerPlugin('最终版权归我所有'),
		new HtmlWebpackPlugin({
			template: 'index.html' // 指定html模板
		})
	]
})

