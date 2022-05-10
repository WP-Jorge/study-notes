import {
	INCREMENT,
	DECREMENT,
	INCREMENTCOUNT,
	ADDSTUDENT,
	AUPPDATEINFO,
	UPDATEINFO,
	UPDATENAME,
	AUPPDATENAME
} from '../mutations-types.js'

export default {
	state: {
		name: '懒蕉'
	},
	mutations: {
		[UPDATENAME](state, payload) {
			state.name = payload
		}
	},
	// 这里的context只表示这个modules中的mutations
	actions: {
		[AUPPDATENAME](context) {
			setTimeout(() => {
				context.commit(UPDATENAME, '巨型懒蕉')
			}, 1000)
		}
	},
	getters: {
		fullName(state) {
			return state.name + '111'
		},
		fullName2(state, getters) {
			return getters.fullName + '222'
		},
		// 在模块中的第三个参数是rootState，表示根下的state
		fullName3(state, getters, rootState) {
			return getters.fullName2 + rootState.counter
		}
	}
}