import bcrypt from 'bcryptjs';
import jwt from 'jsonwebtoken';
import { Context, Next } from 'Koa';

import { JWT_SECRET } from '../config/config.default';
import { userService } from '../service/user.service';
import { ResultType } from '../constant/ResultType';

const userValidator = async (ctx: Context, next: Next) => {
	console.log('userValidator');
	await next();
};

const bcryptPassword = async (ctx: Context, next: Next) => {
	let { password } = (ctx.request as any).body;
	let { authorization = '' } = (ctx.request as any).header;
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
		});
	} catch (error) {
		console.error(error);
	}
	console.log(authorization);
	// 验证 token
	if (authorization) {
		authorization = authorization.replace('Bearer ', '');
		console.log(authorization);
		let decoded = jwt.verify(authorization, JWT_SECRET as string);
		console.log(decoded);
	}
	// 密码加密
	const salt = bcrypt.genSaltSync(10);
	const hash = bcrypt.hashSync(password, salt);
	(ctx.request as any).body.password = hash;
	// 密码验证
	// console.log(bcrypt.compareSync(password, '$2a$10$RY3ynlEmuKZPtwzhRIWqBOEw9.mHCLbg35RQIojzYnhA5KDJ3ovMq'));
	// 生成token
	let token = jwt.sign({ password }, JWT_SECRET as string, {
		expiresIn: '1d' // 1d: 1天，1: 1秒
	});
	console.log(token);
	ctx.body = {
		token: token
	};
	await next();
};

const userLoginValidator = async (ctx: Context, next: Next) => {
	let { cookie } = (ctx.request as any).query;
	cookie = encodeURIComponent(cookie);
	let res = await userService.loginStatus(cookie);
	if (!res) {
		return (ctx.body = ResultType.error('请登录', res));
	}
	await next();
};

export { userValidator, bcryptPassword, userLoginValidator };
