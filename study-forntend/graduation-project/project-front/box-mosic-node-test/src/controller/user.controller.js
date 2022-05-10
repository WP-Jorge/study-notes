const { login } = require('../service/user.service');

class UserController {
	async login(ctx, next) {
		console.log(ctx.request.body);
		let { username, password } = ctx.request.body;
		let res = await login(username, password);
		// ctx.body = res;
		await next();
	}
}

module.exports = new UserController();