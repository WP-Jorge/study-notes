import { DataTypes } from 'sequelize';

import { seq } from '../database/sequelize';

export const User = seq.define(
	'music',
	{
		music_id: {
			type: DataTypes.BIGINT,
			allowNull: false,
			autoIncrement: true,
			primaryKey: true,
			unique: true,
			comment: '音乐id'
		},
		music_title: {
			type: DataTypes.STRING,
			allowNull: false,
			comment: '音乐名称'
		},
		music_pic: {
			type: DataTypes.STRING,
			comment: '音乐封面'
		},
		lyrics: {
			type: DataTypes.TEXT,
			comment: '歌词'
		},
		album: {
			type: DataTypes.STRING,
			comment: '专辑'
		},
		genre: {
			type: DataTypes.STRING,
			comment: '流派'
		},
		duration: {
			type: DataTypes.DOUBLE,
			allowNull: false,
			comment: '时长'
		},
		size: {
			type: DataTypes.INTEGER,
			comment: '大小'
		},
		level: {
			type: DataTypes.STRING,
			allowNull: false,
			comment: '音乐最高品质'
		},
		music_format: {
			type: DataTypes.STRING,
			allowNull: false,
			comment: '音乐最高品质格式'
		},
		bitrate: {
			type: DataTypes.INTEGER,
			allowNull: false,
			comment: '音乐最高音质码率'
		},
		views: {
			type: DataTypes.INTEGER,
			comment: '播放量'
		},
		music_url: {
			type: DataTypes.STRING,
			allowNull: false,
			comment: '音乐地址'
		},
		create_time: {
			type: DataTypes.DATE,
			comment: '创建时间'
		},
		update_time: {
			type: DataTypes.DATE,
			comment: '更新时间'
		},
		deleted: {
			type: DataTypes.INTEGER,
			comment: '逻辑删除'
		}
	},
	{
		freezeTableName: true,
		timestamps: false
	}
);
