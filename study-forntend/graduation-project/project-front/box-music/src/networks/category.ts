import axios from '@/networks';

import { BaseInterface } from '@/globals/globalTypes';

export interface Category extends BaseInterface {
	categoryId?: string;
	categoryName: string;
	categoryPic: string;
}

const baseUrl = '/category/';

export const getCategoriesByCategoryNamePageApi = (
	currentPage?: number,
	pageSize?: number,
	categoryName?: string
) => {
	return axios.get(baseUrl + 'getCategoriesByCategoryNamePage', {
		params: {
			currentPage,
			pageSize,
			categoryName
		}
	});
};
