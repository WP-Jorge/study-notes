const ffmpegPath = require('@ffmpeg-installer/ffmpeg');
const ffprobePath = require('@ffprobe-installer/ffprobe');
const ffmpeg = require('fluent-ffmpeg');

// asar打包后路径有所变化
// if (process.env.NODE_ENV !== 'development') {
// 	console.log(ffmpegPath.path.replace('app.asar', 'app.asar.unpacked'));
// 	ffmpeg.setFfmpegPath(ffmpegPath.path.replace('app.asar', 'app.asar.unpacked'));
// 	ffmpeg.setFfprobePath(ffprobePath.path.replace('app.asar', 'app.asar.unpacked'));
// } else {
// 	ffmpeg.setFfmpegPath(ffmpegPath.path);
// 	ffmpeg.setFfprobePath(ffprobePath.path);
// }

ffmpeg.setFfmpegPath(ffmpegPath.path);
ffmpeg.setFfprobePath(ffprobePath.path);

ffmpeg.ffprobe('D:/音乐/漫长的告白-双笙-162283619.flac', function (err, metadata) {
	console.log(err);
	console.dir(metadata);
});
