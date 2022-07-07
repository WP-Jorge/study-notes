import axios from './index';

export const getMusicApi = (keywords: string, cookie: string) => {
	return axios.get('/search', {
		params: {
			keywords,
			cookie
		}
	});
};

export const getCatListApi = (cookie: string) => {
	return axios.get('/playlist/catlist', {
		params: {
			cookie
		}
	});
};

export const getPlaylistByCatNameApi = (catName: string, cookie: string) => {
	return axios.get('/top/playlist', {
		params: {
			cat: catName,
			cookie
		}
	});
};

export const getSongsByPlaylistIdWithPageApi = (
	playListId: string,
	cookie: string,
	pageSize = 100,
	offset = 1
) => {
	return axios.get('/playlist/track/all', {
		params: {
			id: playListId,
			cookie,
			limit: pageSize,
			offset
		}
	});
};

export const getSongDetailBySongIdApi = (songId: string, cookie: string) => {
	return axios.get('/song/detail', {
		params: {
			ids: songId,
			cookie
		}
	});
};

export const getAlbumDetailByAlbumIdApi = (albumId: string, cookie: string) => {
	return axios.get('/album', {
		params: {
			id: albumId,
			cookie
		}
	});
};

export const getSongUrlBySongIdApi = (songId: string, cookie: string) => {
	return axios.get('/song/url', {
		params: {
			id: songId,
			cookie
		}
	});
};

export const getSongLyricBySongIdApi = (songId: string, cookie: string) => {
	return axios.get('lyric', {
		params: {
			id: songId,
			cookie
		}
	});
};

export const getSingerDetailBySingerIdApi = (
	singerId: string,
	cookie: string
) => {
	return axios.get('/artist/detail', {
		params: {
			id: singerId,
			cookie
		}
	});
};
