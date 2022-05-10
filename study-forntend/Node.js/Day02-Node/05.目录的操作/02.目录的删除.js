let fs = require('fs')

// 删除目录
fs.rmdir('删除', err => {
	if (err) {
		console.log('删除失败');
	} else {
		console.log('删除成功');
	}
})