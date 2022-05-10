let axios = require('axios');
let fs = require('fs');
let path = require('path');
let write = require('./write');

(async function () {
	// 基本配置
	let baseSarchOptions = {
		type: 'search', // 搜索音乐
		count: 3, // 每次搜索音乐数量
		pages: 1, // 页数
		source: 'kugou' // 音乐来源，备选：网易云音乐：netease, 酷狗音乐：kugou
	};
	// 获取热搜词
	let hotWords = await getHotWord();
	// 获取搜索配置
	let searchOptions = getSearchOptions(baseSarchOptions, hotWords);
	let musicList = [];
	// 通过搜索配置批量获取音乐
	for (let item of searchOptions) {
		let musicInfo = await getMusicInfo(item);
		musicList.push(musicInfo);
	}
	// 将音乐信息写入json
	write.fsWrite(`./music/allMusic/全部音乐.json`, JSON.stringify(musicList, null, 4));
	// console.dir(musicList);
})();

// 获取url
function getUrl(options) {
	if (options.type === 'search') {
		return encodeURI(
			`http://www.abkabk.com/tools/T/mkplayer/api.php?types=${options.type}&count=${options.count}&source=${options.source}&pages=${options.pages}&name=${options.name}`
		);
	}
	return `http://www.abkabk.com/tools/T/mkplayer/api.php?types=${options.type}&id=${options.id}&source=${options.source}`;
}

// 获取热门搜索词(后期通过爬虫从网上爬取热门搜索词)
async function getHotWord() {
	return ['双笙', '陈元汐', '小曲儿', '古风', '小满'];
}

// 获取批量搜索配置
function getSearchOptions(options, hotWords) {
	let searchOptions = [];
	hotWords.forEach((item, index) => {
		let option = {
			type: options.type,
			count: options.count,
			pages: options.pages,
			name: item,
			source: options.source
		};
		searchOptions.push(option);
	});
	return searchOptions;
}

// 获取音乐信息
async function getMusicInfo(searchOptions) {
	let res = await axios.post(getUrl(searchOptions));
	let musicInfos = {
		keyword: searchOptions.name,
		musics: []
	};
	// await write.fsdirSync(`./music/${searchOptions.name}`);
	// await write.fsdirSync(`./music/resources${searchOptions.name}`);
	for (let item of res.data) {
		let musicInfo = {};
		let picOptions = {
			type: 'pic',
			id: item.pic_id,
			source: item.source
		};
		let lyricOptions = {
			type: 'lyric',
			id: item.lyric_id,
			source: item.source
		};
		let musicOptions = {
			type: 'url',
			id: item.url_id,
			source: item.source
		};
		musicInfo['id'] = item.id;
		musicInfo['name'] = item.name;
		musicInfo['album'] = item.album;
		musicInfo['artist'] = item.artist;
		musicInfo['musicUrl'] = getUrl(musicOptions);
		musicInfo['imgUrl'] = getUrl(picOptions);
		musicInfo['lyricUrl'] = getUrl(lyricOptions);
		musicInfo['source'] = item.source;
		musicInfos['musics'].push(musicInfo);

		for (let info of musicInfos.musics) {
			downMusic('url', info, searchOptions);
			downMusic('lyric', info, searchOptions);
			downMusic('img', info, searchOptions);
		}
		// await write.fsWrite(`./music/${searchOptions.name}/${item.album}-${searchOptions.name}.json`, JSON.stringify(musicInfos, null, 4));
	}
	// console.log(musicInfos);
	return musicInfos;
}

// 音乐下载
async function downMusic(type, musicInfo, searchOptions) {
	// 下载音乐
	if (type === 'url') {
		let musicRes = await axios.post(musicInfo.musicUrl);
		if (musicRes && musicRes.data && musicRes.data.url) {
			downloadFile(
				musicRes.data.url,
				`./music/resources/${searchOptions.name}/${musicInfo.album}/`,
				`${musicInfo.album}-${searchOptions.name}.mp3`
			);
		}
		return;
	}

	// 下载歌词
	if (type === 'lyric') {
		let lyricRes = await axios.post(musicInfo.lyricUrl);
		if (lyricRes && lyricRes.data && lyricRes.data.lyric) {
			write.fsWrite(
				`./music/resources/${searchOptions.name}/${musicInfo.album}/${musicInfo.album}-${searchOptions.name}-歌词.lrc`,
				lyricRes.data.lyric
			);
		}
		return;
	}

	// 下载封面
	if (type === 'img') {
		let imgRes = await axios.post(musicInfo.imgUrl);
		if (imgRes && imgRes.data && imgRes.data.url) {
			downloadFile(
				getImg(imgRes.data.url),
				`./music/resources/${searchOptions.name}/${musicInfo.album}/`,
				`${musicInfo.album}-${searchOptions.name}.jpg`
			);
		}
		return;
	}
}

// 处理封面
function getImg(imgUrl, options) {
	if (!options || !options.width) {
		return imgUrl.split('?')[0];
	}
	let reg = /\?param=\d+y\d+$/
	return imgUrl.replace(reg, `?param=${options.width}y${options.width}`)
}

// 文件下载方法
async function downloadFile(url, filepath, name) {
	await write.fsdirSync(filepath);
	const mypath = path.resolve(filepath, name);
	const writer = fs.createWriteStream(mypath);
	console.log(url);
	const response = await axios({
		url,
		method: 'GET',
		responseType: 'stream'
	});
	response.data.pipe(writer);
	return new Promise((resolve, reject) => {
		writer.on('finish', resolve);
		writer.on('error', reject);
	});
}

// 文件下载
async function download(sources) {
	for (let musics of sources) {
		for (let music of musics) {
			await downloadFile(music.musicUrl);
		}
	}
}
