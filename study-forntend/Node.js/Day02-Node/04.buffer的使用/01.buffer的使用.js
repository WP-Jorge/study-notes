// 1.数组不能进行二进制数据的操作
// 2.js数组不像python、java效率高
// 3.buffer内存空间中开辟出固定大小的内存

// 示例
let str = 'hello word'
let buf = Buffer.from(str)
// 输出二进制
// console.log(buf);
// 转化为字符串
// console.log(buf.toString());

// 开辟出一个空的buffer（缓存区）
let buf1 = Buffer.alloc(10)
// 最多只能有255，超出就a % 256
buf1[0] = 257
console.log(buf1);

// 获取一个不安全的空间，里面有之前遗留下来的数据
let buf2 = Buffer.allocUnsafe(10)
console.log(buf2);