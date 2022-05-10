module.exports = {
	// 设置我们的运行环境为浏览器 + es2021 + node ,否则eslint在遇到 Promise，window等全局对象时会报错
	env: {
		browser: true,
		es2021: true,
		node: true,
		// 开启setup语法糖环境
		'vue/setup-compiler-macros': true
	},
	// 继承eslint推荐的规则集，vue基本的规则集，typescript的规则集
	extends: ['plugin:vue/vue3-essential', 'eslint:recommended', 'plugin:vue/vue3-recommended', 'plugin:@typescript-eslint/recommended', 'plugin:prettier/recommended'],
	// 支持ts的最新语法
	// 新增，解析vue文件
	parser: 'vue-eslint-parser',
	parserOptions: {
		ecmaVersion: 'latest',
		parser: '@typescript-eslint/parser'
	},
	// 添加vue和@typescript-eslint插件，增强eslint的能力
	plugins: ['vue', '@typescript-eslint'],
	rules: {
		'vue/require-default-prop': 'off',
		'@typescript-eslint/no-var-requires': 0,
		'no-console': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
		'no-debugger': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
		'@typescript-eslint/no-explicit-any': ['off'],
		'prefer-const': 'off',
		'@typescript-eslint/no-unused-vars': 0,
		'vue/multi-word-component-names': 0,
		'prettier/prettier': [
			'warn',
			{
				useTabs: true,
				tabWith: 4,
				singleQuote: true,
				semi: true,
				trailingComma: 'none',
				printWidth: 800,
				endOfLine: 'auto',
				'arrow-parens': 'as-needed'
			}
		]
	}
};
