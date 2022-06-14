import { DataTypes } from 'sequelize';

import { seq } from '../database/sequelize';

export const MusicCategory = seq.define(
	'music_category',
	{
		music_category_id: {
			type: DataTypes.BIGINT,
			allowNull: false,
			autoIncrement: true,
			primaryKey: true,
			unique: true,
			comment: '音乐分类id'
		},
		music_id: {
			type: DataTypes.BIGINT,
			allowNull: false,
			comment: '音乐id'
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
