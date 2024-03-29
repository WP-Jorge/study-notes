import JSONBig from 'json-bigint';
const JSONbigString = JSONBig({ storeAsString: true });

export const options = {
	// 请求头
	headers: {},
	// 请求超时时长
	timeout: 120 * 1000,
	// base 地址
	baseURL:
		import.meta.env.MODE === 'development'
			? import.meta.env.VITE_BASE_URL
			: import.meta.env.VITE_BASE_URL,
	// 跨域时是否使用凭证
	withCredentials: false,
	transformResponse: [(data: any) => JSONbigString.parse(data)]
};
