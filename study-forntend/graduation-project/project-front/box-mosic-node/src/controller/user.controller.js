const userService = require('../service/user.service');
const ResultType = require('../constant/resultType.js');

class UserController {
	async login(ctx, next) {
		let { phone, password } = ctx.request.body;
		let res = await userService.login(phone, password);
		if (res) {
			ctx.body = ResultType.success('登录成功', res);
			return await next();
		}
		ctx.body = ResultType.error('登陆失败');
		await next();
	}

	async loginStatus(ctx, next) {
		let { cookie } = ctx.request.body;
		cookie = encodeURIComponent(cookie);
		let res = await userService.loginStatus(cookie);
		if (res) {
			ctx.body = ResultType.success('获取登录状态成功', res);
			return await next();
		}
		ctx.body = ResultType.error('获取登录信息失败');
		await next();
	}

	async refreshLogin(ctx, next) {
		let { cookie } = ctx.request.body;
		cookie = encodeURIComponent(cookie);
		let res = await userService.refreshLogin(cookie);
		if (res) {
			ctx.body = ResultType.success('刷新登录状态成功', res);
			return await next();
		}
		ctx.body = ResultType.error('刷新登录状态失败');
		await next();
	}
}

module.exports = new UserController();