import axios from '@/networks';

import { BaseInterface } from '@/globals/globalTypes';
import { Key } from 'antd/es/table/interface';
import { PureUser } from './user';

export interface Playlist extends BaseInterface {
	playlistId?: bigint | Key | string;
	playlistName: string;
	totalViews: number;
	user: PureUser;
	opened: number;
	playlistPic?: string;
}

const baseUrl = '/playlist/';

export const getPlaylistsByPlaylistNamePageApi = (
	currentPage?: number,
	pageSize?: number,
	playlistName?: string
) => {
	return axios.get(baseUrl + 'getPlaylistsByPlaylistNamePage', {
		params: {
			currentPage,
			pageSize,
			playlistName
		}
	});
};

export const deletePlaylistsByPlaylistIdsApi = (ids: Array<bigint>) => {
	return axios.delete(baseUrl + 'deletePlaylistsByPlaylistIds', {
		data: ids
	});
};

export const addPlaylistApi = (data: FormData) => {
	return axios.post(baseUrl + 'addPlaylist', data);
};

export const updatePlaylistApi = (data: FormData) => {
	return axios.put(baseUrl + 'updatePlaylist', data);
};
