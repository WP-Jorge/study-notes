// 处理业务逻辑的中间件，读取某个json文件的数据给前端
const path = require('path')
const fileUtils = require('../utils/file_utils')
module.exports = async (ctx, next) => {
	// 根据请求的url读取data的json文件
	const url = ctx.request.url // /appi/seller -> ../data/seller.json
	let filePath = url.replace('/api', '')
	filePath = '../data' + filePath + '.json' // ../data/seller.json
	filePath = path.join(__dirname, filePath) // 将当前文件的绝对路径与filePath拼接
	try{
		const ret = await fileUtils.getFileJsonData(filePath)
		ctx.response.body = ret
	}catch(e){
		const errorMsg = {
			message: '读取文件内容失败，文件资源不存在',
			status: 404
		}
		ctx.response.body = JSON.stringify(errorMsg)
	}
	console.log(filePath)
	await next()
}