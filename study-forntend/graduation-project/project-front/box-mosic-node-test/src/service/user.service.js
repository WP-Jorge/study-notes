class UserService {
	async login(username, password) {
		return {
			username: '张三',
			age: 19
		};
	}
}

module.exports = new UserService();