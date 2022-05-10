// 1.安装mysql
// npm i mysql

// 2.导入mysql
const mysql = require('mysql')
let options = {
	host: 'localhost',
	port: '3306', // 不写默认3306
	user: 'root',
	password: '111111',
	database: 'mall'
}
// 3.创建与数据库的连接对象
let con = mysql.createConnection(options)
// 4.创建连接
con.connect(err => {
	if (err) {
		console.log(err);
	} else {
		console.log('数据库连接成功');
	}
})
// 5.执行数据库语句
// 执行查询语句
let sql = ''
// sql = 'select * from user'
// err:错误 res:结果 fields:字段
// con.query(sql, (err, res, fields) => {
// 	console.log(err);
// 	console.log(res);
// 	console.log(fields);
// })

// 删除表
// sql = 'drop table user'
// con.query(sql, (err, data) => {
// 	console.log(err);
// 	console.log(data);
// })


// 删库
// sql = 'drop database myshop'
// con.query(sql, (err, res) => {
// 	console.log(err);
// 	console.log(res);
// })

// 创建库
// sql = 'create database mall'
// con.query(sql, (err, res) => {
// 	console.log(err);
// 	console.log(res);
// })

// 创建表
// sql = `
// 	create table user (
// 		id int not null auto_increment,
// 		username varchar(255) null,
// 		password varchar(255) null,
// 		mail varchar(255) null,
// 		primary key(id)
// 	);
// `
// con.query(sql, (err, res) => {
// 	console.log(err);
// 	console.log(res);
// })

// 插入数据
// sql = 'insert into user values(null, "野猪乔治", "111111", null)'
// con.query(sql, (err, res) => {
// 	console.log(err);
// 	console.log(res);
// })

// 使用占位符
sql = 'insert into user values(null, ?, ?, ?)'
con.query(sql, ['假猪佩奇', '111111', '123@qq.com'], (err, res) => {
	console.log(err);
	console.log(res);
})