import axios from '@/networks';

import { BaseInterface } from '@/globals/globalTypes';
import { PureUser } from './user';
import { Category } from './category';
import { Music } from './music';

export interface Playlist extends BaseInterface {
	playlistId?: string;
	playlistName: string;
	playlistDescription: string;
	totalViews: number;
	user: PureUser;
	opened: number;
	playlistPic: string;
	categories: Category[];
	musics?: Music[];
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

export const getPlaylistsByTotalViewsSortPageApi = (
	currentPage?: number,
	pageSize?: number
) => {
	return axios.get(baseUrl + 'getPlaylistsByTotalViewsSortPage', {
		params: {
			currentPage,
			pageSize
		}
	});
};

export const getPlaylistsByCategoryIdPageApi = (
	currentPage?: number,
	pageSize?: number,
	categoryId?: string
) => {
	return axios.get(baseUrl + 'getPlaylistsByCategoryIdPage', {
		params: {
			currentPage,
			pageSize,
			categoryId
		}
	});
};

export const getSimplePlaylistsWithMusicsApi = () => {
	return axios.get(baseUrl + 'getSimplePlaylistsWithMusics');
};

export const getPlaylistsByPlaylistNameAndUserIdPageApi = (
	currentPage?: number,
	pageSize?: number,
	playlistName?: string
) => {
	return axios.get(baseUrl + 'getPlaylistsByPlaylistNameAndUserIdPage', {
		params: {
			currentPage,
			pageSize,
			playlistName
		}
	});
};

export const addSimplePlaylistApi = (data: any) => {
	return axios.post(baseUrl + 'addSimplePlaylist', data);
};

export const deleteSimplePlaylistsByPlaylistIdsApi = (ids: string[]) => {
	return axios.delete(baseUrl + 'deleteSimplePlaylistsByPlaylistIds', {
		data: ids
	});
};

export const updateSimplePlaylistApi = (data: any) => {
	return axios.put(baseUrl + 'updateSimplePlaylist', data);
};

export const deleteUserPlaylistsApi = (ids: string[]) => {
	return axios.delete(baseUrl + 'deleteUserPlaylists', {
		data: ids
	});
};
