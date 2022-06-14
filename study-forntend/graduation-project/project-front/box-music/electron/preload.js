// 所有Node.js API都可以在预加载过程中使用。
// 它拥有与Chrome扩展一样的沙盒。
// const ffmpegPath = require('ffmpeg-static-electron');
// const ffprobePath = require('ffprobe-static-electron');
const ffmpeg = require('fluent-ffmpeg');
// const fs = require('fs');
const { contextBridge, ipcRenderer } = require('electron');

contextBridge.exposeInMainWorld('ipcRenderer', ipcRenderer);
contextBridge.exposeInMainWorld('electronAPI', {
	isMaximized: () => ipcRenderer.invoke('isMaximized'),
	on: (channel, fn) => {
		ipcRenderer.on(channel, (event, ...args) => fn(...args));
	},
	getMusicInfo: (musicPath, fn) => {
		ffmpeg.ffprobe(musicPath, (err, metadata) => {
			if (err) {
				// console.error(err);
			}
			fn(metadata);
		});
	},
	getMsg: (musicPath, targetPath, onStart, onEnd, onProgress) => {
		ffmpeg(musicPath)
			.on('start', commandLine => {
				onStart('开始下载：' + commandLine);
			})
			.on('end', () => {
				onEnd('下载完成');
			})
			.on('progress', progress => {
				onProgress('下载进度：' + progress.percent);
			})
			.save(targetPath);
	}
});

// fs.readFile('D:/CloudMusic/1.lrc', 'utf-8', (err, data) => {
// 	if (err) {
// 		console.log(err);
// 	} else {
// 		let lyric = data.replace(/\n/g, '<e>').replace(/\s/g, '<b>');
// 		console.log(lyric);
// 	}
// });

window.addEventListener('DOMContentLoaded', () => {
	// 配置 ffmpeg 路径
	// asar 打包后路径有所变化
	// if (process.env.NODE_ENV !== 'development') {
	// 	ffmpeg.setFfmpegPath(
	// 		ffmpegPath.path.replace('app.asar', 'app.asar.unpacked')
	// 	);
	// 	ffmpeg.setFfprobePath(
	// 		ffprobePath.path.replace('app.asar', 'app.asar.unpacked')
	// 	);
	// } else {
	// 	ffmpeg.setFfmpegPath(ffmpegPath.path);
	// 	ffmpeg.setFfprobePath(ffprobePath.path);
	// }
	// const replaceText = (selector, text) => {
	// 	const element = document.getElementById(selector);
	// 	if (element) element.innerText = text;
	// };
	// for (const dependency of ['chrome', 'node', 'electron']) {
	// 	replaceText(`${dependency}-version`, process.versions[dependency]);
	// }
});
