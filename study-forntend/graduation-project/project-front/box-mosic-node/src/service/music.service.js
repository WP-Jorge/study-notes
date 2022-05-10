const path = require('path');

const {
	getMusicApi,
	getCatListApi,
	getPlaylistByCatNameApi,
	getSongsByPlaylistIdWithPageApi,
	getSongDetailBySongIdApi,
	getSongUrlBySongIdApi,
	getSongLyricBySongIdApi,
	getSingerDetailBySingerIdApi
} = require('../network/music.network');
const { sleep, getMusicLevel, isFileNameRight, getRightFileName } = require('../utils/util');
const { isFileExisted, mkdir, downloadFile, writeFile } = require('../utils/fileUtil');
const { writeMusicInfo } = require('../utils/ffmpegUtil');
const { MUSIC_HOME, DRIVE_LETTER } = require('../config/config.default');
const Music = require('../model/music.model');
const Singer = require('../model/singer.model');
const Category = require('../model/category.model');
const MusicSinger = require('../model/music_singer.model');
const MusicCategory = require('../model/music_category.model');
const seq = require('../database/sequelize');

class MusicService {
	async getMusic(keywords, cookie) {
		let res = await getMusicApi(keywords, cookie);
		if (res && res.data.code === 200) {
			return res.data.result.songs;
		}
		return null;
	}
	async getCatList(cookie) {
		let res = await getCatListApi(cookie);
		if (res && res.data.code === 200) {
			return res.data.sub;
		}
		return null;
	}
	async getPlaylistByCatName(catName, cookie) {
		let res = await getPlaylistByCatNameApi(catName, cookie);
		if (res && res.data.code === 200) {
			return res.data.playlists;
		}
		return null;
	}
	async getSongsByPlaylistIdWithPage(playlistId, cookie, pageSize, offset) {
		let res = await getSongsByPlaylistIdWithPageApi(playlistId, cookie, pageSize, offset);
		if (res && res.data.code === 200) {
			return res.data.songs;
		}
		return null;
	}

	async getSongDetailBySongId(songId, cookie) {
		let res = await getSongDetailBySongIdApi(songId, cookie);
		if (res && res.data.code === 200) {
			return res.data.songs[0];
		}
		return null;
	}

	async getSongUrlBySongId(songId, cookie) {
		let res = await getSongUrlBySongIdApi(songId, cookie);
		if (res && res.data.code === 200) {
			return res.data.data[0];
		}
		return null;
	}

	async getSongLyricBySongId(songId, cookie) {
		let res = await getSongLyricBySongIdApi(songId, cookie);
		if (res && res.data.code === 200) {
			return res.data;
		}
		return null;
	}

