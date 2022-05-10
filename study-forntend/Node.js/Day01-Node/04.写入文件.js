let fs = require('fs');

// 参数一：文件路径
// 参数二：文件内容
// 参数三：回调函数
//		error:
// 			成功：
//				写入文件
//				error为null
// 			失败：
// 				写入失败
// 				error为错误对象
// 文件如果没有则创建，有则写入
fs.writeFile('04.文本.txt', '你好从上次，node.js', error => {
	if (error) {
		// 当写入文件名不合法时error
		console.log(error);
	} else {
		console.log('写入成功！');
	}
});