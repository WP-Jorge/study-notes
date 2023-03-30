import axios from '@/networks';

import { BaseInterface } from '@/globals/GlobalTypes';
import { Category } from './category';
import { Singer } from './singer';
import { Album } from './album';
import { DownloadItemInfo } from '@/store/download';

export interface Music extends BaseInterface {
	musicId?: string;
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

export interface DesMusic extends BaseInterface {
	musicId: string;
	musicTitle: string;
	musicUrl: string;
	lyric: string;
	albumId: string;
	albumName: string;
	albumPic: string;
	albumDescription?: string;
	duration: number;
	size: number;
	level: string;
	musicFormat: string;
	bitrate: number;
	categoryId?: string;
	categoryName?: string;
	categoryType?: string;
	singerId: string;
	singerName: string;
	singerPic: string;
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

export const getMusicsByTotalViewsSortPageApi = () => {
	return axios.get(baseUrl + 'getMusicsByTotalViewsSortPage');
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

export const getMusicsByAlbumIdPageApi = (
	currentPage?: number,
	pageSize?: number,
	albumId?: string
) => {
	return axios.get(baseUrl + 'getMusicsByAlbumIdPage', {
		params: {
			currentPage,
			pageSize,
			albumId
		}
	});
};

export const getMusicsByKeywordApi = (keyword: string) => {
	return axios.get(baseUrl + 'getMusicsByKeyword', {
		params: {
			keyword
		}
	});
};
