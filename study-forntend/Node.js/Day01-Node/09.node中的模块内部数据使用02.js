let foo = '02'

// exports 只能单个赋值
// 将foo挂载到exports对象中
exports.foo = '02中的数据'

// module.ecports可以整个赋值
// module.exports 系统默认设置了exports = module.exports,但是module的权级更高，以module为准
module.exports.c = 'c'
console.log(module);

// 向exports对象中添加add函数
exports.add = (x, y) => {
	return x + y
}