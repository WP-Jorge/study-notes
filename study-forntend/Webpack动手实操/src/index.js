import { demo } from 'js/demo.js';
import Vue from 'vue';
import App from '@/App';

// hmr：在 devServer 中开启 hot: true 后在下面配置哪些模块需要开启热更新 (js 需要设置，而 .vue 文件就不需要设置了)
if (module.hot) {
	module.hot.accept(['js/demo'], args => {
		console.log('demo.js 热更新了', args);
	});
}
console.log(123456);
demo();

new Vue({
	render: h => h(App)
}).$mount('#app');
