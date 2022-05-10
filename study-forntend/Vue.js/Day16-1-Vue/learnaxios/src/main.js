import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
	el: '#app',
	router,
	render: h => h(App),
	mode: 'hitory'
})

// 教学示例

// 1.简单发送请求
// axios({
// 	url: 'http://123.207.32.32:8000/home/multidata',
// }).then(res => {
// 	console.log(res)
// })

// 2.传参后发送请求
// axios({
// 	// url: 'http://123.207.32.32:8000/home/data?type=sell&page=3',
// 	// 也可以这样 专门针对get请求的参数拼接
// 	url: 'http://123.207.32.32:8000/home/data',
// 	parmas: {
// 		type: 'pop',
// 		page: 1
// 	}
// }).then(res => {
// 	console.log(res)
// })

// 3.发送并发请求
// axios.all([axios({
// 	url: 'http://123.207.32.32:8000/home/multidata'
// }), axios({
// 	url: 'http://123.207.32.32:8000/home/data',
// 	params: {
// 		type: 'sell',
// 		page: 5
// 	}
// })]).then(results => {
// 	console.log(results)
// })

// 3.发送并发请求.then的其他写法
// axios.all([axios({
// 	url: 'http://123.207.32.32:8000/home/multidata'
// }), axios({
// 	url: 'http://123.207.32.32:8000/home/data',
// 	params: {
// 		type: 'sell',
// 		page: 5
// 	}
// })]).then(axios.spread((res1, res2) => {
// 	console.log(res1)
// 	console.log(res2)
// }))

// 4.post请求
// 4.1.axios
// axios({
// 	method: 'post',
// 	url: 'http://123.207.32.32:8000/home/data',
// 	data: {
// 		// post请求在data里面放传输数据
// 	}
// }).then(res => {
// 	console.log(res)
// })

// 4.2.axios.post
// axios.post('http://123.207.32.32:8000/home/data', {
//     // 在这里传参
//   })
//   .then(function (response) {
//     console.log(response);
//   })
//   .catch(function (error) {
//     console.log(error);
//   });

// 5.全局配置
// axios.defaults.baseURL = 'http://123.207.32.32:8000/'
// axios.defaults.timeout = 3000

// axios.all([axios({
// 	// baseURL: 'http://123.207.32.32:8000/',
// 	// timeout: 3000,
// 	url: 'home/multidata'
// }), axios({
// 	// baseURL: 'http://123.207.32.32:8000/',
// 	// timeout: 3000,
// 	url: 'home/data',
// 	params: {
// 		type: 'sell',
// 		page: 5
// 	}
// })]).then(axios.spread((res1, res2) => {
// 	console.log(res1)
// 	console.log(res2)
// }))

// 6.创建axios实例 解决全局配置不相同问题
// const instance1 = axios.create({
// 	baseURL: 'http://123.207.32.32:8000/',
// 	timeout: 4000,
// 	method: 'get'
// })

// 使用实例
// instance1({
// 	url: 'home/multidata'
// }).then(res => {
// 	console.log(res)
// })

// instance1({
// 	url: 'home/data',
// 	params: {
// 		type: 'pop',
// 		page: 3
// 	}
// }).then(res => {
// 	console.log(res)
// })

// 7.封装request模块
import {request} from './network/request.js'

request({
	url: '/home/multidata'
}).then(res => {
	console.log(res)
}).catch(err => {
	console.log(err)
})