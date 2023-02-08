import axios from '@/networks';
const baseUrl = '/collection/';

export const getCollectionApi = () => {
	return axios.get(baseUrl + 'getCollection');
};

export const addMusicToCollectionApi = (ids: Array<bigint | string>) => {
	return axios.post(baseUrl + 'addMusicToCollection', ids);
};

export const deleteCollectionApi = (ids: Array<bigint | string>) => {
	return axios.delete(baseUrl + 'deleteCollection', {
		data: ids
	});
};
