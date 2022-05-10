const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');

const { JWT_SECRET } = require('../config/config.default');

const userValidator = async (ctx, next) => {
	console.log('userValidator');
	await next();
};

const bcryptPassword = async (ctx, next) => {
	let { password } = ctx.request.body;
	let { authorization = '' } = ctx.request.header;
	try {
		ctx.verifyParams({
			username: {
				type: 'string',
				require: true
			},
			password: {
				type: 'string',
				require: true
			}
		})
	} catch (error) {
		console.error(error);
	}
	console.log(authorization);
	// 验证 token
	if (authorization) {
		authorization = authorization.replace('Bearer ', '');
		console.log(authorization);
		let decoded = jwt.verify(authorization, JWT_SECRET);
		console.log(decoded);
	}
	// 密码加密
	const salt = bcrypt.genSaltSync(10);
	const hash = bcrypt.hashSync(password, salt);
	ctx.request.body.password = hash;
	// 密码验证
	// console.log(bcrypt.compareSync(password, '$2a$10$RY3ynlEmuKZPtwzhRIWqBOEw9.mHCLbg35RQIojzYnhA5KDJ3ovMq'));
	// 生成token
	let token = jwt.sign({ password }, JWT_SECRET, {
		expiresIn: '1d' // 1d: 1天，1: 1秒
	});
	console.log(token);
	ctx.body = {
		token: token
	};
	await next();
};

module.exports = {
	userValidator,
	bcryptPassword
};
