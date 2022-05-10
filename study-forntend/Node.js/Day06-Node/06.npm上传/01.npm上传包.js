1.创建文件夹
2.npm包的初始化
npm init 
3.npm包信息的设置
{
  "name": "myrw",
  "version": "1.0.0",
  "description": "将原生的fs模块进行封装，可以快速的使用async await模式",
  "main": "myrw.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1"
  },
  "keywords": [
    "fs",
    "promise封装"
  ],
  "author": "WP-Jorge",
  "license": "ISC"
}
4.注册npm官网账号
5.npm官网邮箱验证(不然上传不了)
6.本机登录npm
npm login
7.发布npm包(不能使用镜像,不然会报403)
npm publish