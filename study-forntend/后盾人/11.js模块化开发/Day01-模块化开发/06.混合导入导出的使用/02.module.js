// 多个导出
// 1.具名导出
// export let site = '后盾人'
let site = '后盾人'

// 2.默认导出
class User {
	static render() {
		return 'user static render'
	}
}
// export { User as default }

// 3.合起来写
export { User as default, site }
