const webpack = require('webpack');

module.exports = {
	pluginOptions: {
		electronBuilder: {
			nodeIntegration: true,
			builderOptions: {
				nsis: {
					oneClick: false, // 是否一键安装
					allowToChangeInstallationDirectory: true // 允许修改安装目录
				},
				productName: 'My-Music'
			}
		}
	},
	configureWebpack: {
		plugins: [
			new webpack.DefinePlugin({
				'process.env.FLUENTFFMPEG_COV': false
			})
		],
		externals: {
			'ffmpeg-static-electron': 'commonjs2 ffmpeg-static-electron',
			'ffprobe-static-electron': 'commonjs2 ffprobe-static-electron'
		}
	}
};
