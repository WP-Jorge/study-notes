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
	powerCounter(state) {
		return state.counter * state.counter
	},
	overTwenty(state) {
		return state.students.filter(s => s.age > 20)
	},
	// 第二个参数是这个getters,可以在计算属性中调用getters里面的计算属性
	overTwentyLength(state, getters) {
		return getters.overTwenty.length
	},
	overAge(state) {
		return function(age) {
			return state.students.filter(s => s.age > age)
		}
	}
}
