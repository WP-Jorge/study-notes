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

const baseUrl = '/user/';

export const getAllUsersPageApi = (currentPage?: number, pageSize?: number) => {
	return axios.get(baseUrl + 'getAllUsersPage', {
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
	return axios.get(baseUrl + 'getUsersByUsernamePage', {
		params: {
			currentPage,
			pageSize,
			username
		}
	});
};

export const deleteUsersByUserIdsApi = (ids: Array<bigint>) => {
	return axios.delete(baseUrl + 'deleteUsersByUserIds', {
		data: ids
	});
};

export const updateUserStatusApi = (userId: bigint, status: number) => {
	return axios.patch(`${baseUrl}updateUserStatus/${userId}/${status}`);
};

export const resetPasswordByUserIdApi = (userId: bigint) => {
	return axios.patch(`${baseUrl}resetPasswordByUserId/${userId}`);
};

export const updateUserApi = (formData: FormData) => {
	return axios.put(baseUrl + 'updateUser', formData);
};
