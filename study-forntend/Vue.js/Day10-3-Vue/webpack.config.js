const path = require('path')
//导入html-webpack-plugin 插件  只要是插件就一定要导入，放到plugins节点中去
const htmlWebpackPlugin = require('html-webpack-plugin')

//这个配置文件就是一个js文件，通过node中的操作向外暴露l一个配置对象
module.exports = {
	//手动设置配置文件中的入口、出口
	entry:path.join(__dirname,'./src/main.js'),//入口，表示要使用哪个文件进行webpack打包
	output:{//输出文件相关配置
		publicPath:'/',
		path:path.join(__dirname,'./dist'),//指定打包好的文件输出路径
		filename:'bundle.js'//输出的文件名
	},
	plugins:[//配置插件的节点
		new htmlWebpackPlugin({//配置html-webpack-plugin
			template:'./src/index.html',//确定模板地址、名
			filename:'index.html'//生成的模板名，不写默认这个
		})
	],
	module:{//这个节点用于配置所有第三方模块 加载器
		rules:[//所有第三方模块的匹配规则
			{test:/\.css$/,use:['style-loader','css-loader']},//这是配置处理。css文件的第三方loader规则
			{test:/\.less$/,use:['style-loader','css-loader','less-loader']},//这是less第三方loader规则
			{test:/\.scss$/,use:['style-loader','css-loader','sass-loader']}//这是scss第三方规则
		]
	},
	mode:'development',//webpack4.0以上需要添加这个，不然报错
	devtool: 'inline-source-map'//webpack4.0以上需要添加这个，不然浏览器报警告
}
//--open:键入 npm run dev 后自动打开浏览器 后边可添加参数指定打开浏览器
//--port：指定打开端口号
//--contentBase：设置默认打开地址的根路径
//--hot：局部刷新  只在css样式中起效 webpack4.0后自动打开
// "dev": "webpack-dev-server --open chrome --port 3000 --contentBase src --hot"