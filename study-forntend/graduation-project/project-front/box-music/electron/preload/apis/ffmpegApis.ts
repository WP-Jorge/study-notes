import { Music, Singer } from '../../globalValues/Type';
const ffmpeg = require('fluent-ffmpeg');

const formatLyric = (lyric: string) => {
	return lyric
		.replace(/\r\n/g, '<rn>')
		.replace(/\n/g, '<n>')
		.replace(/\s/g, '<s>');
};

export const getMusicInfo = (
	musicPath: string,
	fn: (musicInfo: any) => any
) => {
	ffmpeg.ffprobe(musicPath, (err: Error, metadata: any) => {
		if (err) {
			console.error(err);
		}
		fn(metadata);
	});
};
export const writeinMusicInfo = (
	musicPath: string,
	targetPath: string,
	// outputOptions: [],
	music: Music,
	onStart: (msg: string) => any,
	onProgress: (msg: any) => any,
	onEnd: (msg: string) => any
) => {
	targetPath += `${music.musicTitle}.${music.musicFormat}`;
	const artists = music.singers
		.map((item: Singer) => item.singerName)
		.join(',');
	if (music.musicFormat?.toLowerCase() === 'flac') {
		ffmpeg(musicPath)
			.addOption('-i', `${music.album.albumPic}`)
			.addOption('-map', `0:a`)
			.addOption('-map', `1`)
			.addOption('-codec', `copy`)
			.addOption('-metadata:s:v', `title="Album cover"`)
			.addOption('-metadata:s:v', `comment="Cover (front)"`)
			.addOption('-disposition:v', `attached_pic`)
			.addOption('-metadata', `album=${music.album.albumName}`)
			.addOption('-metadata', `artist=${artists}`)
			.addOption('-metadata', `genre=box-music`)
			.addOption('-metadata', `title=${music.musicTitle}`)
			.addOption('-metadata', `lyric=${formatLyric(music.lyric as string)}`)
			.on('start', (commandLine: string) => {
				onStart('开始下载：' + commandLine);
			})
			.on('end', () => {
				onEnd('下载完成');
			})
			.on('progress', (progress: any) => {
				onProgress(progress);
			})
			.save(targetPath);
	} else {
		ffmpeg(musicPath)
			.addOption('-i', `${music.album.albumPic}`)
			.addOption('-map', `0:0`)
			.addOption('-map', `1:0`)
			.addOption('-c', `copy`)
			.addOption('-id3v2_version', `3`)
			.addOption('-metadata:s:v', `title="Album cover"`)
			.addOption('-metadata:s:v', `comment="Cover (front)"`)
			.addOption('-metadata', `album=${music.album.albumName}`)
			.addOption('-metadata', `artist=${artists}`)
			.addOption('-metadata', `genre=box-music`)
			.addOption('-metadata', `title=${music.musicTitle}`)
			.addOption('-metadata', `lyric=${formatLyric(music.lyric as string)}`)
			.on('start', (commandLine: string) => {
				onStart('开始下载：' + commandLine);
			})
			.on('end', () => {
				onEnd('下载完成');
			})
			.on('progress', (progress: any) => {
				onProgress(progress);
			})
			.save(targetPath);
	}
};
