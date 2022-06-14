import axios from '@/networks';

import { BaseInterface } from '@/globals/globalTypes';

export interface Role extends BaseInterface {
	roleId: bigint;
	roleName: string;
}

const baseUrl = '/role/';

export const getAllRolesPageApi = (currentPage?: number, pageSize?: number) => {
	return axios.get(baseUrl + 'getAllRolesPage', {
		params: {
			currentPage,
			pageSize
		}
	});
};

export const getRolesByRoleNamePageApi = (
	currentPage?: number,
	pageSize?: number,
	roleName?: string
) => {
	return axios.get(baseUrl + 'getRolesByRoleNamePage', {
		params: {
			currentPage,
			pageSize,
			roleName
		}
	});
};

export const deleteRolesByRoleIdsApi = (ids: Array<bigint>) => {
	return axios.delete(baseUrl + 'deleteRolesByRoleIds', {
		data: ids
	});
};

export const addRoleApi = (data: Role) => {
	return axios.post(baseUrl + 'addRole', data);
};

export const updateRoleApi = (data: Role) => {
	return axios.put(baseUrl + 'updateRole', data);
};
