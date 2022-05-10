// 使用commonjs的模块化规范
const {add, mul} = require('./js/mathUtil.js')

console.log(add(20, 30))
console.log(mul(20, 30))

// 使用ES6的模块化规范
import {name, age, height} from "./js/info.js"
console.log(name)
console.log(age)
console.log(height)

// 依赖css文件
require('./css/normal.css')

// 依赖less文件
require('./css/special.less')
document.writeln('<h2>啦啦啦</h2>')

// 1.cnpm webpack webpack-cli -d 安装webpack相关包到本地依赖
// 2.npm init -y初始化package.json文件 如果有中文名目录，使用npm init 然后输入没有大写的英文名

// webpack4.0以上要在package里添加下面两条才能使用 webpack ./src/main.js -o ./dist/bundle.js
// "dev": "webpack --mode development",
// "bulid": "webapck --mode production"