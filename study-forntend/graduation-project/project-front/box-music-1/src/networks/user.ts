import axios from '@/networks';
import { BaseInterface } from '@/globals/GlobalTypes';

export const loginApi = (data: UserLoginRequest) => {
	return axios.post<UserLoginResponse>('/login', data);
};

export const logoutApi = () => {
	return axios.post('/user/logout');
};

export const getVerificationCodeApi = (codeId: string) => {
	return axios.get('/user/getVerificationCode', {
		params: {
			codeId
		}
	});
};

export interface UserLoginRequest extends BaseInterface {
	username: string;
	password: string;
	verificationCode?: string;
	codeId?: string;
}

export interface UserLoginResponse extends BaseInterface {
	type: string;
	userInfo: UserInfo;
	roles: Array<Role>;
	apis: Array<Api>;
	token: string;
}

export interface Role {
	roleId: number;
	roleName: string;
}

export interface Api {
	apiId?: number;
	parentApiId?: number;
	apiName?: string;
	apiPath?: string | null;
	apiType?: number;
	apiMethod?: string;
}

export interface UserInfo {
	userId?: number;
	username?: string;
	sex?: string | null;
	age?: number | null;
	tel?: string | null;
	email?: string | null;
	status?: number;
}

export interface ApiTree {
	roleId: number;
	roleName: string;
	childrenApi: ChildrenApi;
}

export interface ChildrenApi extends Api {
	childrenApi: ChildrenApi;
}

export interface UserLogoutResponse extends BaseInterface {
	type: string;
	msg: string;
}

export interface PureUser extends BaseInterface {
	userId: bigint;
	username: string;
}
