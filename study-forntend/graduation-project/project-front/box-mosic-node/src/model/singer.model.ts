import { DataTypes } from 'sequelize';

import { seq } from '../database/sequelize';

export const Singer = seq.define(
	'singer',
	{
		singer_id: {
			type: DataTypes.BIGINT,
			allowNull: false,
			autoIncrement: true,
			primaryKey: true,
			unique: true,
			comment: '歌手id'
		},
		singer_name: {
			type: DataTypes.STRING,
			allowNull: false,
			comment: '歌手名称'
		},
		singer_pic: {
			type: DataTypes.STRING,
			comment: '歌手图片'
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
