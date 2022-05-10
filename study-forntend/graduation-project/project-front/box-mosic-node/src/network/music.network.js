const axios = require('./index');

const getMusicApi = (keywords, cookie) => {
	return axios.get('/search', {
		params: {
			keywords,
			cookie
		}
	});
};

const getCatListApi = (cookie) => {
	return axios.get('/playlist/catlist', {
		params: {
			cookie
		}
	});
};

const getPlaylistByCatNameApi = (catName, cookie) => {
	return axios.get('/top/playlist', {
		params: {
			cat: catName,
			cookie
		}
	});
};

const getSongsByPlaylistIdWithPageApi = (playListId, cookie, pageSize = 100, offset = 1) => {
	return axios.get('/playlist/track/all', {
		params: {
			id: playListId,
			cookie,
			limit: pageSize,
			offset
		}
	});
};

const getSongDetailBySongIdApi = (songId, cookie) => {
	return axios.get('/song/detail', {
		params: {
			ids: songId,
			cookie
		}
	});
};

const getSongUrlBySongIdApi = (songId, cookie, br = 999000) => {
	return axios.get('/song/url', {
		params: {
			id: songId,
			cookie,
			br: 999000
		}
	});
};

const getSongLyricBySongIdApi = (songId, cookie) => {
	return axios.get('lyric', {
		params: {
			id: songId,
			cookie
		}
	});
};

const getSingerDetailBySingerIdApi = (singerId, cookie) => {
	return axios.get('/artist/detail', {
		params: {
			id: singerId,
			cookie
		}
	});
};


module.exports = {
	getMusicApi,
	getCatListApi,
	getPlaylistByCatNameApi,
	getSongsByPlaylistIdWithPageApi,
	getSongDetailBySongIdApi,
	getSongUrlBySongIdApi,
	getSongLyricBySongIdApi,
	getSingerDetailBySingerIdApi
};
