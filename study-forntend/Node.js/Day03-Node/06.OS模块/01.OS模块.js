let os = require('os')

console.log(os);

// 查看cpu信息
console.log(os.cpus());
// 查看内存信息
console.log(os.totalmem());
// 查看系统架构
console.log(os.arch());
// 查看剩余内存容量
console.log(os.freemem());
// 查看系统平台
console.log(os.platform());