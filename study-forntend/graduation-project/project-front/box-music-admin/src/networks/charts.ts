import axios from '@/networks';

import { BaseInterface } from '@/globals/globalTypes';

export interface UserCount extends BaseInterface {
	day: Date;
	num: number;
}

const baseUrl = '/charts';

export const getRecentlyUserCountsApi = () => {
	return axios.get(`${baseUrl}/getRecentlyUserCounts`);
};

export const getMusicLevelCountsApi = () => {
	return axios.get(`${baseUrl}/getMusicLevelCounts`);
};

export const getCategoryMusicCountsApi = () => {
	return axios.get(`${baseUrl}/getCategoryMusicCounts`);
};
