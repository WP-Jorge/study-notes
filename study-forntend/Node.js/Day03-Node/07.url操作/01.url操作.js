let url = require('url')
console.log(url);

// url地址解析
let httpUrl = 'https://sale.vmall.com/hwmate.html?cid=10602'
let urlObj = url.parse(httpUrl)
console.log(urlObj);

// url地址合成
let targetUrl = 'http://www.taobao.com/'
httpUrl = './aaa/bbb/ccc.html'
let newUrl = url.resolve(targetUrl, httpUrl)
console.log(newUrl);