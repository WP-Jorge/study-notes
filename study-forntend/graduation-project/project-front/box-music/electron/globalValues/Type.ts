import { DownloadItemInfo } from '../main/downloadListener';

export interface Music extends BaseInterface {
	musicId: string;
	musicTitle: string;
	musicUrl: string;
	lyric?: string;
	album: Album;
	duration: number;
	size?: number;
	level?: string;
	musicFormat?: string;
	bitrate?: number;
	totalViews?: number;
	deleted?: number;
	categories?: Category[];
	singers: Singer[];
	downloadItemInfo: DownloadItemInfo;
}

export interface Album extends BaseInterface {
	albumId: string;
	albumName: string;
	albumDescription?: string;
	totalViews?: number;
	albumPic: string;
}

export interface Category extends BaseInterface {
	categoryId: string;
	categoryName: string;
	categoryPic: string;
}

export interface Singer extends BaseInterface {
	singerId?: string;
	singerName: string;
	totalViews: number;
	singerPic: string;
}

export interface BaseInterface {
	[x: string]: any;
}
