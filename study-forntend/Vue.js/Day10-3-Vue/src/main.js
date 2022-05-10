//main.js是项目的js的入口文件
//使用npm init -y 初始化package.json配置文件
//使用cnpm i jquery webpack webpack-cli webpack-dev-server html-webpack-plugin -d 将工具、插件安装至本地

//import *** from *** 是ES6中导入模块的方式
//由于ES6的代码太高级，浏览器解析不了，所以会报错
import $ from 'jquery'

//使用import导入css样式表
import './css/index.css'
//注意：webpack默认只能打包处理js文件，无法处理其他非js类型的文件
//如果要处理非js类型的文件，需要手动安装一些第三方合适的loader加载器
//1、如果要打包处理css文件，需要安装cnpm i style-loader css-loader -d
//2、打开webpack.config.js配置文件，在里面新增一个配置节点 module，他是一个对象，在module对象中有的rules属性，他是个数组，存放了
//对第三方文件的匹配、处理规则
//3、在调用loader时，是从后往前调用的，注意调用顺序
//4、当所有的loader调用完毕后会把处理的结果直接交给webpack进行打包处理，最终输出到bundle.js中去

import './css/index.less'
//cnpm i less-loader less -d 安装less样式的loader
//注意：less是less-loader中的一个内置依赖，无需在webpack.config.js配置文件中设置，只需设置less-loader

import './css/index.scss'
//cnpm i sass-loader node-sass -d 安装scss样式的loader
//注意：node-sass是sass-loader中的一个内置依赖，无需在webpack.config.js配置文件中设置，只需设置sass-loader

$(function(){
	$('li:odd').css('backgroundColor','skyblue') 
	$('li:even').css('backgroundColor',function(){
		return 'hotpink' 
	})
})

//webpack有什么用？
//1、webpack可以处理文件间的互相依赖关系 如import 包；
//2、webpack可以处理js的兼容问题，把高级的、浏览器不识别的语法转化为低级的、浏览器可以识别的语法

//webpack 的初始化：
//1、需要npm i webpack -d 下载webpack
//2、需要npm i webpack-cli -d 下载webpack-cli
//3、运行命令格式：webpack 要进行处理的地址文件 -o 输出的文件地址文件名

//当我们在控制台输入webpack时，webpack做了一下几步：
//1、首先webpack会发现我们并没有通过命令的形式给他指定出入口
//2、webpack就会去项目根目录中查找一个叫'webpack.config.js'的配置文件
//3、找到后，webpack会解析配置文件，当解析完后就得到了配置文件中的配置对像
//4、拿到配置对象中的出入口，然后进行打包构建


//使用webpack-dev-server工具来实现自动打包编译功能
//1、运行cnpm i webpack-dev-server -D把工具安装到本地依赖
//2、简化webpack-dev-server，在package.json中的scripts中添加 "dev": "webpack-dev-server" 后可键入npm run dev 开启自动编译功能
//3、注意：要想webpack-dev-server正常运行，必须先安装webpack webpack-cli
//4、注意：webpack-dev-server 所生成的bundle.js存在放根目录的缓存中，不存放在物理磁盘中

//html-webpack-plugin插件：
//1、自动在内存中根据指定页面生成一个内存的页面
//2、自动把打包好的bundle.js追加到页面中去