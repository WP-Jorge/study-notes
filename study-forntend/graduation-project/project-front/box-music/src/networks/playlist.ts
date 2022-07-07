import axios from '@/networks';

import { BaseInterface } from '@/globals/globalTypes';
import { PureUser } from './user';
import { Category } from './category';

export interface Playlist extends BaseInterface {
	playlistId: string;
	playlistName: string;
	playlistDescription: string;
	totalViews: number;
	user: PureUser;
	opened: number;
	playlistPic: string;
	categories: Category[];
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
