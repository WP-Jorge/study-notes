const mysql = require('mysql')
let options = {
	host: 'localhost',
	port: '3306',
	user: 'root',
	password: '111111',
	database: 'book'
}
let con = mysql.createConnection(options)
con.connect(err => {
	if (err) {
		console.log(err);
	} else {
		console.log('数据库连接成功');
	}
})

function sqlQuery(sql, arr) {
	return new Promise((resolve, reject) => {
		con.query(sql, arr, (err, res) => {
			if (err) {
				reject(err)
			} else {
				resolve(res)
			}
		})
	})
}
module.exports = sqlQuery
