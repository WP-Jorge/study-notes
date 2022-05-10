module.exports = {
	plugins: [
		/**
		 * npm i autoprefixer -D
		 * 自动补齐兼容版本的前缀
		 */
		require('autoprefixer'),
		/**
		 * npm i postcss-preset-env
		 * postcss的预设插件集合，对css的兼容，如 #12345678 转换成 rgba(18,52,86,0.47059)
		 */
		require('postcss-preset-env')
	]
};
