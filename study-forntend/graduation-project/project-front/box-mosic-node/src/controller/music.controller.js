const musicService = require('../service/music.service');
const ResultType = require('../constant/resultType');
const { downloadFile } = require('../utils/fileUtil');
const { MUSIC_HOME } = require('../config/config.default');

class MusicController {
	async getMusic(ctx, next) {
		let { keywords, cookie } = ctx.request.query;
		cookie = encodeURIComponent(cookie);
		let res = await musicService.getMusic(keywords, cookie);
		if (res) {
			ctx.body = ResultType.success('获取音乐成功', res);
			return await next();
		}
		ctx.body = ResultType.error('获取音乐失败');
		await next();
	}
	async getCatList(ctx, next) {
		console.log(musicService.getCatList);
		let { cookie } = ctx.request.query;
		cookie = encodeURIComponent(cookie);
		let res = await musicService.getCatList(cookie);
		if (res) {
			ctx.body = ResultType.success('获取分类成功', res);
			return await next();
		}
		ctx.body = ResultType.error('获取分类失败');
		await next();
	}
	async getPlaylistByCatName(ctx, next) {
		let { catName, cookie } = ctx.query;
		cookie = encodeURIComponent(cookie);
		let res = await musicService.getPlaylistByCatName(catName, cookie);
		if (res) {
			ctx.body = ResultType.success('获取分类歌单列表成功', res);
			return await next();
		}
		ctx.body = ResultType.error('获取分类歌单列表失败');
		await next();
	}
	async getSongsByPlaylistIdWithPage(ctx, next) {
		let { playListId, cookie, pageSize, offset } = ctx.query;
		cookie = encodeURIComponent(cookie);
		let res = await musicService.getSongsByPlaylistIdWithPage(playListId, cookie, pageSize, offset);
		if (res) {
			ctx.body = ResultType.success('获取歌单歌曲成功', res);
			return await next();
		}
		ctx.body = ResultType.error('获取歌单歌曲失败');
		await next();
	}
	async getSongDetailBySongId(ctx, next) {
		let { songId, cookie } = ctx.query;
		cookie = encodeURIComponent(cookie);
		let res = await musicService.getSongDetailBySongId(songId, cookie);
		if (res) {
			ctx.body = ResultType.success('获取音乐详情成功', res);
			return await next();
		}
		ctx.body = ResultType.error('获取音乐详情失败');
		await next();
	}
	async getSongUrlBySongId(ctx, next) {
		let { songId, cookie } = ctx.query;
		cookie = encodeURIComponent(cookie);
		let res = await musicService.getSongUrlBySongId(songId, cookie);
		if (res) {
			ctx.body = ResultType.success('获取音乐链接成功', res);
			return await next();
		}
		ctx.body = ResultType.error('获取音乐链接失败');
		await next();
	}

	async getSongLyricBySongId(ctx, next) {
		let { songId, cookie } = ctx.query;
		cookie = encodeURIComponent(cookie);
		let res = await musicService.getSongLyricBySongId(songId, cookie);
		if (res) {
			ctx.body = ResultType.success('获取歌词成功', res);
			return await next();
		}
		ctx.body = ResultType.error('获取歌词失败');
		await next();
	}

	async getSingerDetailBySingerId(ctx, next) {
		let { singerId, cookie } = ctx.query;
		cookie = encodeURIComponent(cookie);
		let res = await musicService.getSingerDetailBySingerId(singerId, cookie);
		if (res) {
			ctx.body = ResultType.success('获取歌手信息成功', res);
			return await next();
		}
		ctx.body = ResultType.error('获取歌手信息失败');
		await next();
	}

	async crawlMusic(ctx, next) {
		let { cookie, pageSize, offset, category, targetOffset } = ctx.query;
		cookie = encodeURIComponent(cookie);
		let res = await musicService.crawlMusic(cookie, parseInt(pageSize), parseInt(offset), parseInt(targetOffset), category);
		console.log(res);
		ctx.body = res;
		await next();
	}

	async downloadMusicByUrl(ctx, next) {
		let { musicUrl } = ctx.request.body;
		let res = await downloadFile(MUSIC_HOME, 'asd.flac', musicUrl);
		ctx.body = res;
	}
}

module.exports = new MusicController();
