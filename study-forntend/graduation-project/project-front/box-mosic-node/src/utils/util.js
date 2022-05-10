const sleep = time => new Promise(resolve => setTimeout(() => resolve(), time));

/**
 * 
 * @param {- 无损：
  - 格式：flac
  - 码率：640+
- 超高品：
  - 格式：mp3
  - 码率：129-640
- 高品：
  - 格式：mp3
  - 码率：65-128
- 流畅：
  - 格式：aac
  - 码率：64-} br 
 */
const getMusicLevel = (br=0) => {
	if (br <= 64000) {
		return '流畅';
	}
	if (br <= 128000) {
		return '高品';
	}
	if (br <= 640000) {
		return '超高品';
	}
	if (br > 640000) {
		return '无损';
	}
};

const isFileNameRight = fileName => {
	let illegalChars = [`"`, '<', '>', '\\', '/', ':', '*', '?', '|'];
	let fileNameList = fileName.split('');
	return fileNameList.filter(item => illegalChars.includes(item)).length === 0;
};

const getRightFileName = fileName => {
	return fileName.replace(/["|<|>|\\|\/|:|\*|\?|\|]/g, ' ');
};

module.exports = {
	sleep,
	getMusicLevel,
	isFileNameRight,
	getRightFileName
};
