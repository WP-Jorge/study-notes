module.exports = {
	root: true,
	env: {
		node: true
	},
	extends: [
		'plugin:vue/vue3-essential',
		'@vue/airbnb'
	],
	parserOptions: {
		parser: 'babel-eslint'
	},
	rules: {
		// 禁止使用末尾逗号
		'comma-dangle': ['error', 'never'],
		// 允许使用 tab 缩进
		'no-tabs': 'off',
		// 使用 tab 缩进
		indent: ['error', 'tab'],
		// 允许 tab 和 空格混用
		'no-mixed-spaces-and-tabs': 'off',
		// 允许使用 const 定义变量
		'prefer-const': 'off',
		// 允许不加后缀
		'import/extensions': ['error', 'always', {
			'js': 'never',
			'vue': 'never'
		}],
		'no-console': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
		'no-debugger': process.env.NODE_ENV === 'production' ? 'warn' : 'off'
	},
	settings: {
		// 允许不加后缀 （读取 webpack 的配置文件来忽略后缀）
		'import/resolver': {
			webpack: {
				config: 'node_modules/@vue/cli-service/webpack.config.js'
			}
		}
	}
};
