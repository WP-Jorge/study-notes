const webpack = require('webpack');
const path = require('path');
const AutoImport = require('unplugin-auto-import/webpack');
const Components = require('unplugin-vue-components/webpack');
const { ElementPlusResolver } = require('unplugin-vue-components/resolvers')

module.exports = {
	pluginOptions: {
		electronBuilder: {
			nodeIntegration: true,
			builderOptions: {
				nsis: {
					oneClick: false, // 是否一键安装
					allowToChangeInstallationDirectory: true // 允许修改安装目录
				},
				productName: 'Box Music',
				extraResources: [
					{
						from: './extraResources/',
						to: 'node_modules/'
					}
				]
			}
		}
	},
	configureWebpack: {
		resolve: {
			alias: {
				'@': path.resolve(__dirname, 'src'),
				components: path.resolve(__dirname, 'src/components'),
				assets: path.resolve(__dirname, 'src/assets'),
				views: path.resolve(__dirname, 'src/views'),
				utils: path.resolve(__dirname, 'src/utils'),
				networks: path.resolve(__dirname, 'src/networks'),
				configs: path.resolve(__dirname, 'src/configs'),
				globals: path.resolve(__dirname, 'src/global')
			}
		},
		plugins: [
			new webpack.DefinePlugin({
				'process.env.FLUENTFFMPEG_COV': false
			}),
			AutoImport({
				resolvers: [ElementPlusResolver()]
			}),
			Components({
				resolvers: [ElementPlusResolver()]
			})
		],
		externals: {
			'ffmpeg-static-electron': 'commonjs2 ffmpeg-static-electron',
			'ffprobe-static-electron': 'commonjs2 ffprobe-static-electron'
		}
	}
};
