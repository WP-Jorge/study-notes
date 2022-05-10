import { createBrowserHistory } from 'history';
import NProgress from 'nprogress';
import axios, {
	AxiosRequestConfig,
	AxiosResponse,
	Method,
	Canceler
} from 'axios';
import { message } from 'antd';
import { Store } from '@/redux/store';
import { options } from '@/configs/axios';
import { errorCodes } from '@/globals/errorCodes';

const history = createBrowserHistory();
let progressCount = 0;
const addProgress = () => {
	NProgress.start();
	progressCount++;
};
const cancelProgress = () => {
	if (--progressCount <= 0) {
		NProgress.done();
	}
};

/**
 * 请求失败后错误统一处理
 * @param status 状态码
 * @param msg 信息
 */
const errorHandler = (status: number, msg: string) => {
	// 状态码判断
	switch (status) {
		case errorCodes.CODE_0:
			message.error(msg);
			break;
		case errorCodes.CODE_400:
			message.error('请求语法错误，服务端不支持，' + msg);
			break;
		case errorCodes.CODE_401:
			message.error('身份信息认证失败，' + msg);
			history.replace('/login');
			break;
		case errorCodes.CODE_403:
			message.error('身份信息认证失败，' + msg);
			history.replace('/login');
			break;
		case errorCodes.CODE_404:
			message.error('网络请求不存在，' + msg);
			break;
		case errorCodes.CODE_500:
			message.error('服务器异常，' + msg);
			break;
		case errorCodes.CODE_502:
			message.error('网关错误，' + msg);
			break;
		case errorCodes.CODE_503:
			message.error('当前服务不可用，' + msg);
			break;
		default:
			message.error('未知错误，' + msg);
	}
};

/**
 * 定义等待请求接口
 */
interface PendingRequest {
	url?: string;
	method?: Method;
	params: any;
	data: any;
	cancel: Canceler;
}

/**
 * 定义请求数组
 */
const pendingRequests: Array<PendingRequest> = [];

/**
 * 移除重复请求
 * @param config axios 配置类
 */
const removeDuplicateRequests = (config: AxiosRequestConfig) => {
	pendingRequests.forEach((item: PendingRequest, index: number) => {
		if (
			item.url === config.url &&
			item.method === config.method &&
			JSON.stringify(item.params) === JSON.stringify(config.params) &&
			JSON.stringify(item.data) === JSON.stringify(config.data)
		) {
			item.cancel('操作太频繁，请稍后再试');
		}
		pendingRequests.splice(index, 1);
		// cancelProgress();
	});
};

/**
 * 实例化请求配置
 */
const instance = axios.create(options);

/**
 * 请求拦截器
 * 每次请求前，如果存在 token，则在请求头中携带 token
 */
export const interceptor = (store: Store) => {
	instance.interceptors.request.use(
		(config: AxiosRequestConfig) => {
			addProgress();
			removeDuplicateRequests(config);
			config.cancelToken = new axios.CancelToken((cancel: Canceler) => {
				const { token } = store.getState().system;
				pendingRequests.push({
					url: config.url,
					method: config.method,
					params: config.params,
					data: config.data,
					cancel
				});
				// 登录流程控制中，根据本地是否存在token判断用户的登录情况
				// 但是即使token存在，也有可能token是过期的，所以在每次的请求头中携带token
				// 后台根据携带的token判断用户的登录情况，并返回给我们对应的状态码
				// 而后我们可以在响应拦截器中，根据状态码进行一些统一的操作。
				// const token = store.state.token;
				// localStorage.setItem('token', token);

				if (token && !config.url?.includes('login')) {
					if (config.headers) {
						config.headers.Authorization = token;
					}
				} else {
					console.log('没有 token');
				}
			});
			return config;
		},
		error => {
			message.error(error.message);
			cancelProgress();
			return Promise.reject(error.message);
		}
	);

	/**
	 * 响应拦截器
	 */
	instance.interceptors.response.use(
		(response: AxiosResponse) => {
			cancelProgress();
			removeDuplicateRequests(response);
			response.headers['access-control-allow-headers'] = '*';
			// 请求成功
			if (response.status === 200 || response.status < 300) {
				return Promise.resolve(response);
			}
			return Promise.reject(response);
		},
		error => {
			if (error.response) {
				errorHandler(error.response.status, error.response.data.message);
			} else {
				errorHandler(errorCodes.CODE_0, error.message);
			}
			cancelProgress();
			return Promise.reject(error);
		}
	);
};

export default instance;
