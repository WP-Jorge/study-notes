// 所有Node.js API都可以在预加载过程中使用。
// 它拥有与Chrome扩展一样的沙盒。
const ffmpegPath = require('@ffmpeg-installer/ffmpeg');
const ffprobePath = require('@ffprobe-installer/ffprobe');
const ffmpeg = require('fluent-ffmpeg');

import './apis';
window.addEventListener('DOMContentLoaded', () => {
	// 配置 ffmpeg 路径
	// asar 打包后路径有所变化
	if (process.env.NODE_ENV !== 'development') {
		ffmpeg.setFfmpegPath(
			ffmpegPath.path.replace('app.asar', 'app.asar.unpacked')
		);
		ffmpeg.setFfprobePath(
			ffprobePath.path.replace('app.asar', 'app.asar.unpacked')
		);
	} else {
		ffmpeg.setFfmpegPath(ffmpegPath.path);
		ffmpeg.setFfprobePath(ffprobePath.path);
	}
});
