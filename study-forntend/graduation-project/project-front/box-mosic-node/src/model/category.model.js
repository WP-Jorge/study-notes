const { DataTypes } = require('sequelize');

const seq = require('../database/sequelize');

const Category = seq.define('category', {
	category_id: {
		type: DataTypes.BIGINT,
		allowNull: false,
		autoIncrement: true,
		primaryKey: true,
		unique: true,
		comment: '分类id'
	},
	category_name: {
		type: DataTypes.STRING,
		allowNull: false,
		comment: '分类名称'
	},
	category_pic: {
		type: DataTypes.STRING,
		comment: '分类图片'
	},
	create_time: {
		type: DataTypes.DATE,
		comment: '创建时间'
	},
	update_time: {
		type: DataTypes.DATE,
		comment: '更新时间'
	}
}, {
	freezeTableName: true,
	timestamps: false
});

module.exports = Category;