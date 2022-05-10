// 1、使用commonjs的模块化规范
const {add, mul} = require('./js/mathUtil.js')

console.log(add(20, 30))
console.log(mul(20, 30))

// 2、使用ES6的模块化规范
import {name, age, height} from "./js/info.js"
console.log(name)
console.log(age)
console.log(height)

// 3、依赖css文件
require('./css/normal.css')

// 4、依赖less文件
require('./css/special.less')
// document.writeln('<h2>啦啦啦</h2>')

// cnpm i --save vue 可用-S 安装vue
// 5、使用vue进行开发
// 如果不在webpack.config.js中配置对应Vue路径，则会默认调用vue.runtime.js，这个时阉割的vue，template组件用不了
import Vue from 'vue'

// 一、未抽离
// new Vue({
// 	el: '#app',
// 	// 这里这样写会把template里面的的代码替换html的<div id="app"></div> 
// 	// 也就是template会替换el:#app
// 	template:`
// 	<div>
// 		<h2>{{ message }}</h2>
// 		<button @click="btnClick">按钮</button>
// 		<h2>{{ name }}</h2>
// 	</div>
// 	`,
// 	data: {
// 		message: 'hello webpack',
// 		name: '大炮'
// 	},
// 	methods: {
// 		btnClick() {
			
// 		}
// 	}
// })

// 二、抽离出去
// const App = {
// 	template:`
// 	<div>
// 		<h2>{{ message }}</h2>
// 		<button @click="btnClick">按钮</button>
// 		<h2>{{ name }}</h2>
// 	</div>
// 	`,
// 	 data() {
// 	 	return {
// 			message: 'hello webpack',
// 			name: '大炮'
// 		}
// 	 },
// 	 methods: {
// 	 	btnClick() {
	 		
// 	 	}
// 	 }
// }
// new Vue({
// 	el: '#app',
// 	// template会替换el: '#app'
// 	template:'<App/>',
// 	components: {
// 		App
// 	}
// })

// 三、全部抽离到vue/app.js中
// import App from './vue/app.js'
// new Vue({
// 	el: '#app',
// 	// template会替换el: '#app'
// 	template:'<App></App>',
// 	components: {
// 		App
// 	}
// })

// 四、抽离至vue文件中
import App from './vue/App.vue'
new Vue({
	el: '#app',
	// template会替换el: '#app'
	template:'<App></App>',
	components: {
		App
	}
})
document.writeln('<button>啦啦啦啦啦啦啦啦啦啦啦啦啦</button>')
// 1.cnpm webpack webpack-cli -d 安装webpack相关包到本地依赖
// 2.npm init -y初始化package.json文件 如果有中文名目录，使用npm init 然后输入没有大写的英文名

// webpack4.0以上要在package里添加下面两条才能使用 webpack ./src/main.js -o ./dist/bundle.js
// "dev": "webpack --mode development",
// "bulid": "webapck --mode production"