const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
// const { CleanWebpackPlugin } = require('clean-webpack-plugin');
const { DefinePlugin } = require('webpack');
const CopyWebpackPligin = require('copy-webpack-plugin');
const VueLoaderPlugin = require('vue-loader/lib/plugin');

module.exports = {
	mode: 'development',
	/**
	 * 详细参考：https://webpack.docschina.org/configuration/devtool/#root
	 * devtool: 对于报错时的 错误所在源文件索引
	 * 	eval: 每个模块都使用 eval() 执行，不能正确的显示行数
	 * 	eval-source-map: 每个模块使用 eval() 执行，不生成 .map 文件，直接通过 base64 转码映射信息到 eval 后边，
	 * 		它会生成用于开发环境的最佳品质的 source map，完整的索引
	 *	source-map: 生成 .map 文件，完整的索引
	 *  inline-source-map: 不生成 .map 文件，直接将映射信息通过 base64 转码后拼接到 main.js 最后一行，完整的索引
	 *  cheap-source-map: 生成 .map 文件，有行无列
	 * 	cheap-module-source-map: 生成 .map 文件，同时 loader 的 sourcemap 也被简化为只包含对应行的，有行无列 （react 默认）
	 */
	devtool: 'cheap-module-source-map',
	entry: './src/index.js',
	output: {
		filename: 'js/main.js',
		path: path.resolve(__dirname, './dist'),
		clean: true
		// assertModuleFilename: 'img/[name].[hash:4][ext]'
	},
	/**
	 * extensions: 对于导入的文件可以省略后缀，会在这里查找，找不到才会报错
	 * alias: 配置路径别名
	 */
	resolve: {
		extensions: ['.js', '.css', '.scss', '.vue', '.json', '.ts', '.jsx'],
		alias: {
			'@': path.resolve(__dirname, 'src'),
			'js': '@/js',
			'css': '@/css',
			'font': '@/font',
			'img': '@/img'
		}
	},
	/**
	 * 告知 webpack 为目标(target)指定一个环境。默认值为 "browserslist"，如果没有找到 browserslist 的配置，则默认为 "web"
	 */
	target: 'web',
	devServer: {
		/**
		 * 打开服务网址 端口
		 */
		// host: '127.0.0.1',
		port: 8080,
		/**
		 * 启用热模块替换功能，在构建失败时不刷新页面作为回退
		 */
		hot: 'only',
		/**
		 * 自动打开浏览器
		 */
		open: false,
		/**
		 * 利用 gzips 压缩 public/ 目录当中的所有内容并提供一个本地服务
		 */
		static: {
			directory: path.join(__dirname, 'public')
		},
		compress: true,
		/**
		 * 当出现编译错误或警告时，在浏览器中显示全屏覆盖
		 */
		client: {
			overlay: {
				errors: false,
				warnings: false
			},
			/**
			 * 在浏览器中以百分比显示编译进度
			 */
			progress: false,
			/**
			 * 告诉 dev-server 它应该尝试重新连接客户端的次数, 当为 true 时，它将无限次尝试重新连接
			 */
			reconnect: true
		},
		/**
		 * proxy 代理(解决跨域)
		 * 目标地址 https://apimusic.postgraduate.top/toplist/detail
		 * 项目中访问 http://localhost:8080/api/toplist/detail
		 * 代理通过匹配 /api ，将 /api 前面的地址转换成 target，并替换 '/api' => ''
		 * 最后转换成 https://apimusic.postgraduate.top/toplist/detail
		 */
		proxy: {
			'/api': {
				target: 'https://apimusic.postgraduate.top',
				pathRewrite: {'^/api': ''},
				changeOrigin: true
			}
		}
	},
	module: {
		rules: [
			{
				/**
				 * npm i style-loader css-loader sass-loader sass -D
				 * 打包css、scss后缀结尾的文件
				 * npm i postcss postcss-loader -D
				 * 根据browserslist转换css成兼容样式，一般跟autoprefixer一起使用
				 */
				test: /\.(css|scss)$/,
				use: [
					'style-loader',
					{
						loader: 'css-loader',
						options: {
							/**
							 * 如果遇到 @import() 引用其他样式，则从前面两个loader开始再次执行
							 */
							importLoaders: 2,
							esModule: false
						}
					},
					'postcss-loader',
					'sass-loader'
				]
			},
			{
				/**
				 * [ext]:扩展名
				 * [name]:文件名
				 * [hash]:文件内容
				 * [contentHash]:[hash:<length>]
				 * [path]
				 */
				test: /\.(jp?g|png|gif|svg)$/,
				/**
				 * 使用type后就不用再安装 url-loader 和 file-loader 了
				 * 1 asset/inline: url-loader base64 uri文件当中，减少请求次数
				 * 2 asset/resource: file-loader将资源拷贝至指定的目录，分开请求
				 * 3 asset: url-loader内部其实也可以调用file-loader
				 * 4 limit
				 */
				// 1.asset/resource
				// type: 'asset/resource',
				// generator: {
				// 	filename: 'img/[name].[hash:4][ext]'
				// }

				// 2.asset/inline
				// type: 'asset/inline'

				// 3.asset
				type: 'asset',
				generator: {
					filename: 'img/[name].[hash:4][ext]'
				},
				parser: {
					dataUrlCondition: {
						maxSize: 50 * 1024
					}
				}

				// use: [
				// 	{
				// 		/**
				// 		 * npm i file-loader -D
				// 		 * 用来解决图片导入问题
				// 		 */
				// 		// loader: 'file-loader',
				// 		/**
				// 		 * 使用require 导入图片，此时如果不配置esModule: false ，则需.default 导出
				// 		 * 使用esModule: false 来关闭es模块，解决图片require('../img/风景.jpg').default 问题
				// 		 * 采用import xxx from图片资源，此时可以直接使用 xxX×
				// 		 */

				// 		/**
				// 		 * npm i url-loader -D
				// 		 * 用来解决图片导入问题，内部包含了file-loader，所以不用再安装file-loader
				// 		 */
				// 		// loader: 'url-loader',
				// 		// options: {
				// 		// 	name: 'img/[name].[hash:6].[ext]',
				// 		// 	limit: 50 * 1024,
				// 		// 	esModule: false
				// 		// }
				// 	}
				// ]
			},
			{
				test: /\.(eot|ttf|woff2?|otf)$/,
				type: 'asset/resource',
				generator: {
					filename: 'font/[name].[hash:3][ext]'
				}
			},
			{
				/**
				 * npm i baber-loader -D
				 * 安装 babel-loader
				 * npm i @babel/core @babel-preset-env -D
				 * 安装 babel 的核心和预设
				 */
				test: /\.js$/,
				exclude: /node_modules/,
				use: ['babel-loader']
			},
			{
				test: /\.vue$/,
				use: ['vue-loader']
			},
			{
				test: /\.ts$/,
				exclude: /node_modules/,
				use: ['babel-loader']
			}
		]
	},
	plugins: [
		/**
		 * npm i html-webpack-plugin -D
		 * 将打包好的 main.js 自动添加到html中
		 */
		new HtmlWebpackPlugin({
			template: './public/index.html',
			title: 'plugin 的使用'
		}),
		/**
		 * webpack5 内置清除插件，在 output 中配置 clean: true 即可
		 * npm i clean-webpack-plugin -D
		 * 每次开始打包时清除之前打包好的 dist 文件夹中的所有东西
		 */
		// new CleanWebpackPlugin(),
		/**
		 * webpack 默认自带插件，直接使用
		 */
		new DefinePlugin({
			BASE_URL: '"./"'
		}),
		/**
		 * npm i copy-webpack-plugin -D
		 * 将指定文件夹中的文件拷贝到目标文件夹中
		 */
		new CopyWebpackPligin({
			patterns: [
				{
					from: 'public',
					globOptions: {
						ignore: ['**/index.html']
					}
				}
			]
		}),
		/**
		 * npm i vue-loader -D
		 * 安装最新的 vue-loader 版本：15，用来解析 vue 模板
		 */
		new VueLoaderPlugin()
	]
};
