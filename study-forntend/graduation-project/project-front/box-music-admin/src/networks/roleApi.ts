import axios from '@/networks';

import { BaseInterface } from '@/globals/globalTypes';
import { Key } from 'antd/es/table/interface';

export interface RoleApi extends BaseInterface {
	roleApiId?: bigint | Key;
	roleId: bigint | Key;
	apiId: bigint | Key;
}

const baseUrl = '/roleApi/';

export const updateRoleApisByRoleIdApi = (map: {
	roleId: Key | bigint | string;
	roleApis: RoleApi[];
}) => {
	return axios.post(baseUrl + 'updateRoleApisByRoleId', map);
};

export const getApisByRoleIdApi = (roleId: bigint | string) => {
	return axios.get(baseUrl + 'getApisByRoleId', {
		params: {
			roleId
		}
	});
};
