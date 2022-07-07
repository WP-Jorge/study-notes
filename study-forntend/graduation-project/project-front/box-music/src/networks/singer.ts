import axios from '@/networks';

import { BaseInterface } from '@/globals/globalTypes';
export interface Singer extends BaseInterface {
	singerId?: string;
	singerName: string;
	totalViews: number;
	singerPic: string;
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

export const getSingersByTotalViewsSortPageApi = (
	currentPage?: number,
	pageSize?: number
) => {
	return axios.get(baseUrl + 'getSingersByTotalViewsSortPage', {
		params: {
			currentPage,
			pageSize
		}
	});
};
