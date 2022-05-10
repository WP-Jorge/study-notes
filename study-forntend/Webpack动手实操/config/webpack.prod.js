const CopyWebpackPligin = require('copy-webpack-plugin');

module.exports = {
	mode: 'production',
	plugins: [
		new CopyWebpackPligin({
			patterns: [
				{
					from: 'public',
					globOptions: {
						ignore: ['**/index.html']
					}
				}
			]
		})
	]
};