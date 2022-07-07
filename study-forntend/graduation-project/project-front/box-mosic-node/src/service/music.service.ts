import path from 'path';

import {
	getMusicApi,
	getCatListApi,
	getPlaylistByCatNameApi,
	getSongsByPlaylistIdWithPageApi,
	getSongDetailBySongIdApi,
	getSongUrlBySongIdApi,
	getSongLyricBySongIdApi,
	getSingerDetailBySingerIdApi,
	getAlbumDetailByAlbumIdApi
} from '../network/music.network';
import {
	sleep,
	getMusicLevel,
	getRightFileName,
	getResourceType
} from '../utils/util';
import { isFileExisted, downloadFile, mkdirSavely } from '../utils/fileUtil';
import { MUSIC_HOME, DRIVE_LETTER } from '../config/config.default';
import { Music } from '../model/music.model';
import { Singer } from '../model/singer.model';
import { Category } from '../model/category.model';
import { MusicSinger } from '../model/music_singer.model';
import { MusicCategory } from '../model/music_category.model';
import { seq } from '../database/sequelize';
import { Album } from '../model/album.model';
import { Playlist } from '../model/playlist.model';
import { PlaylistCategory } from '../model/playlist_category.model';
import { MusicPlaylist } from '../model/music_playlist.model';

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

	async getAlbumDetailByAlbumId(albumId: string, cookie: string) {
		let res;
		try {
			res = await getAlbumDetailByAlbumIdApi(albumId, cookie);
		} catch (err: any) {
			if (err.response.status === 404) {
				console.log(err.response.data.message);
			}
			return null;
		}
		if (res && res.data.code === 200) {
			return res.data.album;
		}
		return null;
	}

	async spiderMusic(
		cookie: string,
		playlistSize = 10,
		playlistOffset = 0,
		pageSize = 10,
		offset = 0,
		targetOffset = 50,
		category: string
	) {
		let res = [];
		try {
			for (offset; offset <= targetOffset; offset += pageSize) {
				// await sleep(200);
				console.log(offset, targetOffset);
				res.push(
					await this.spider(
						cookie,
						playlistSize,
						playlistOffset,
						pageSize,
						offset,
						category
					)
				);
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
		playlistSize = 10,
		playlistOffset = 0,
		pageSize = 10,
		offset = 0,
		category: string,
		time = 0
	) {
		console.log(offset, pageSize, category);

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
		await createDirs(musicHome, [
			'musics',
			'album-pictures',
			'playlist-pictures',
			'singer-pictures'
		]);

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
			// try {
			// } catch (err) {
			// 	console.error('错误：' + err);
			// 	continue;
			// }
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
			let playlists = await this.getPlaylistByCatName(cat.name, cookie);
			// console.log(playlist);
			// playlist = playlist.slice(playlist.length - 1);
			// console.log(playlist);
			// await sleep(1000000);
			// console.log(playlist);

			// 3、根据歌单id获取歌曲列表
			// let catImgPath = '';
			// let catImgName = '';
			for (
				let i = playlistOffset;
				i <= playlistSize && i < playlists.length;
				i++
			) {
				let playlistItem = playlists[i];
				if (!playlistItem.coverImgUrl) {
					continue;
				}
				let playlistImgPath = '';
				let playlistImgName = '';
				// for (let playlistItem of playlists) {
				musicItem[cat.name]['categoryInfo'] = playlistItem;
				playlistImgPath = musicHome + 'playlist-pictures';
				playlistImgName =
					playlistItem.id + getResourceType(playlistItem.coverImgUrl);
				let playlistImgExisted = await isFileExisted(
					playlistImgPath,
					playlistImgName
				);
				if (!playlistImgExisted) {
					if (!playlistItem.coverImgUrl) {
						console.log('url不正确：' + playlistItem.coverImgUrl);
						continue;
					}
					await sleep(time);
					let isPlaylistImgCreated = await downloadFile(
						playlistItem.coverImgUrl,
						playlistImgPath,
						playlistImgName
					);
					if (!isPlaylistImgCreated) {
						console.log('创建歌单图片失败：' + playlistItem.name);
						continue;
					}
					// try {
					// } catch (err) {
					// 	console.error('错误：' + err);
					// 	// continue;
					// 	return;
					// }
					// try {
					// } catch (err) {
					// 	console.error('错误：' + err);
					// 	// continue;
					// 	return;
					// }
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
					console.log('开始爬取：' + songItem.name + '-' + songItem.id);
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
					let musicLeftName = songDetail.id;
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
					let albumPicturePath = musicHome + 'album-pictures';
					// let albumDetail = await this.getAlbumDetailByAlbumId(
					// 	songDetail.al.id,
					// 	cookie
					// );
					let albumDetail: any;
					if (!songDetail.al.id) {
						albumDetail = {
							id: 1,
							name: '未知专辑',
							picUrl: 'G:/box-music/other-files/未知专辑.jpg',
							description: '未知专辑'
						};
					} else {
						albumDetail = await this.getAlbumDetailByAlbumId(
							songDetail.al.id,
							cookie
						);
					}
					let albumImgName =
						albumDetail.id + getResourceType(albumDetail.picUrl);
					let isAlbumImgExisted = await isFileExisted(
						albumPicturePath,
						albumImgName
					);
					if (!isAlbumImgExisted) {
						// let isPictureDirCreated = await mkdir(musicHome, 'music-pictures');
						// if (!isPictureDirCreated) {
						// 	console.log('创建文件夹失败：' + 'music-pictures');
						// 	continue;
						// }
						if (!albumDetail.picUrl) {
							console.log('url不正确：' + albumDetail.picUrl);
							continue;
						}
						await sleep(time);
						let isAlbumImgCreated = await downloadFile(
							albumDetail.picUrl,
							albumPicturePath,
							albumImgName
						);
						if (!isAlbumImgCreated) {
							console.log('创建专辑图片失败：' + albumImgName);
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
						songAlbum: albumDetail,
						songInfo: {
							categoryId: playlistItem.id,
							category: cat.name,
							playlistName: playlistItem.name
						}
					});

					try {
						await seq.transaction(async (t: any) => {
							let [categoryInfo] = await Category.findOrCreate({
								where: {
									category_name: getRightFileName(cat.name)
								},
								defaults: {
									category_name: getRightFileName(cat.name),
									category_type: (cat as any).category
								},
								transaction: t
							});
							await Playlist.findOrCreate({
								where: {
									playlist_id: playlistItem.id
								},
								defaults: {
									// playlist_id: playlistItem.id,
									playlist_name: playlistItem.name,
									playlist_pic: playlistImgName,
									playlist_description: playlistItem.description ?? null,
									opened: 1,
									user_id: 2
								},
								transaction: t
							});
							await PlaylistCategory.findOrCreate({
								where: {
									playlist_id: playlistItem.id,
									category_id: (categoryInfo as any).category_id
								},
								defaults: {
									playlist_id: playlistItem.id,
									category_id: (categoryInfo as any).category_id
								},
								transaction: t
							});
							await Album.findOrCreate({
								where: {
									album_id: albumDetail.id
								},
								defaults: {
									album_name: albumDetail.name,
									album_pic: albumImgName,
									album_description:
										albumDetail.description ?? albumDetail.briefDesc ?? null
								},
								transaction: t
							});
							await Music.findOrCreate({
								where: {
									music_id: songDetail.id
								},
								defaults: {
									music_title: getRightFileName(songDetail.name),
									lyric: songLyric.lrc.lyric,
									album_id: albumDetail.id,
									duration: songDetail.dt / 1000,
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
							await MusicPlaylist.findOrCreate({
								where: {
									music_id: songDetail.id,
									playlist_id: playlistItem.id
								},
								defaults: {
									music_id: songDetail.id,
									playlist_id: playlistItem.id
								},
								transaction: t
							});
							// let [categoryInfo] = await Category.findOrCreate({
							// 	where: {
							// 		category_name: getRightFileName(cat.name)
							// 	},
							// 	defaults: {
							// 		category_name: getRightFileName(cat.name)
							// 	},
							// 	transaction: t
							// });
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
									singerInfo.artist.id +
									getResourceType(singerInfo.artist.cover);
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
										// singer_id: singerInfo.artist.id,
										singer_name: getRightFileName(singerInfo.artist.name),
										singer_pic: singerName,
										singer_description: singerInfo.artist.briefDesc,
										total_views: 0,
										deleted: 0
									},
									transaction: t
								});
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
							// for (let singerInfo of songSingers) {
							// 	await MusicSinger.findOrCreate({
							// 		where: {
							// 			music_id: songDetail.id,
							// 			singer_id: singerInfo.artist.id
							// 		},
							// 		defaults: {
							// 			music_id: songDetail.id,
							// 			singer_id: singerInfo.artist.id
							// 		},
							// 		transaction: t
							// 	});
							// }
						});
					} catch (err) {
						console.error('错误：' + err);
						// continue;
						return;
					}
				}
			}
			allSongs.push(musicItem);
		}
		// console.log(allSongs);
		return allSongs;
	}
}

const createDirs = async (path: string, dirnames: string[]) => {
	for (let i = 0; i < dirnames.length; i++) {
		const res = await mkdirSavely(path, dirnames[i]);
		if (!res) {
			continue;
		}
	}
};

export const musicService = new MusicService();
