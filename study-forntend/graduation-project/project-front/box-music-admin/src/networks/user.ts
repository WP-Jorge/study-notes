import axios from '@/networks';

import { BaseInterface } from '@/globals/globalTypes';

export interface User extends BaseInterface {
	userId: bigint;
	username: string;
	sex: string;
	age: number;
	tel: string;
	email: string;
	userPic: string;
	status: number;
}

export interface PureUser extends BaseInterface {
	userId: bigint;
	username: string;
}

// const baseUrl = '/user/';

export const getAllUsersPageApi = (currentPage?: number, pageSize?: number) => {
	return axios.get('/admin/getAllUsersPage', {
		params: {
			currentPage,
			pageSize
		}
	});
};

export const getUsersByUsernamePageApi = (
	currentPage?: number,
	pageSize?: number,
	username?: string
) => {
	return axios.get('/admin/getUsersByUsernamePage', {
		params: {
			currentPage,
			pageSize,
			username
		}
	});
};

export const deleteUsersByUserIdsApi = (ids: Array<bigint>) => {
	return axios.delete('/admin/deleteUsersByUserIds', {
		data: ids
	});
};

export const updateUserStatusApi = (userId: bigint, status: number) => {
	return axios.patch(`/admin/updateUserStatus/${userId}/${status}`);
};

export const resetPasswordByUserIdApi = (userId: bigint) => {
	return axios.patch(`/admin/resetPasswordByUserId/${userId}`);
};

export const addUserApi = (formData: FormData) => {
	return axios.post('/admin/addUser', formData);
};

export const updateUserApi = (formData: FormData) => {
	return axios.put('/admin/updateUser', formData);
};
