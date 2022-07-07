import { DataTypes } from 'sequelize';

import { seq } from '../database/sequelize';

export const Album = seq.define(
	'album',
	{
		album_id: {
			type: DataTypes.BIGINT,
			allowNull: false,
			autoIncrement: true,
			primaryKey: true,
			unique: true,
			comment: '专辑id'
		},
		album_name: {
			type: DataTypes.STRING,
			allowNull: false,
			comment: '专辑名称'
		},
		album_pic: {
			type: DataTypes.STRING,
			comment: '专辑图片'
		},
		album_description: {
			type: DataTypes.TEXT,
			comment: '专辑介绍'
		},
		total_views: {
			type: DataTypes.INTEGER,
			comment: '点击量'
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
