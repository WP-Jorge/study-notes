### 配置

```json
{
	// vscode基本设置
	"workbench.tree.indent": 24,
	"workbench.statusBar.visible": true,
	"workbench.activityBar.visible": true,
	"editor.minimap.enabled": true,
	"editor.insertSpaces": false,
	"liveServer.settings.donotShowInfoMsg": true,
	"liveServer.settings.CustomBrowser": "microsoft-edge",
	"editor.formatOnSave": false, // 开启保存自动格式化
	"editor.autoClosingBrackets": "always",
	"typescript.disableAutomaticTypeAcquisition": true,
	"editor.suggest.snippetsPreventQuickSuggestions": false,
	"workbench.colorTheme": "GitHub Light",
	"explorer.confirmDelete": true,
	// 格式化设置
	"eslint.validate": ["javascript", "javascriptreact", "vue"],
	"[javascript]": {
		"editor.defaultFormatter": "esbenp.prettier-vscode"
	},
	"[vue]": {
		"editor.defaultFormatter": "octref.vetur"
	},
	"[jsonc]": {
		"editor.defaultFormatter": "esbenp.prettier-vscode"
	},
	"[html]": {
		"editor.defaultFormatter": "esbenp.prettier-vscode"
	},
	"[css]": {
		"editor.defaultFormatter": "esbenp.prettier-vscode"
	},
	// 默认使用prettier格式化支持的文件
	"vetur.format.defaultFormatter.js": "prettier",
	"vetur.format.defaultFormatter.html": "prettyhtml",
	"vetur.format.defaultFormatterOptions": {
		"prettier": {
			// 结尾无分号
			"semi": false,
			// 超过140个字符换行
			"printWidth": 140,
			// 使用单引号
			"singleQuote": true,
			// 无尾随逗号
			"trailingComma": "none",
			// 箭头函数单个参数不加分号
			"arrowParens": "avoid"
		},
		"prettyhtml": {
			"printWidth": 140
		}
	},
	// 同上prettier格式化代码
	"prettier.semi": true,
	"prettier.printWidth": 140,
	"prettier.trailingComma": "none",
	"prettier.singleQuote": true,
	"prettier.arrowParens": "avoid",
	"prettier.tabWidth": 4,
	"files.associations": {
		"*.cjson": "jsonc",
		"*.wxss": "css",
		"*.wxs": "javascript"
	},
	"git.enableSmartCommit": true,
	"vetur.format.options.tabSize": 4,
	"editor.detectIndentation": false,
	"editor.tabSize": 4,
	"prettier.useTabs": true,
	// leetcode配置
	"leetcode.workspaceFolder": "C:\\Users\\蛋丁\\.leetcode",
	"leetcode.hint.configWebviewMarkdown": false,
	"leetcode.hint.commentDescription": false,
	"leetcode.defaultLanguage": "javascript",
	"leetcode.endpoint": "leetcode-cn",
	"vscodeGoogleTranslate.preferredLanguage": "Chinese (Simplified)"
}
```
