import { Music } from '@/networks/music';
// import { formatLyric } from './fileUtil';

export const getOutputOPtions = (music: Music) => {
	const artists = music.singers.map(item => item.singerName);
	console.log(artists);
	const artist = artists.join(',');
	if (music.musicFormat === 'flac') {
		return [
			`-i`,
			music.album.albumPic,
			`-metadata`,
			`album=${music.album.albumName}`,
			`-metadata`,
			`artist=${artist}`,
			`-metadata`,
			`genre=box-music`,
			`-metadata`,
			`title=${music.musicTitle}`,
			// `-metadata`,
			// `lyric=${formatLyric(music.lyric as string)}`,
			`-map`,
			`0:a`,
			`-map`,
			`1`,
			`-codec`,
			`copy`,
			`-metadata:s:v`,
			`title="AlbumCover"`,
			`-metadata:s:v`,
			`comment="Cover(front)"`,
			`-disposition:v`,
			`attached_pic`
			// targetPath
		];
	}
	// return [
	// 	// '-i',
	// 	// music.musicUrl,
	// 	'-i',
	// 	music.album.albumPic,
	// 	'-metadata',
	// 	`album=${music.album.albumName}`,
	// 	'-metadata',
	// 	`artist=${artist}`,
	// 	'-metadata',
	// 	'genre=box-music',
	// 	'-metadata',
	// 	`title=${music.musicTitle}`,
	// 	// '-metadata',
	// 	// `lyric=${music.lyric}`,
	// 	'-map',
	// 	'0:a',
	// 	'-map',
	// 	'1',
	// 	'-codec',
	// 	'copy',
	// 	'-metadata:s:v',
	// 	'title="Album cover"',
	// 	'-metadata:s:v',
	// 	'comment="Cover (front)"',
	// 	'-disposition:v',
	// 	'attached_pic'
	// 	// targetPath
	// ];
};
