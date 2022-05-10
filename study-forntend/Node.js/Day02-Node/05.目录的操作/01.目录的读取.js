let fs = require('fs')

// 读取文件目录
fs.readdir('../05.目录的操作', (err, data) => {
	if (err) {
		console.log('读取失败');
	} else {
		console.log(data);
	}
})