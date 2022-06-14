import { userService } from '../service/user.service';
import { ResultType } from '../constant/ResultType';
import { Context, Next } from 'Koa';

class UserController {
	async login(ctx: Context, next: Next) {
		let { phone, password } = (ctx.request as any).body;
		let res = await userService.login(phone, password);
		if (res) {
			ctx.body = ResultType.success('登录成功', res);
			return await next();
		}
		ctx.body = ResultType.error('登陆失败');
		await next();
	}

	async loginStatus(ctx: Context, next: Next) {
		let { cookie } = (ctx.request as any).body;
		cookie = encodeURIComponent(cookie);
		let res = await userService.loginStatus(cookie);
		if (res) {
			ctx.body = ResultType.success('获取登录状态成功', res);
			return await next();
		}
		ctx.body = ResultType.error('获取登录信息失败');
		await next();
	}

	async refreshLogin(ctx: Context, next: Next) {
		let { cookie } = (ctx.request as any).body;
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

export const userController = new UserController();
