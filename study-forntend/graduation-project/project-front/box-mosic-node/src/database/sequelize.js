const { Sequelize } = require('sequelize');

const { MYSQL_USERNAME, MYSQL_PASSWORD, MYSQL_HOST, MYSQL_DATABASE_NAME, DATABASE_NAME } = require('../config/config.default');

let seq = new Sequelize(MYSQL_DATABASE_NAME, MYSQL_USERNAME, MYSQL_PASSWORD, {
	host: MYSQL_HOST,
	dialect: DATABASE_NAME
});

// seq
// .authenticate()
// .then(() => {
// 	console.log('数据库连接成功');
// })
// .catch(err => {
// 	console.log('数据库连接失败', err);
// })

module.exports = seq;