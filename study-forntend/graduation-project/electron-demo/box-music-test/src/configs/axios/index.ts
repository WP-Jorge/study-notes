export const options = {
	// 请求头
	headers: {},
	// 请求超时时长
	timeout: 20 * 1000,
	// base 地址
	baseURL: process.env.NODE_ENV === 'development' ? process.env.VUE_APP_BASE_URL_DEV : process.env.VUE_APP_BASE_URL_PROD,
	// 跨域时是否使用凭证
	withCredentials: false
};
