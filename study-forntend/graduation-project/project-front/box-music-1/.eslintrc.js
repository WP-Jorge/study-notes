module.exports = {
	// 设置我们的运行环境为浏览器 + es2021 + node ,否则eslint在遇到 Promise，window等全局对象时会报错
	env: {
		browser: true,
		es2021: true,
		node: true,
		'vue/setup-compiler-macros': true
	},
	// 继承eslint推荐的规则集，vue基本的规则集，typescript的规则集
	extends: [
		'eslint:recommended',
		'plugin:vue/vue3-recommended',
		'plugin:@typescript-eslint/recommended',
		'plugin:prettier/recommended',
		'./.eslintrc-auto-import.json'
	],
	parser: 'vue-eslint-parser',
	// 支持ts的最新语法
	parserOptions: {
		ecmaVersion: 'latest',
		parser: '@typescript-eslint/parser'
	},
	// 添加vue和@typescript-eslint插件，增强eslint的能力
	plugins: ['vue', '@typescript-eslint'],
	rules: {
		'no-console': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
		'no-debugger': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
		'@typescript-eslint/no-var-requires': 0,
		'@typescript-eslint/no-unused-vars': 2,
		'no-unused-vars': 0,
		'vue/multi-word-component-names': 0,
		'@typescript-eslint/no-explicit-any': 0,
		'@typescript-eslint/explicit-module-boundary-types': 0,
		'@typescript-eslint/no-non-null-assertion': 0,
		'@typescript-eslint/no-empty-interface': 0,
		'@typescript-eslint/no-non-null-asserted-nullish-coalescing': 0,
		'prefer-const': 'off',
		'jsx-a11y/no-noninteractive-element-interactions': 0,
		'no-irregular-whitespace': 0,
		'prettier/prettier': [
			'error',
			{
				useTabs: true, // 使用 tab 缩进
				tabWith: 4, // tab 长度
				singleQuote: true, // 是否使用单引号
				semi: true, // 是否在语句末尾打印分号
				trailingComma: 'none', // 去除对象最末尾元素跟随的逗号
				printWidth: 80, // 单行长度
				enforceForArrowConditionals: false,
				endOfLine: 'auto',
				arrowParens: 'avoid',
				bracketSameLine: true, // 开始标签的右尖括号是否跟随在最后一行属性末尾
				htmlWhitespaceSensitivity: 'ignore' // 空格敏感
			}
		]
	}
};
