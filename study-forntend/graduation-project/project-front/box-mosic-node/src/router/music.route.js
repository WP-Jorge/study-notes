const Router = require('koa-router');

const {
	getMusic,
	getCatList,
	getPlaylistByCatName,
	getSongsByPlaylistIdWithPage,
	getSongDetailBySongId,
	getSongUrlBySongId,
	getSongLyricBySongId,
	getSingerDetailBySingerId,
	crawlMusic,
	downloadMusicByUrl
} = require('../controller/music.controller');

const { userLoginValidator } = require('../middleware/user.middleware');

const router = new Router({ prefix: '/music' });

router.get('/search', getMusic);
router.get('/getCatList', getCatList);
router.get('/getPlaylistByCatName', getPlaylistByCatName);
router.get('/getSongsByPlaylistIdWithPage', getSongsByPlaylistIdWithPage);
router.get('/getSongDetailBySongId', getSongDetailBySongId);
router.get('/getSongUrlBySongId', getSongUrlBySongId);
router.get('/getSongLyricBySongId', getSongLyricBySongId);
router.get('/getSingerDetailBySingerId', getSingerDetailBySingerId);
router.get('/crawlMusic', userLoginValidator, crawlMusic);
router.post('/downloadMusicByUrl', downloadMusicByUrl, downloadMusicByUrl);

module.exports = router;
