// require 方法有两个作用
// 		1、加载文件模块并执行里面的代码
// 		2、拿出被加载文件模块导出的接口对象
// 		在每个模块中都提供了一个对象：exports
// 		exports 默认是一个空对象，可以把所有需要被访问的成员挂载到这个exports对象中

// require初始化只会初始化一次，多次导入只会在原有的基础上进行修改，不会执行比如02里面的console.log
let ret = require('./09.node中的模块内部数据使用02')

// 使用02中的foo数据
console.log(ret.foo)
console.log(ret.add(2, 3));
console.log(ret.c);