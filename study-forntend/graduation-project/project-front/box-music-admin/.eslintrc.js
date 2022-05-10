module.exports = {
	root: true,
	parser: '@typescript-eslint/parser',
	parserOptions: {
		parser: '@babel/eslint-parser',
		ecmaVersion: 2020,
		sourceType: 'module',
		ecmaFeatures: {
			jsx: true
		}
	},
	settings: {
		react: {
			version: 'detect'
		},
		'import/resolver': {
			node: {
				paths: ['src'],
				extensions: ['.js', '.jsx', '.ts', '.tsx']
			}
		}
	},
	env: {
		browser: true,
		amd: true,
		node: true
	},
	extends: [
		'eslint:recommended',
		'plugin:react/recommended',
		'plugin:jsx-a11y/recommended',
		'plugin:prettier/recommended' // Make sure this is always the last element in the array.
	],
	plugins: ['simple-import-sort', 'prettier', '@typescript-eslint'],
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
				arrowParens: 'avoid'
			}
		]
	}
};
