import Observer from './Observer.js';
import Compiler from './Compiler.js';

export default class Vue {
	constructor(options = {}) {
		this.$options = options;
		this.$methods = options.methods;
		this.$data = options.data;

		// 获取根元素，并存储到 Vue 实例，简单检查一下传入的 el 是否合法
		this.initRootElement(options);

		// 利用 Object.defineProperty 将 data 属性注入到 Vue 实例中
		this._proxyData(this.$data);

		// 实例化 Observer 对象，监听数据变化
		new Observer(this.$data);

		// 实例化 Compiler 对象，解析指令和模板表达式
		new Compiler(this);
	}

	/**
	 * 获取根元素，并存储到 Vue 实例，简单检查一下传入的 el 是否合法
	 */
	initRootElement(options) {
		if (typeof options.el === 'string') {
			this.$el = document.querySelector(options.el);
		} else if (options.el instanceof HTMLElement) {
			this.$el = options.el;
		}
		if (!this.$el) {
			throw new Error(
				'传入的 el 不合法，请传入 css selector 或者 HTMLElement'
			);
		}
	}

	/**
	 * 利用 Object.defineProperty 将 data 属性注入到 Vue 实例中
	 */
	_proxyData(data) {
		Object.keys(data).forEach(key => {
			Object.defineProperty(this, key, {
				enumable: true,
				configurable: true,
				get() {
					return data[key];
				},
				set(newValue) {
					if (data[key] === newValue) {
						return;
					}
					data[key] = newValue;
				}
			});
		});
	}
}