	async getSingerDetailBySingerId(singerId, cookie) {
		let res;
		try {
			res = await getSingerDetailBySingerIdApi(singerId, cookie);
		} catch (err) {
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

	async crawlMusic(cookie, pageSize = 1, offset = 0, targetOffset = 50, category) {
		let res = [];
		try {
			for (offset; offset < targetOffset; offset += pageSize) {
				// await sleep(800);
				console.log(offset, targetOffset);
				res.push(await this.craw(cookie, pageSize, offset, category, 0));
			}
			console.log('爬取数据完成！');
			return res;
		} catch (err) {
			console.log('错误offset：' + offset);
			console.log('错误err：' + err);
		}
	}

	async craw(cookie, pageSize = 1, offset = 0, category, time = 0) {
		console.log(pageSize, offset, category);
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
			let musicItem = {
				[cat.name]: {
					musicList: [],
					categoryInfo: {}
				}
			};
			let musicHome = path.join(DRIVE_LETTER, MUSIC_HOME);
			let isCatDirExisted = await isFileExisted(musicHome, cat.name);
			// console.log('isCatDirExisted', isCatDirExisted);
			if (!isCatDirExisted) {
				let isCatDirCreated = await mkdir(musicHome, cat.name);
				if (!isCatDirCreated) {
					console.log('创建分类文件夹失败：' + cat.name);
					continue;
				}
			}

			await sleep(time);
			let playlist = await this.getPlaylistByCatName(cat.name, cookie);
			// console.log(playlist);
			// playlist = playlist.slice(playlist.length - 1);
			// console.log(playlist);
			// await sleep(1000000);
			// console.log(playlist);

			// 3、根据歌单id获取歌曲列表
			let index = 0;
			let catImgPath;
			let catImgName;
			for (let playlistItem of playlist) {
				if (index++ === 0) {
					musicItem[cat.name]['categoryInfo'] = playlistItem;
					catImgPath = musicHome + cat.name;
					catImgName = cat.name + '.jpg';
					let catImgExisted = await isFileExisted(catImgPath, catImgName);
					if (!catImgExisted) {
						await sleep(time);
						if (!playlistItem.coverImgUrl) {
							console.log('url不正确：' + playlistItem.coverImgUrl);
							continue;
						}
						let isCatImgCreated = await downloadFile(
							musicHome + cat.name,
							catImgName,
							playlistItem.coverImgUrl
						);
						if (!isCatImgCreated) {
							console.log('创建分类图片失败：' + cat.name);
							continue;
						}
					}
				}
				await sleep(time);
				let songList = await this.getSongsByPlaylistIdWithPage(playlistItem.id, cookie, pageSize, offset);
				// console.log(songList);

				// 4、循环歌曲列表，根据歌曲id获取对应数据
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
					let songDetail = await this.getSongDetailBySongId(songItem.id, cookie);
					// console.log(songDetail);
					let musicDirPath = musicHome + cat.name;
					let musicDirName = songDetail.name + '-' + songDetail.id;
					let isMusicDirExisted = await isFileExisted(musicDirPath, musicDirName);
					if (!isMusicDirExisted) {
						let dirNameFlag = isFileNameRight(musicDirName);
						if (!dirNameFlag) {
							musicDirName = getRightFileName(musicDirName);
						}
						let isMusicDirCreated = await mkdir(musicDirPath, musicDirName);
						if (!isMusicDirCreated) {
							console.log('创建文件夹失败：' + musicDirName);
							continue;
						}
					}

					let musicPath = musicHome + cat.name + '/' + musicDirName;
					let musicName = musicDirName + '.' + songUrl.type;
					let isMusicExisted = await isFileExisted(musicPath, musicName);
					if (!isMusicExisted) {
						await sleep(time);

						// 下载音乐并将音乐信息写入音乐
						let artists = [];
						songDetail.ar.map(item => {
							artists.push(item.name);
						});
						let musicInfo = {
							album: songDetail.al.name,
							artists,
							genre: songDetail.al.name,
							title: songDetail.name
						};
						let isMusicInfoWrited = await writeMusicInfo(songUrl.url, musicPath, musicName, musicInfo);
						if (!isMusicInfoWrited) {
							console.log('音乐信息写入失败：' + musicName);
							continue;
						}

						// let isMusicCreated = await downloadFile(musicPath, tempMusicName, songUrl.url);
						// if (!isMusicCreated) {
						// 	return null;
						// }
					}
					let musicImgName = musicDirName + '.jpg';
					let isMusicImgExisted = await isFileExisted(musicPath, musicImgName);
					if (!isMusicImgExisted) {
						if (!songDetail.al.picUrl) {
							console.log('url不正确：' + songDetail.al.picUrl);
							continue;
						}
						await sleep(time);
						let isMusicImgCreated = await downloadFile(musicPath, musicImgName, songDetail.al.picUrl);
						if (!isMusicImgCreated) {
							console.log('创建音乐文件失败：' + musicImgName);
							continue;
						}
					}

					// ③ 根据歌曲id获取歌曲歌词
					await sleep(time);
					let songLyric = await this.getSongLyricBySongId(songItem.id, cookie);
					let musicLyricName = musicDirName + '.lrc';
					let isMusicLyricExisted = await isFileExisted(musicPath, musicLyricName);
					if (!isMusicLyricExisted) {
						let isLyricWrited = await writeFile(musicPath, musicLyricName, songLyric.lrc.lyric);
						if (!isLyricWrited) {
							console.log('歌词文件写入失败：' + musicLyricName);
							continue;
						}
					}
					// console.log(songLyric);

					// ④ 根据歌曲id获取歌手信息
					let singerIds = songItem.ar;
					let songSingers = [];
					for (let singerItem of singerIds) {
						if (!singerItem.id) {
							continue;
						}
						await sleep(time);
						let singer = await this.getSingerDetailBySingerId(singerItem.id, cookie);
						if (singer) {
							songSingers.push(singer);
						}
						// console.log(singer);
					}

					musicItem[cat.name]['musicList'].push({
						['songDetail']: songDetail,
						['songUrl']: songUrl,
						['songLyric']: songLyric,
						['songSingers']: songSingers,
						['songInfo']: {
							categoryId: playlistItem.id,
							category: cat.name,
							playlistName: playlistItem.name,
							categoryPic: path.join(catImgPath, catImgName)
						}
					});

					try {
						await seq.transaction(async t => {
							await Music.findOrCreate({
								where: {
									music_id: songDetail.id
								},
								defaults: {
									music_title: songDetail.name,
									music_pic: musicImgName,
									lyric: songLyric.lrc.lyric,
									lyric_url: musicLyricName,
									album: songDetail.al.name,
									genre: songDetail.al.name,
									duration: songDetail.dt,
									size: songUrl.size,
									level: getMusicLevel(songUrl.br),
									music_format: songUrl.type,
									bitrate: songUrl.br,
									views: 0,
									music_url: musicName
								},
								transaction: t
							});
							let [categoryInfo] = await Category.findOrCreate({
								where: {
									category_name: cat.name
								},
								defaults: {
									category_name: cat.name,
									category_pic: catImgName
								},
								transaction: t
							});
							for (let singerInfo of songSingers) {
								let isSingerDirExisted = await isFileExisted(musicHome, '歌手');
								if (!isSingerDirExisted) {
									let isSingerDirCreated = await mkdir(musicHome, '歌手');
									if (!isSingerDirCreated) {
										console.log('创建文件夹失败：' + musicHome + '歌手');
										continue;
									}
								}
								let singerPath = musicHome + '歌手';
								let singerName = singerInfo.artist.name + '-' + singerInfo.artist.id + '.jpg';
								let isSingerNameRight = isFileNameRight(singerName);
								if (!isSingerNameRight) {
									singerName = getRightFileName(singerName);
								}
								let isSingerImgExisted = await isFileExisted(singerPath, singerName);
								if (!isSingerImgExisted) {
									let isSingerImgCreated = await downloadFile(
										singerPath,
										singerName,
										singerInfo.artist.cover
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
										singer_name: isSingerNameRight
											? singerInfo.artist.name
											: getRightFileName(singerInfo.artist.name),
										singer_pic: singerName,
										total_views: 0
									},
									transaction: t
								});
							}
							await MusicCategory.findOrCreate({
								where: {
									music_id: songDetail.id,
									category_id: categoryInfo.category_id
								},
								defaults: {
									music_id: songDetail.id,
									category_id: categoryInfo.category_id
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

module.exports = new MusicService();
