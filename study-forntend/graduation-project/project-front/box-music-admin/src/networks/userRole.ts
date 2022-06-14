import axios from '@/networks';

import { BaseInterface } from '@/globals/globalTypes';
import { Key } from 'antd/es/table/interface';
import { Role } from './system';

export interface UserRole extends BaseInterface {
	userId: string | bigint | Key;
	userName: string;
	roleList: Array<Role>;
}

const baseUrl = '/userRole/';

export const getUserRolesByUsernamePageApi = (
	currentPage?: number,
	pageSize?: number,
	username?: string
) => {
	return axios.get(baseUrl + 'getUserRolesByUsernamePage', {
		params: {
			currentPage,
			pageSize,
			username
		}
	});
};

export const updateUserRolesByUserIdApi = (
	userId: string | bigint | Key,
	roleIds: Array<bigint | string | Key>
) => {
	return axios.post(baseUrl + 'updateUserRolesByUserId', {
		userId,
		roleIds
	});
};
