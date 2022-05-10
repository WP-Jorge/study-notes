<template>
	<div id="app">
		<h2>---------App内容--------</h2>
		<h2>{{message}}</h2>
		
		<h2>---------App内容：state相关信息--------</h2>
		<p>{{$store.state.counter}}</p>
		<!-- 创建新的方法 -->
		<button @click="substraction">-</button>
		<button @click="addition">+</button>
		<button @click="addCount(5)">+5</button>
		<button @click="addCount(10)">+10</button>
		<button @click="addStudent">添加学生</button>
		<button @click="updateInfo">修改信息</button>
		
		<h2>---------App内容：getters相关信息--------</h2>
		<h2>{{$store.getters.powerCounter}}</h2>
		<h2>年龄>20的人：{{$store.getters.overTwenty}}</h2>
		<h2>人数：{{$store.getters.overTwentyLength}}</h2>
		<h2>年龄>某某的人：{{$store.getters.overAge(20)}}</h2>
		<h2>info：{{$store.state.info}}</h2>
		
		<h2>---------HelloVuex内容--------</h2>
		<hello-vuex></hello-vuex>
		
		<h2>---------App内容：modules中的信息--------</h2>
		<!-- 引用modules中的内容 -->
		<button @click="updateName">修改信息</button>
		<h2>{{$store.state.a.name}}</h2>
		<h2>{{$store.getters.fullName}}</h2>
		<h2>{{$store.getters.fullName2}}</h2>
		<h2>{{$store.getters.fullName3}}</h2>
		<button @click="asycUppdateName">异步修改信息</button>
	</div>
</template>

<script>
	import HelloVuex from './components/HelloVuex.vue'
	// 导入类型常量
	import {
		INCREMENT,
		DECREMENT,
		INCREMENTCOUNT,
		ADDSTUDENT,
		AUPPDATEINFO,
		UPDATEINFO,
		AUPPDATENAME
	} from './store/mutations-types.js'
	export default {
		name: 'App',
		data() {
			return {
				message: '我是App组件',
			}
		},
		components: {
			HelloVuex
		},
		methods: {
			// 在创建的方法里面调用this.$store.commit('mutations里面的方法') 实现监听每步操作
			addition() {
				// 使用类型常量
				this.$store.commit(INCREMENT)
			},
			substraction() {
				this.$store.commit(DECREMENT)
			},
			addCount(count) {
				// 传参  payload :负载
				// 1.普通的提交风格
				// this.$store.commit(INCREMENTCOUNT, count)
				
				// 2.特殊的提交风格
				this.$store.commit({
					type: INCREMENTCOUNT,
					// count: count 简写
					// count
					count
				})
			},
			addStudent() {
				const stu = {id: 114, name: 'orange', age: 23}
				this.$store.commit(ADDSTUDENT, stu)
			},
			updateInfo() {
				// 1.第一种
				// this.$store.commit(UPDATEINFO)
				
				// 2.第二种
				// this.$store.dispatch(AUPPDATEINFO,{
				// 	message: '我是携带的信息',
				// 	success: () => {
				// 		console.log('完成了')
				// 	}
				// })
				
				// 3.第三种
				this.$store.dispatch(AUPPDATEINFO, '我是携带的信息').then(res => console.log(res)).catch(err => console.log(err))
			},
			updateName() {
				// 定义在modules里的使用方式与放在外面的一样
				this.$store.commit(UPDATENAME, '大懒蕉')
			},
			asycUppdateName() {
				this.$store.dispatch(AUPPDATENAME)
			}
		}
	}
</script>

<style>

</style>
