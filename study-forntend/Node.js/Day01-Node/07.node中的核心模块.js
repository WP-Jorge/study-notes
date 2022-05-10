// Node为JavaScript 提供了很多服务器级别的API,这些API绝大多数都被包装到了一-个具名的核心模块中了。
// 例如文件操作的| fs核心模块，http 服务构建的http 模块，path 路径操作模块、os 操作系统信息模块。。。。

// 以后只要我说这个模块是一个核心模块，你就要马上想到如果想要使用它，就必须:

// 文件读写信息
var fs = require('fs')

// 操作系统信息
let os = require('os')

// 用来操作路径
let path = require('path')