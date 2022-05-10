const { 
	loginApi, 
	loginStatusApi,
	refreshLoginApi
} = require('../network/user.network');

class UserService {
	async login(phone, password) {
		let res = await loginApi(phone, password);
		if (res && res.data.code === 200) {
			return res.data;
		}
		return null;
	}

	async loginStatus(cookie) {
		let res = await loginStatusApi(cookie);
		if (res && res.data.data.account && res.data.data.code === 200) {
			return res.data.data;
		}
		return null;
	}

	async refreshLogin(cookie) {
		try {
			let res = await refreshLoginApi(cookie);
			if (res && res.data.code === 200) {
				return res.data;
			}
			return null;
		} catch(err) {
			console.log(err);
			if (err.response.data.code === 301) {
				return err.response.data;
			}
			return null;
		}
	}
}

module.exports = new UserService();
