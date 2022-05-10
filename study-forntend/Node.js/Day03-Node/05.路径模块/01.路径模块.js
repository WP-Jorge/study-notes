let path = require('path')

let strPath = 'http://www.xinhuanet.com/politics/xxjxs/index.htm'

// 获取后缀名
let extend = path.extname(strPath)
console.log(extend);

// 把一个路径或路径片段的序列解析为一个绝对路径
let arr = ['/aaa', 'bbb', 'ccc']
let abPath = path.resolve(...arr)
console.log(abPath);

// path. join([... paths])方法使用平台特定的分隔符把全部给定的path片段连接到一起,并规范化生成的路径
// 获取当前执行目录的完整路径
console.log(__dirname);
let abPath2 = path.join(...[__dirname], 'aaa', 'bbb', 'ccc')
console.log(abPath2);

// 解析路径，将路径信息直接解析出来,解析出根路径，目录，扩展名、文件名称
console.log(path.parse(__filename));
