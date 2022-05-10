let fs = require('fs')

fs.unlink('删除.html', err => {
	if (err) {
		console.log('删除失败');
	} else {
		console.log('删除成功');
	}
})