import axios from '@/networks';

import { BaseInterface } from '@/globals/globalTypes';
import { Category } from './category';
import { Singer } from './singer';
import { Album } from './album';

export interface Music extends BaseInterface {
	musicId: bigint;
	musicTitle: string;
	musicPic: string;
	lyric?: string;
	lyricUrl?: string;
	album?: Album;
	albumId?: bigint;
	duration?: number;
	size?: number;
	level?: string;
	musicFormat?: string;
	bitrate?: number;
	totalViews?: number;
	musicUrl?: string;
	deleted?: number;
	categories: Category[];
	singers: Singer[];
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

export const deleteMusicsByMusicIdsApi = (ids: Array<bigint>) => {
	return axios.delete(baseUrl + 'deleteMusicsByMusicIds', {
		data: ids
	});
};

export const addMusicApi = (formData: FormData) => {
	return axios.post(baseUrl + 'addMusic', formData);
};

export const updateMusicApi = (formData: FormData) => {
	return axios.put(baseUrl + 'updateMusic', formData);
};
