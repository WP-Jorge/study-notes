// 1.在需要导出的地方前面添加 export default 进行默认导出
// export default class User {
// 	static render() {
// 		return 'user static render'
// 	}
// }

// 2.使用as 赋值别名的方式进行默认导出
class User {
	static render() {
		return 'user static render'
	}
}
export { User as default }
