import {
	INCREMENT,
	DECREMENT,
	INCREMENTCOUNT,
	ADDSTUDENT,
	AUPPDATEINFO,
	UPDATEINFO,
	UPDATENAME,
	AUPPDATENAME
} from './mutations-types.js'


export default {
	// context: 上下文，理解成store对象
	// [AUPPDATEINFO](context, payload) {
	// 	setTimeout(() => {
	// 		context.commit(UPDATEINFO)
	// 		console.log(payload.message)
	// 		payload.success()
	// 	}, 1000)
	// }
	[AUPPDATEINFO](context, payload) {
		return new Promise((resolve, reject) => {
			setTimeout(() => {
				context.commit(UPDATEINFO)
				console.log(payload)
				resolve('完成了')
				reject('失败了')
			}, 1000)
		})
	}
}
