const { NETEASE_HOST, NETEASE_PORT } = require('../config/config.default');

const options = {
	// 请求头
	headers: {},
	// 请求超时时长
	timeout: 20 * 1000,
	// base 地址
	baseURL: `${NETEASE_HOST}:${NETEASE_PORT}`,
	// 跨域时是否使用凭证
	withCredentials: false
};

module.exports = {
	options
}