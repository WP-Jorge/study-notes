module.exports = class ResultType {
	static success(message = '获取数据成功', result) {
		return {
			code: 'success',
			message,
			result
		}
	}
	static error(message = '获取数据失败', result = {}) {
		return {
			code: 'error',
			message,
			result
		}
	}
}