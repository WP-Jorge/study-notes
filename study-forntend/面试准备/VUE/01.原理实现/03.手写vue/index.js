import Vue from './vue/Vue.js';

const vm = new Vue({
	el: '#app',
	data: {
		msg: 'Hello World',
		count: '100',
		testHtml: '<ul><li>这里是 ul 渲染出来的</li></ui>'
	},
	methods: {
		handler() {
			console.log('🦃🦃111', 111);
		}
	}
});

console.log('🦃🦃vm', vm);
