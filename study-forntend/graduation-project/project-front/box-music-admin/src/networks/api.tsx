import axios from '@/networks';

import { BaseInterface } from '@/globals/globalTypes';
import { Key } from 'antd/es/table/interface';

export interface Api extends BaseInterface {
	apiId: bigint | Key;
	apiName: string;
}

const baseUrl = '/api/';

export const getApiTreeApi = () => {
	return axios.get(baseUrl + 'getApiTree');
};

export const updateApisApi = (data: Api[]) => {
	return axios.post(baseUrl + 'updateApis', data);
};

export const deleteApisByApiIdsApi = (apiIds: bigint[]) => {
	return axios.delete(baseUrl + 'deleteApisByApiIds', {
		data: apiIds
	});
};

export const updateApiApi = (data: Api) => {
	return axios.post(baseUrl + 'updateApi', data);
};
