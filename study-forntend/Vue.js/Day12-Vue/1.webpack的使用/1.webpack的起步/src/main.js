const {add, mul} = require('mathUtil.js')

console.log(add(20, 30))
console.log(mul(20, 30))

import {name, age, height} from "info.js"
console.log(name)
console.log(age)
console.log(height)


// 1.cnpm webpack webpack-cli -D 安装webpack相关包到本地依赖 -D必须大写
// 2.npm init -y初始化package.json文件 如果有中文名目录，使用npm init 然后输入没有大写的英文名

// 3.webpack4.0以上要在package里添加下面两条才能使用 webpack ./src/main.js -o ./dist/bundle.js
// "dev": "webpack --mode development",
// "bulid": "webapck --mode production" --mode production压缩代码