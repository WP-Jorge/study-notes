import axios from '@/networks';

import { BaseInterface } from '@/globals/globalTypes';
export interface Album extends BaseInterface {
	albumId: string;
	albumName: string;
	albumDescription?: string;
	totalViews?: number;
	albumPic: string;
}

const baseUrl = '/album/';

export const getAlbumsByAlbumNamePageApi = (
	currentPage?: number,
	pageSize?: number,
	albumName?: string
) => {
	return axios.get(baseUrl + 'getAlbumsByAlbumNamePage', {
		params: {
			currentPage,
			pageSize,
			albumName
		}
	});
};

export const getAlbumsByTotalViewsSortPageApi = (
	currentPage?: number,
	pageSize?: number
) => {
	return axios.get(baseUrl + 'getAlbumsByTotalViewsSortPage', {
		params: {
			currentPage,
			pageSize
		}
	});
};
