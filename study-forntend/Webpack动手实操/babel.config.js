module.exports = {
	presets: [
		[
			'@babel/preset-env',
			{
				/**
				 * 1.useBuiltIns
				 * 	false: 不对当前的JS进行处理做 polyfill 的填充
				 * 	usage: 根据自己的需求选择性的进行 polyfill 的填充
				 *  entry: 相当于没写，通过 browserslist 中所定义的兼容浏览器来进行转换
				 * 2.corejs
				 * 	如果使用 useBuiltIns: usage，则需要指定 corejs 版本号为3，因为我们安装的是3版本，webpack 默认使用2版本，
				 * 同时在 JS 的 loader 上排除 node_modules 目录下的文件，防止对里面的 JS 进行编译转换
				 * 	corejs: 3
				 */
				useBuiltIns: 'usage',
				corejs: 3,
				// targets: 'chrome 91'
			}
		],
		/**
		 * npm i @babel/preset-typescript -D
		 * 安装 babel 的 typescript 预设，对 ts 代码进行编译
		 */
		['@babel/preset-typescript']
	]

	// presets: [
	// 	[
	// 		'@babel/preset-env',
	// 		/**
	// 		 * 优先以这里的配置为准
	// 		 */
	// 		{
	// 			targets: 'chrome 91'
	// 		}
	// 	]
	// ]
}