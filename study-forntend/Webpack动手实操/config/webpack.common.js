const resolveApp = require('./paths');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const { DefinePlugin } = require('webpack');
const { merge } = require('webpack-merge');
const VueLoaderPlugin = require('vue-loader/lib/plugin');

// 导入其他配置
const devConfig = require('./webpack.dev');
const prodConfig = require('./webpack.prod');

// 定义对象保存 base 信息
const commonConfig = {
	entry: './src/index.js',
	output: {
		filename: 'js/main.js',
		path: resolveApp('./dist'),
		clean: true
	},
	resolve: {
		extensions: ['.js', '.css', '.scss', '.vue', '.json', '.ts', '.jsx'],
		alias: {
			'@': resolveApp('./src'),
			'js': '@/js',
			'css': '@/css',
			'font': '@/font',
			'img': '@/img'
		}
	},
	module: {
		rules: [
			{
				test: /\.(css|scss)$/,
				use: [
					'style-loader',
					{
						loader: 'css-loader',
						options: {
							importLoaders: 2,
							esModule: false
						}
					},
					'postcss-loader',
					'sass-loader'
				]
			},
			{
				test: /\.(jp?g|png|gif|svg)$/,
				type: 'asset',
				generator: {
					filename: 'img/[name].[hash:4][ext]'
				},
				parser: {
					dataUrlCondition: {
						maxSize: 50 * 1024
					}
				}
			},
			{
				test: /\.(eot|ttf|woff2?|otf)$/,
				type: 'asset/resource',
				generator: {
					filename: 'font/[name].[hash:3][ext]'
				}
			},
			{
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
		new HtmlWebpackPlugin({
			template: resolveApp('./public/index.html'),
			title: 'plugin 的使用'
		}),
		new DefinePlugin({
			BASE_URL: '"./"'
		}),
		new VueLoaderPlugin()
	]
};

module.exports = env => {
	let isProduction = env.production;
	// 合并配置信息
	const config = isProduction ? prodConfig : devConfig;
	return merge(commonConfig, config);
}