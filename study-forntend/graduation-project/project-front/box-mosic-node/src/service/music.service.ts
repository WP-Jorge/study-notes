import path from 'path';

import {
	getMusicApi,
	getCatListApi,
	getPlaylistByCatNameApi,
	getSongsByPlaylistIdWithPageApi,
	getSongDetailBySongIdApi,
	getSongUrlBySongIdApi,
	getSongLyricBySongIdApi,
	getSingerDetailBySingerIdApi
} from '../network/music.network';
import { sleep, getMusicLevel, getRightFileName } from '../utils/util';
import { isFileExisted, downloadFile, mkdirSavely } from '../utils/fileUtil';
import { MUSIC_HOME, DRIVE_LETTER } from '../config/config.default';
import { Music } from '../model/music.model';
import { Singer } from '../model/singer.model';
import { Category } from '../model/category.model';
import { MusicSinger } from '../model/music_singer.model';
import { MusicCategory } from '../model/music_category.model';
import { seq } from '../database/sequelize';

class MusicService {
	async getMusic(keywords: string, cookie: string) {
		let res = await getMusicApi(keywords, cookie);
		if (res && res.data.code === 200) {
			return res.data.result.songs;
		}
		return null;
	}
	async getCatList(cookie: string) {
		let res = await getCatListApi(cookie);
		if (res && res.data.code === 200) {
			return res.data.sub;
		}
		return null;
	}
	async getPlaylistByCatName(catName: string, cookie: string) {
		let res = await getPlaylistByCatNameApi(catName, cookie);
		if (res && res.data.code === 200) {
			return res.data.playlists;
		}
		return null;
	}
	async getSongsByPlaylistIdWithPage(
		playlistId: string,
		cookie: string,
		pageSize: number,
		offset: number
	) {
		let res = await getSongsByPlaylistIdWithPageApi(
			playlistId,
			cookie,
			pageSize,
			offset
		);
		if (res && res.data.code === 200) {
			return res.data.songs;
		}
		return null;
	}

	async getSongDetailBySongId(songId: string, cookie: string) {
		let res = await getSongDetailBySongIdApi(songId, cookie);
		if (res && res.data.code === 200) {
			return res.data.songs[0];
		}
		return null;
	}

	async getSongUrlBySongId(songId: string, cookie: string) {
		let res = await getSongUrlBySongIdApi(songId, cookie);
		if (res && res.data.code === 200) {
			return res.data.data[0];
		}
		return null;
	}

	async getSongLyricBySongId(songId: string, cookie: string) {
		let res = await getSongLyricBySongIdApi(songId, cookie);
		if (res && res.data.code === 200) {
			return res.data;
		}
		return null;
	}

	async getSingerDetailBySingerId(singerId: string, cookie: string) {
		let res;
		try {
			res = await getSingerDetailBySingerIdApi(singerId, cookie);
		} catch (err: any) {
			if (err.response.status === 404) {
				console.log(err.response.data.message);
			}
			return null;
		}
		if (res && res.data.code === 200) {
			return res.data.data;
		}
		return null;
	}

	async spiderMusic(
		cookie: string,
		pageSize = 1,
		offset = 0,
		targetOffset = 50,
		category: string
	) {
		let res = [];
		try {
			for (offset; offset < targetOffset; offset += pageSize) {
				// await sleep(200);
				console.log(offset, targetOffset);
				res.push(await this.spider(cookie, pageSize, offset, category));
			}
			console.log('爬取数据完成！');
			return res;
		} catch (err) {
			console.log('错误offset：' + offset);
			console.log('错误err：' + err);
		}
	}

