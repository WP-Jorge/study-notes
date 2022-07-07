import { Sequelize } from 'sequelize';
import path from 'path';

import {
	MYSQL_USERNAME,
	MYSQL_PASSWORD,
	MYSQL_HOST,
	MYSQL_DATABASE_NAME,
	DATABASE_NAME
} from '../config/config.default';
import { writeFile } from '../utils/fileUtil';

const writeLog = (...args: any) => {
	writeFile(path.join(__dirname, 'log'), 'log.txt', args[0] + '\r\n', {
		flag: 'a+'
	});
};

export const seq = new Sequelize(
	MYSQL_DATABASE_NAME as string,
	MYSQL_USERNAME as string,
	MYSQL_PASSWORD,
	{
		host: MYSQL_HOST,
		dialect: DATABASE_NAME as any,
		logging: writeLog
	}
);

// seq
// .authenticate()
// .then(() => {
// 	console.log('数据库连接成功');
// })
// .catch(err => {
// 	console.log('数据库连接失败', err);
// })
