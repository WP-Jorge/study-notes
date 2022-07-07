import Router from 'koa-router';

import { musicController } from '../controller/music.controller';

const {
	getMusic,
	getCatList,
	getPlaylistByCatName,
	getSongsByPlaylistIdWithPage,
	getSongDetailBySongId,
	getSongUrlBySongId,
	getSongLyricBySongId,
	getSingerDetailBySingerId,
	getAlbumDetailByAlbumId,
	spiderMusic,
	downloadMusicByUrl
} = musicController;

import { userLoginValidator } from '../middleware/user.middleware';

const router = new Router({ prefix: '/music' });

router.get('/search', getMusic);
router.get('/getCatList', getCatList);
router.get('/getPlaylistByCatName', getPlaylistByCatName);
router.get('/getSongsByPlaylistIdWithPage', getSongsByPlaylistIdWithPage);
router.get('/getSongDetailBySongId', getSongDetailBySongId);
router.get('/getSongUrlBySongId', getSongUrlBySongId);
router.get('/getSongLyricBySongId', getSongLyricBySongId);
router.get('/getSingerDetailBySingerId', getSingerDetailBySingerId);
router.get('/getAlbumDetailByAlbumId', getAlbumDetailByAlbumId);
router.get('/spiderMusic', userLoginValidator, spiderMusic);
router.post('/downloadMusicByUrl', downloadMusicByUrl, downloadMusicByUrl);

export { router };
