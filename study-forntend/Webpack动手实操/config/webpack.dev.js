const resolveApp = require('./paths');

module.exports = {
	mode: 'development',
	devtool: 'cheap-module-source-map',
	target: 'web',
	devServer: {
		port: 8080,
		hot: 'only',
		open: false,
		static: {
			directory: resolveApp('public')
		},
		compress: true,
		client: {
			overlay: {
				errors: false,
				warnings: false
			},
			progress: false,
			reconnect: true
		},
		proxy: {
			'/api': {
				target: 'https://apimusic.postgraduate.top',
				pathRewrite: {'^/api': ''},
				changeOrigin: true
			}
		}
	}
};