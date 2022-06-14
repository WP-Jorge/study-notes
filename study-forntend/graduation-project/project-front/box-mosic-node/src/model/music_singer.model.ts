import { DataTypes } from 'sequelize';

import { seq } from '../database/sequelize';

export const MusicSinger = seq.define(
	'music_singer',
	{
		music_singer_id: {
			type: DataTypes.BIGINT,
			allowNull: false,
			autoIncrement: true,
			primaryKey: true,
			unique: true,
			comment: '音乐歌手id'
		},
		music_id: {
			type: DataTypes.BIGINT,
			allowNull: false,
			comment: '音乐id'
		},
		singer_id: {
			type: DataTypes.BIGINT,
			comment: '歌手id'
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
