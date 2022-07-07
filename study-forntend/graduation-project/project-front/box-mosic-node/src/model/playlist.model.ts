import { DataTypes } from 'sequelize';

import { seq } from '../database/sequelize';

export const Playlist = seq.define(
	'playlist',
	{
		playlist_id: {
			type: DataTypes.BIGINT,
			allowNull: false,
			autoIncrement: true,
			primaryKey: true,
			unique: true,
			comment: '歌单id'
		},
		playlist_name: {
			type: DataTypes.STRING,
			allowNull: false,
			comment: '歌单名称'
		},
		playlist_pic: {
			type: DataTypes.STRING,
			comment: '歌单图片'
		},
		playlist_description: {
			type: DataTypes.TEXT,
			comment: '歌单描述'
		},
		opened: {
			type: DataTypes.INTEGER,
			comment: '是否公开'
		},
		user_id: {
			type: DataTypes.BIGINT,
			comment: '所属用户'
		},
		total_views: {
			type: DataTypes.INTEGER,
			comment: '播放量'
		},
		create_time: {
			type: DataTypes.DATE,
			comment: '创建时间'
		},
		update_time: {
			type: DataTypes.DATE,
			comment: '更新时间'
		}
	},
	{
		freezeTableName: true,
		timestamps: false
	}
);
