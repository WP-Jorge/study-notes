import axios from './index';

export const loginApi = (phone: string, password: string) => {
	return axios.get('/login/cellphone', {
		params: {
			phone,
			password
		}
	});
};

export const loginStatusApi = (cookie: string) => {
	return axios.post('/login/status', {
		cookie
	});
};

export const refreshLoginApi = (cookie: string) => {
	return axios.post('/login/refresh', {
		cookie
	});
};
