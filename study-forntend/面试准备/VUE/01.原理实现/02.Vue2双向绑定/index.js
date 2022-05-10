import Vue from './vue.js';

const vm = new Vue({
	el: '#app',
	data: {
		msg: 'Hello world',
		name: 'Jorge',
		count: '100',
		testHtml: '<ul><li>v-html 渲染的</li></ul>'
	},
	methods: {
		handler() {
			alert(this.msg);
		}
	}
});

console.log(vm);