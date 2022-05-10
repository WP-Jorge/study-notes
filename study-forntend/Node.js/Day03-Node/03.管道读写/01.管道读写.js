let fs = require('fs')

let ws = fs.createWriteStream('b.png')

let rs = fs.createReadStream('a.png')

// 管道，将读出来的数据写入到目标位置
rs.pipe(ws)