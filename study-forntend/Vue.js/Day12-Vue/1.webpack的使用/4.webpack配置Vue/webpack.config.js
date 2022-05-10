// 4.要想直接敲webpack打包修改后的文件，需要以下配置

const path = require('path')
const VueLoaderPlugin = require('vue-loader/lib/plugin')

module.exports = {
	entry: './src/main.js',
	output: {
		// 需要动态获取地址路劲
		// 需要导入const path = require('path')模块
		path: path.join(__dirname, './dist'), // path.join(__dirname, './dist')路径拼接 join可以改成resolve
		filename: 'bundle.js',
		// 为所有url设置根路径
		publicPath: './dist/'
	},
	plugins: [
		new VueLoaderPlugin()
	],
	module: {
		rules: [{
				// test正则表达式，来匹配以.css结尾的引入
				test: /\.css$/,
				use: [
					// 使用多个loader时，从后往前读取，所以style-loader放在css-loader前面
					{
						// cnpm install style-loader --save-dev
						// 将css文件加载后的样式解析到页面
						loader: "style-loader"
					},
					{
						// cnpm install --save-dev css-loader
						// css-loder只负责将css文件进行加载，不进行解析
						loader: "css-loader"
					}
				]
			},
			{
				test: /\.less$/,
				use: [{
					loader: "style-loader" // creates style nodes from JS strings
				}, {
					loader: "css-loader" // translates CSS into CommonJS
				}, {
					// cnpm install -D less-loader@4.1.0 less
					// less-loader用来加载less文件 注意：less-loder要安装4.1.0版本
					// less负责转换成css 下面不用写less
					loader: "less-loader" // compiles Less to CSS
				}, ]
			},
			{
				rules: [{
					test: /\.(png|jpg|gif|jpeg)$/,
					use: [{
						// url-loader 版本需要1.1.2
						// cnpm i -D url-loder@1.1.2
						loader: 'url-loader',
						options: {
							// 当加载的图片小于limit，会将图片编译成base64字符串形式
							// 当图片大于limit时会使用file-loader进行加载，需要安装
							// cnpm i -D file-loader 进行安装file-loader ，报错的话安装3.0.1版本
							limit: 30000,
							// 给用file-loader处理的图片命名
							name: 'img/[name].[hash:8].[ext]'
						},
					}]
				}]
			},
			{
				// 以防有人浏览器不支持es6，配置这个将es6的代码转化为es5的代码
				// cnpm install --save-dev babel-loader@7 babel-core babel-preset-es2015
				rules: [{
					test: /\.js$/,
					exclude: /(node_modules|bower_components)/,
					use: {
						loader: 'babel-loader',
						options: {
							presets: ['es2015']
						}
					}
				}]
			},
			{
				// 安装 vue-loader vue-template-compiler
				// cnpm i vue-template-compiler vue-loader -D
				// 15版本以上需要额外加上，const VueLoaderPlugin = require('vue-loader/lib/plugin')引用 这个插件
				// 还需要加上plugins: [new VueLoaderPlugin()]
				test: /\.vue$/,
				use: [{
					loader: 'vue-loader'
				}]
			}
		]
	},
	resolve: {
		// alias 别名
		// 指定路径
		alias: {
			// 当使用 import Vue from 'vue'时，如果有下面的配置，就去下面的路径导入对应的vue
			'vue$': 'vue/dist/vue.esm.js'
		},
		// 配置后可省略后缀
		extensions: ['.js', '.css', '.vue']
	}
}
