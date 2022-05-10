// 浏览器中的js没有读写文件的功能
// node中读写文件的功能

// fs：file-system的简写，要想进行文件读写操作，必须引入fs这个可信模块，他提供了所有文件操作的api
//      例如：fs.readFile 读写文件

// 1.使用require加载fs核心模块
let fs = require('fs');

// 2.读取文件
//  参数一：读取文件路径
//  参数二：回调函数
//      error：
//          如果读取失败error就是错误对象
//          如果读取成功error就是null
//      data：
//          如果读取失败data就是undefined
//          如果读取成功data就是读取到的数据
fs.readFile('03.文本.txt',  (error, data) => {
    if (error) {
		console.log(error); // 如果error，则输出错误对象
	} else {
		console.log(data); //默认二进制数据 <Buffer e4 bd a0 e5 a5 bd 0d 0a 6e 6f 64 65 2e 6a 73>
		// 使用data.toString()将其转化为字符串
		console.log(data.toString()); // 你好node.js
	}
});

