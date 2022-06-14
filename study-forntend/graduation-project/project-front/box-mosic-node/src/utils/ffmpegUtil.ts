import path from 'path';
const ffmpegPath = require('ffmpeg-static-electron');
const ffprobePath = require('ffprobe-static-electron');
const ffmpeg = require('fluent-ffmpeg');

ffmpeg.setFfmpegPath(ffmpegPath.path);
ffmpeg.setFfprobePath(ffprobePath.path);

function writeLyric(filePath: string, fileName: string, lyric: string) {
	return new Promise(resolve => {
		let fullPath = path.join(filePath, fileName);
		ffmpeg(fullPath)
			.addOption('-metadata', `lyric=${lyric}`)
			.on('end', function () {
				console.log('歌词写入完成：' + fileName);
				resolve(true);
			})
			.on('error', (err: any) => {
				console.log('歌词写入失败：' + err.message);
				resolve(false);
			})
			.save(fullPath);
	});
}

function writeMusicInfo(
	musicUrl: string,
	filePath: string,
	fileName: string
	// musicInfo = {
	// 	album: '',
	// 	artists: [],
	// 	genre: '',
	// 	title: '',
	// 	lyric: ''
	// }
) {
	return new Promise(resolve => {
		let fullPath = path.join(filePath, fileName);
		console.log('开始写入音乐信息：' + fileName);
		ffmpeg(musicUrl)
			// .addOption('-metadata', `album=${musicInfo.album}`)
			// .addOption('-metadata', `artist=${musicInfo.artists.join(', ')}`)
			// .addOption('-metadata', `genre=${musicInfo.genre}`)
			// .addOption('-metadata', `title=${musicInfo.title}`)
			// .addOption('-metadata', `lyric=${formatLyric(musicInfo.lyric)}`)
			.on('end', function () {
				console.log('音乐信息写入完成：' + fileName);
				resolve(true);
			})
			.on('error', (err: any) => {
				console.log('音乐信息写入失败：' + err.message);
				resolve(false);
			})
			.save(fullPath);
	});
}

export { writeLyric, writeMusicInfo };

// mp3
// ffmpeg -i 故梦.mp3 -i 1.jpg -map 0:0 -map 1:0 -c copy -id3v2_version 3 -metadata:s:v title="Album cover" -metadata:s:v comment="Cover (Front)" 故梦1.mp3

// others
// ffmpeg -i 双笙.flac -i 2.jpg -map 0:a -map 1 -codec copy -metadata:s:v title="Album cover" -metadata:s:v comment="Cover (front)" -disposition:v attached_pic 双笙2.flac
