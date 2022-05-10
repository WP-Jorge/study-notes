const path = require('path');
const ffmpegPath = require('ffmpeg-static-electron');
const ffprobePath = require('ffprobe-static-electron');
const ffmpeg = require('fluent-ffmpeg');

ffmpeg.setFfmpegPath(ffmpegPath.path);
ffmpeg.setFfprobePath(ffprobePath.path);

function writeLyric(filePath, fileName, lyric) {
	return Promise((resolve, reject) => {
		let fullPath = path.join(filePath, fileName);
		ffmpeg(fullPath)
			.addOption('-metadata', `lyric=${lyric}`)
			.on('end', function () {
				console.log('歌词写入完成：' + fileName);
				resolve(true);
			})
			.on('error', err => {
				console.log('歌词写入失败：' + err.message);
				resolve(false);
			})
			.save(fullPath);
	});
}

function writeMusicInfo(
	musicUrl, 
	filePath,
	fileName,
	musicInfo = {
		album: '',
		artists: [],
		genre: '',
		title: ''
	}
) {
	return new Promise(resolve => {
		let fullPath = path.join(filePath, fileName);
		console.log('开始写入音乐信息：' + fileName);
		ffmpeg(musicUrl)
			.addOption('-metadata', `album=${musicInfo.album}`)
			.addOption('-metadata', `artist=${musicInfo.artists.join(', ')}`)
			.addOption('-metadata', `genre=${musicInfo.genre}`)
			.addOption('-metadata', `title=${musicInfo.title}`)
			.on('end', function () {
				console.log('音乐信息写入完成：' + fileName);
				resolve(true);
			})
			.on('error', err => {
				console.log('音乐信息写入失败：' + err.message);
				resolve(false);
			})
			.save(fullPath);
	});
}

module.exports = {
	writeLyric,
	writeMusicInfo
};
