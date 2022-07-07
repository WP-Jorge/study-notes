import axios from '@/networks';

import { BaseInterface } from '@/globals/GlobalTypes';
import { Category } from './category';
import { Singer } from './singer';
import { Album } from './album';
import { DownloadItemInfo } from '@/store/download';

export interface Music extends BaseInterface {
	musicId: string;
	musicTitle: string;
	musicUrl: string;
	lyric?: string;
	album: Album;
	duration: number;
	size?: number;
	level?: string;
	musicFormat?: string;
	bitrate?: number;
	totalViews?: number;
	deleted?: number;
	categories?: Category[];
	singers: Singer[];
	downloadItemInfo?: DownloadItemInfo;
}

const baseUrl = '/music/';

export const getMusicsByMusicTitlePageApi = (
	currentPage?: number,
	pageSize?: number,
	musicTitle?: string
) => {
	return axios.get(baseUrl + 'getMusicsByMusicTitlePage', {
		params: {
			currentPage,
			pageSize,
			musicTitle
		}
	});
};

export const getMusicsByTotalViewsSortPageApi = (
	currentPage?: number,
	pageSize?: number
) => {
	return axios.get(baseUrl + 'getMusicsByTotalViewsSortPage', {
		params: {
			currentPage,
			pageSize
		}
	});
};

export const getMusicsByCreateTimeSortPageApi = (
	currentPage?: number,
	pageSize?: number
) => {
	return axios.get(baseUrl + 'getMusicsByCreateTimeSortPage', {
		params: {
			currentPage,
			pageSize
		}
	});
};

export const getMusicsByCategoryIdPageApi = (
	currentPage?: number,
	pageSize?: number,
	categoryId?: string
) => {
	return axios.get(baseUrl + 'getMusicsByCategoryIdPage', {
		params: {
			currentPage,
			pageSize,
			categoryId
		}
	});
};

export const getMusicsByPlaylistIdPageApi = (
	currentPage?: number,
	pageSize?: number,
	playlistId?: string
) => {
	return axios.get(baseUrl + 'getMusicsByPlaylistIdPage', {
		params: {
			currentPage,
			pageSize,
			playlistId
		}
	});
};
