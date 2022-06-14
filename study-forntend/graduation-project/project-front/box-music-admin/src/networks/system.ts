import axios from '@/networks';

import { BaseInterface } from '@/globals/globalTypes';

export const registerApi = (formData: FormData) => {
	return axios.post('/user/adminRegister', formData);
};

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

export const updateUserApi = (formData: FormData) => {
	return axios.put('/user/updateUser', formData);
};

export const getUserInfoApi = () => {
	return axios.get('/user/getUserInfo');
};

export const updatePasswordApi = (password: string) => {
	return axios.put('/user/updatePassword', {
		password
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
	children?: Array<Api>;
	[x: string]: any;
}

export interface UserInfo {
	userId?: number;
	username?: string;
	sex?: string | null;
	age?: number | null;
	tel?: string | null;
	email?: string | null;
	userPic?: string | null;
	status?: number;
}

export interface UserLogoutResponse extends BaseInterface {
	type: string;
	msg: string;
}

export interface VerifyCodeImgResponse extends BaseInterface {
	msg: string;
}
