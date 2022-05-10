const a = require('./a.js');

console.log(a.obj.name); // 张三
a.changeName();
console.log(a.obj.name); // 李四 （引用类型中的值发生改变，说明是浅拷贝）