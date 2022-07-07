import axios from '@/networks';

import { BaseInterface } from '@/globals/globalTypes';
export interface Singer extends BaseInterface {
	singerId?: string;
	singerName: string;
	totalViews: number;
	singerPic?: string;
}

const baseUrl = '/singer/';

export const getSingersBySingerNamePageApi = (
	currentPage?: number,
	pageSize?: number,
	singerName?: string
) => {
	return axios.get(baseUrl + 'getSingersBySingerNamePage', {
		params: {
			currentPage,
			pageSize,
			singerName
		}
	});
};

export const deleteSingersBySingerIdsApi = (ids: Array<string>) => {
	return axios.delete(baseUrl + 'deleteSingersBySingerIds', {
		data: ids
	});
};

export const addSingerApi = (data: FormData) => {
	return axios.post(baseUrl + 'addSinger', data);
};

export const updateSingerApi = (data: FormData) => {
	return axios.put(baseUrl + 'updateSinger', data);
};
