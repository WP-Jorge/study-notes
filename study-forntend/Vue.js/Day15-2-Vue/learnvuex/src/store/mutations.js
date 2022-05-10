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
	[INCREMENT](state) {
		state.counter++
	},
	[DECREMENT](state) {
		state.counter--
	},
	[INCREMENTCOUNT](state, payload) {
		state.counter += payload.count
	},
	[ADDSTUDENT](state, stu) {
		state.students.push(stu)
	},
	[UPDATEINFO](state) {
		state.info.age = 100
	}
	// 添加在对象中的数据不是响应式的state.students.sex = '男' // 新版本可以这样响应式
	// 如果旧版本想让他变成响应式，使用 Vue.set(state.students, sex, '男') // 将sex = '男' 添加到响应式系统里面
}
