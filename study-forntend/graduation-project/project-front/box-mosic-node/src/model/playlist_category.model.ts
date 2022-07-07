import { DataTypes } from 'sequelize';

import { seq } from '../database/sequelize';

export const PlaylistCategory = seq.define(
	'playlist_category',
	{
		playlist_category_id: {
			type: DataTypes.BIGINT,
			allowNull: false,
			autoIncrement: true,
			primaryKey: true,
			unique: true,
			comment: '歌单分类id'
		},
		playlist_id: {
			type: DataTypes.BIGINT,
			allowNull: false,
			comment: '歌单id'
		},
		category_id: {
			type: DataTypes.BIGINT,
			comment: '分类id'
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
