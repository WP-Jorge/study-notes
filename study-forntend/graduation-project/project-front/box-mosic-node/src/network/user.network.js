const axios = require('./index');

const loginApi = (phone, password) => {
	return axios.get('/login/cellphone', {
		params: {
			phone,
			password
		}
	})
};

const loginStatusApi = cookie => {
	return axios.post('/login/status', {
		cookie
	});
};

const refreshLoginApi = cookie => {
	return axios.post('/login/refresh', {
		cookie
	});
};

module.exports = {
	loginApi,
	loginStatusApi,
	refreshLoginApi
}