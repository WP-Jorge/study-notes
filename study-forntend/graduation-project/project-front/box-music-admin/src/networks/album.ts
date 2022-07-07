import axios from '@/networks';

import { BaseInterface } from '@/globals/globalTypes';
import { Key } from 'antd/es/table/interface';

export interface Album extends BaseInterface {
	albumId?: bigint | Key | string;
	albumName: string;
	totalViews: number;
	albumPic?: string;
	albumDescription?: string;
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

export const deleteAlbumsByalbumIdsApi = (ids: Array<bigint>) => {
	return axios.delete(baseUrl + 'deleteAlbumsByAlbumIds', {
		data: ids
	});
};

export const addAlbumApi = (data: FormData) => {
	return axios.post(baseUrl + 'addAlbum', data);
};

export const updateAlbumApi = (data: FormData) => {
	return axios.put(baseUrl + 'updateAlbum', data);
};
