import { musicService } from '../service/music.service';
import { ResultType } from '../constant/ResultType';
import { downloadFile } from '../utils/fileUtil';
import { MUSIC_HOME } from '../config/config.default';
import { Context, Next } from 'Koa';

class MusicController {
	async getMusic(ctx: Context, next: Next) {
		let { keywords, cookie } = ctx.request.query;
		cookie = encodeURIComponent(cookie as string);
		let res = await musicService.getMusic(keywords as string, cookie);
		if (res) {
			ctx.body = ResultType.success('获取音乐成功', res);
			return await next();
		}
		ctx.body = ResultType.error('获取音乐失败');
		await next();
	}
	async getCatList(ctx: Context, next: Next) {
		console.log(musicService.getCatList);
		let { cookie } = ctx.request.query;
		cookie = encodeURIComponent(cookie as string);
		let res = await musicService.getCatList(cookie);
		if (res) {
			ctx.body = ResultType.success('获取分类成功', res);
			return await next();
		}
		ctx.body = ResultType.error('获取分类失败');
		await next();
	}
	async getPlaylistByCatName(ctx: Context, next: Next) {
		let { catName, cookie } = ctx.query;
		cookie = encodeURIComponent(cookie as string);
		let res = await musicService.getPlaylistByCatName(
			catName as string,
			cookie
		);
		if (res) {
			ctx.body = ResultType.success('获取分类歌单列表成功', res);
			return await next();
		}
		ctx.body = ResultType.error('获取分类歌单列表失败');
		await next();
	}
	async getSongsByPlaylistIdWithPage(ctx: Context, next: Next) {
		let { playListId, cookie, pageSize, offset } = ctx.query;
		cookie = encodeURIComponent(cookie as string);
		let res = await musicService.getSongsByPlaylistIdWithPage(
			playListId as string,
			cookie,
			pageSize as unknown as number,
			offset as unknown as number
		);
		if (res) {
			ctx.body = ResultType.success('获取歌单歌曲成功', res);
			return await next();
		}
		ctx.body = ResultType.error('获取歌单歌曲失败');
		await next();
	}
	async getSongDetailBySongId(ctx: Context, next: Next) {
		let { songId, cookie } = ctx.query;
		cookie = encodeURIComponent(cookie as string);
		let res = await musicService.getSongDetailBySongId(
			songId as string,
			cookie
		);
		if (res) {
			ctx.body = ResultType.success('获取音乐详情成功', res);
			return await next();
		}
		ctx.body = ResultType.error('获取音乐详情失败');
		await next();
	}
	async getSongUrlBySongId(ctx: Context, next: Next) {
		let { songId, cookie } = ctx.query;
		cookie = encodeURIComponent(cookie as string);
		let res = await musicService.getSongUrlBySongId(songId as string, cookie);
		if (res) {
			ctx.body = ResultType.success('获取音乐链接成功', res);
			return await next();
		}
		ctx.body = ResultType.error('获取音乐链接失败');
		await next();
	}

	async getSongLyricBySongId(ctx: Context, next: Next) {
		let { songId, cookie } = ctx.query;
		cookie = encodeURIComponent(cookie as string);
		let res = await musicService.getSongLyricBySongId(songId as string, cookie);
		if (res) {
			ctx.body = ResultType.success('获取歌词成功', res);
			return await next();
		}
		ctx.body = ResultType.error('获取歌词失败');
		await next();
	}

	async getSingerDetailBySingerId(ctx: Context, next: Next) {
		let { singerId, cookie } = ctx.query;
		cookie = encodeURIComponent(cookie as string);
		let res = await musicService.getSingerDetailBySingerId(
			singerId as string,
			cookie
		);
		if (res) {
			ctx.body = ResultType.success('获取歌手信息成功', res);
			return await next();
		}
		ctx.body = ResultType.error('获取歌手信息失败');
		await next();
	}

	async getAlbumDetailByAlbumId(ctx: Context, next: Next) {
		let { albumId, cookie } = ctx.query;
		cookie = encodeURIComponent(cookie as string);
		let res = await musicService.getAlbumDetailByAlbumId(
			albumId as string,
			cookie
		);
		console.log(res);
		if (res) {
			ctx.body = ResultType.success('获取专辑信息成功', res);
			return await next();
		}
		ctx.body = ResultType.error('获取专辑信息失败');
		await next();
	}

	async spiderMusic(ctx: Context, next: Next) {
		let {
			cookie,
			pageSize,
			offset,
			category,
			targetOffset,
			playlistSize,
			playlistOffset
		} = ctx.query;
		cookie = encodeURIComponent(cookie as string);
		let res = await musicService.spiderMusic(
			cookie,
			parseInt(playlistSize as string),
			parseInt(playlistOffset as string),
			parseInt(pageSize as string),
			parseInt(offset as string),
			parseInt(targetOffset as string),
			category as string
		);
		console.log(res);
		ctx.body = res;
		await next();
	}

	async downloadMusicByUrl(ctx: Context) {
		let { musicUrl } = (ctx.request as any).body;
		let res = await downloadFile(MUSIC_HOME as string, 'asd.flac', musicUrl);
		ctx.body = res;
	}
}

export const musicController = new MusicController();