	async spider(
		cookie: string,
		pageSize = 1,
		offset = 0,
		category: string,
		time = 0
	) {
		console.log(pageSize, offset, category);

		// 0、创建目录
		let musicHome = path.join(DRIVE_LETTER as string, MUSIC_HOME as string);
		// let musicDirPath = path.join(musicHome, 'musics');
		// let categoryPictureDirPath = path.join(musicHome, 'category-pictures');
		// let musicPictureDirPath = path.join(musicHome, 'music-pictures');
		// let singerPictureDirPath = path.join(musicHome, 'singer-pictures');
		// console.log(
		// 	musicDirPath,
		// 	categoryPictureDirPath,
		// 	musicPictureDirPath,
		// 	singerPictureDirPath
		// );
		let isMusicDirCreated = await mkdirSavely(musicHome, 'musics');
		let isMusicPictureDirCreated = await mkdirSavely(
			musicHome,
			'music-pictures'
		);
		let isCategoryPictureDirCreated = await mkdirSavely(
			musicHome,
			'category-pictures'
		);
		let isSingerPictureDirCreated = await mkdirSavely(
			musicHome,
			'singer-pictures'
		);
		console.log(
			isMusicDirCreated,
			isCategoryPictureDirCreated,
			isMusicPictureDirCreated,
			isSingerPictureDirCreated
		);

		if (
			isMusicDirCreated &&
			isCategoryPictureDirCreated &&
			isMusicPictureDirCreated &&
			isSingerPictureDirCreated
		) {
			console.log('创建音乐目录成功');
			console.log('创建音乐图片目录成功');
			console.log('创建分类图片目录成功');
			console.log('创建歌手图片目录成功');
		}
		// 1、获取分类列表
		let allSongs = [];
		let catList = [{ name: category }];
		if (!category) {
			catList = await this.getCatList(cookie);
		}
		// catList = catList.slice(0, 4);
		// let ;
		// console.log(catList);

		// 2、循环分类列表获取分类歌单
		for (let cat of catList) {
			let musicItem: any = {
				[cat.name]: {
					musicList: [] as any[],
					categoryInfo: {} as any
				}
			};
			// let isCatDirExisted = await isFileExisted(musicHome, 'categories');
			// // console.log('isCatDirExisted', isCatDirExisted);
			// if (!isCatDirExisted) {
			// 	let isCatDirCreated = await mkdir(musicHome, 'categories');
			// 	if (!isCatDirCreated) {
			// 		console.log('创建分类文件夹失败：' + 'categories');
			// 		continue;
			// 	}
			// }

			await sleep(time);
			let playlist = await this.getPlaylistByCatName(cat.name, cookie);
			// console.log(playlist);
			// playlist = playlist.slice(playlist.length - 1);
			// console.log(playlist);
			// await sleep(1000000);
			// console.log(playlist);

			// 3、根据歌单id获取歌曲列表
			let index = 0;
			let catImgPath = '';
			let catImgName = '';
			for (let playlistItem of playlist) {
				if (index++ === 0) {
					musicItem[cat.name]['categoryInfo'] = playlistItem;
					catImgPath = musicHome + 'category-pictures';
					catImgName = getRightFileName(cat.name) + '.jpg';
					let catImgExisted = await isFileExisted(catImgPath, catImgName);
					if (!catImgExisted) {
						if (!playlistItem.coverImgUrl) {
							console.log('url不正确：' + playlistItem.coverImgUrl);
							continue;
						}
						await sleep(time);
						let isCatImgCreated = await downloadFile(
							playlistItem.coverImgUrl,
							catImgPath,
							catImgName
						);
						if (!isCatImgCreated) {
							console.log('创建分类图片失败：' + cat.name);
							continue;
						}
					}
				}
				await sleep(time);

				// 4、循环歌曲列表，根据歌曲id获取对应数据
				let songList = await this.getSongsByPlaylistIdWithPage(
					playlistItem.id,
					cookie,
					pageSize,
					offset
				);
				// console.log(songList);
				for (let songItem of songList) {
					// ① 根据歌曲id获取歌曲链接
					await sleep(time);
					let songUrl = await this.getSongUrlBySongId(songItem.id, cookie);
					// console.log(songUrl);
					if (!songUrl.url) {
						continue;
					}

					// ② 根据歌曲id获取歌曲详情
					await sleep(time);
					let songDetail = await this.getSongDetailBySongId(
						songItem.id,
						cookie
					);
					// console.log(songDetail);
					// let isMusicDirExisted = await isFileExisted(musicHome, 'musics');
					// if (!isMusicDirExisted) {
					// 	// let dirNameFlag = isFileNameRight(musicDirName);
					// 	// if (!dirNameFlag) {
					// 	// 	musicDirName = getRightFileName(musicDirName);
					// 	// }
					// 	let isMusicDirCreated = await mkdir(musicHome, 'musics');
					// 	if (!isMusicDirCreated) {
					// 		console.log('创建文件夹失败：' + 'musics');
					// 		continue;
					// 	}
					// }

					let musicPath = musicHome + 'musics';
					let musicLeftName =
						getRightFileName(songDetail.name) + '-' + songDetail.id;
					// let dirNameFlag = isFileNameRight(musicLeftName);
					// if (!dirNameFlag) {
					// musicLeftName = getRightFileName(musicLeftName);
					// }
					let musicName = musicLeftName + '.' + songUrl.type;
					let isMusicExisted = await isFileExisted(musicPath, musicName);
					if (!isMusicExisted) {
						await sleep(time);

						// 下载音乐并将音乐信息写入音乐
						let artists = [];
						songDetail.ar.map((item: any) => {
							artists.push(item.name);
						});
						// let musicInfo = {
						// 	album: songDetail.al.name,
						// 	artists,
						// 	genre: songDetail.al.name,
						// 	title: songDetail.name
						// };
						let isMusicDownload = await downloadFile(
							songUrl.url,
							musicPath,
							musicName
						);
						// let isMusicInfoWrited = await writeMusicInfo(
						// 	songUrl.url,
						// 	musicPath,
						// 	musicName
						// );
						if (!isMusicDownload) {
							console.log('歌曲写入失败：' + musicName);
							continue;
						}

						// let isMusicCreated = await downloadFile(musicPath, tempMusicName, songUrl.url);
						// if (!isMusicCreated) {
						// 	return null;
						// }
					}
					let musicPicturePath = musicHome + 'music-pictures';
					let musicImgName = getRightFileName(musicLeftName) + '.jpg';
					let isMusicImgExisted = await isFileExisted(
						musicPicturePath,
						musicImgName
					);
					if (!isMusicImgExisted) {
						// let isPictureDirCreated = await mkdir(musicHome, 'music-pictures');
						// if (!isPictureDirCreated) {
						// 	console.log('创建文件夹失败：' + 'music-pictures');
						// 	continue;
						// }
						if (!songDetail.al.picUrl) {
							console.log('url不正确：' + songDetail.al.picUrl);
							continue;
						}
						await sleep(time);
						let isMusicImgCreated = await downloadFile(
							songDetail.al.picUrl,
							musicPicturePath,
							musicImgName
						);
						if (!isMusicImgCreated) {
							console.log('创建音乐文件失败：' + musicImgName);
							continue;
						}
					}

					// ③ 根据歌曲id获取歌曲歌词
					await sleep(time);
					let songLyric = await this.getSongLyricBySongId(songItem.id, cookie);
					// let musicLyricPath = musicHome + 'music-lyrics';
					// let musicLyricName = musicLeftName + '.lrc';
					// let isMusicLyricExisted = await isFileExisted(musicLyricPath, musicLyricName);
					// if (!isMusicLyricExisted) {
					// 	let isLyricWrited = await writeFile(musicLyricPath, musicLyricName, songLyric.lrc.lyric);
					// 	if (!isLyricWrited) {
					// 		console.log('歌词文件写入失败：' + musicLyricName);
					// 		continue;
					// 	}
					// }
					// console.log(songLyric);

					// ④ 根据歌曲id获取歌手信息
					let singerIds = songItem.ar;
					let songSingers = [] as any[];
					for (let singerItem of singerIds) {
						if (!singerItem.id) {
							continue;
						}
						await sleep(time);
						let singer = await this.getSingerDetailBySingerId(
							singerItem.id,
							cookie
						);
						if (singer) {
							songSingers.push(singer);
						}
						// console.log(singer);
					}

					musicItem[cat.name]['musicList'].push({
						songDetail: songDetail,
						songUrl: songUrl,
						songLyric: songLyric,
						songSingers: songSingers,
						songInfo: {
							categoryId: playlistItem.id,
							category: cat.name,
							playlistName: playlistItem.name,
							categoryPic: path.join(catImgPath as string, catImgName as string)
						}
					});

					try {
						await seq.transaction(async (t: any) => {
							await Music.findOrCreate({
								where: {
									music_id: songDetail.id
								},
								defaults: {
									music_title: getRightFileName(songDetail.name),
									music_pic: musicImgName,
									lyric: songLyric.lrc.lyric,
									album: songDetail.al.name,
									genre: songDetail.al.name,
									duration: songDetail.dt,
									size: songUrl.size,
									level: getMusicLevel(songUrl.br),
									music_format: songUrl.type,
									bitrate: songUrl.br,
									total_views: 0,
									music_url: musicName,
									deleted: 0
								},
								transaction: t
							});
							let [categoryInfo] = await Category.findOrCreate({
								where: {
									category_name: getRightFileName(cat.name)
								},
								defaults: {
									category_name: getRightFileName(cat.name),
									category_pic: catImgName
								},
								transaction: t
							});
							for (let singerInfo of songSingers) {
								// let isSingerDirExisted = await isFileExisted(musicHome, 'singers');
								// if (!isSingerDirExisted) {
								// 	let isSingerDirCreated = await mkdir(musicHome, 'singers');
								// 	if (!isSingerDirCreated) {
								// 		console.log('创建文件夹失败：' + musicHome + 'singers');
								// 		continue;
								// 	}
								// }
								let singerPath = musicHome + 'singer-pictures';
								let singerName =
									getRightFileName(singerInfo.artist.name) +
									'-' +
									singerInfo.artist.id +
									'.jpg';
								// let isSingerNameRight = isFileNameRight(singerName);
								// if (!isSingerNameRight) {
								// singerName = getRightFileName(singerName);
								// }
								let isSingerImgExisted = await isFileExisted(
									singerPath,
									singerName
								);
								if (!isSingerImgExisted) {
									let isSingerImgCreated = await downloadFile(
										singerInfo.artist.cover,
										singerPath,
										singerName
									);
									if (!isSingerImgCreated) {
										console.log('下载文件失败：' + singerName);
										// throw new Error('下载文件失败');
										continue;
									}
								}
								await Singer.findOrCreate({
									where: {
										singer_id: singerInfo.artist.id
									},
									defaults: {
										singer_id: singerInfo.artist.id,
										singer_name: getRightFileName(singerInfo.artist.name),
										singer_pic: singerName,
										total_views: 0,
										deleted: 0
									},
									transaction: t
								});
							}
							await MusicCategory.findOrCreate({
								where: {
									music_id: songDetail.id,
									category_id: (categoryInfo as any).category_id
								},
								defaults: {
									music_id: songDetail.id,
									category_id: (categoryInfo as any).category_id
								},
								transaction: t
							});
							for (let singerInfo of songSingers) {
								await MusicSinger.findOrCreate({
									where: {
										music_id: songDetail.id,
										singer_id: singerInfo.artist.id
									},
									defaults: {
										music_id: songDetail.id,
										singer_id: singerInfo.artist.id
									},
									transaction: t
								});
							}
						});
					} catch (err) {
						console.error('错误：' + err);
						continue;
					}
				}
			}
			allSongs.push(musicItem);
		}
		// console.log(allSongs);
		return allSongs;
	}
}

export const musicService = new MusicService();
