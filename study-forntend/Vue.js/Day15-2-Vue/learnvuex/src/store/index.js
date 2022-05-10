// 1.导入相关内容
import Vue from 'vue'
import Vuex from 'vuex'
import mutations from './mutations.js'
import actions from './actions.js'
import getters from './getters.js'
import moduleA from './modules/moduleA.js'

// 导入mutations-types
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

// 2.安装插件
Vue.use(Vuex)

// 3.创建对象

const state = {
	counter: 100,
	students: [{
			id: 110,
			name: 'red',
			age: 18
		},
		{
			id: 111,
			name: 'blue',
			age: 25
		},
		{
			id: 112,
			name: 'green',
			age: 21
		},
		{
			id: 113,
			name: 'pink',
			age: 20
		}
	],
	info: {
		id: 114,
		name: 'yellow',
		age: 18
	}
}

// 3.1 创建store 
const store = new Vuex.Store({
	// 保存共享状态
	state,
	// 方法,改state一定要用mutations改
	// 里面不要放异步操作
	mutations,
	// 定义异步操作
	actions,
	// 类似计算属性computed
	getters,
	// 里面可以定义模块
	modules: {
		a: moduleA
		// b :{
		// 	state: {},
		// 	mutations: {},
		// 	actions: {},
		// 	getters: {}
		// },
		// c :{
		// 	state: {},
		// 	mutations: {},
		// 	actions: {},
		// 	getters: {}
		// }
	}
})

// 4.导出store
export default store
