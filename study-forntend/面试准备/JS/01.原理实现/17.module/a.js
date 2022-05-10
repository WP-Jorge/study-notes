let obj = {
	name: '张三'
};

function changeName() {
	obj.name = '李四'
};

module.exports = {
	obj,
	changeName
};