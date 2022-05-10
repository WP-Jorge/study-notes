import Observer from "./observer.js";
import Compiler from "./compiler.js";

/**
 * 包括 vue 的构造函数、接受的各种配置参数等等
 */
export default class Vue {
	constructor(options = {}) {
		this.$options = options;
		this.$data = options.data;
		this.$methods = options.methods;

		// 初始化 #app 元素
		this.initRootElement(options);
		// 利用 Object.defineProperty 将 data 中的 属性注入到 vue 实例上
		this._proxyData(this.$data);
		// 实例化 observer 实例，监听数据变化
		new Observer(this.$data);
		// 实例化 compiler 对象，解析模板
		new Compiler(this);
	}

	/**
	 * 获取根元素并存储到 vue 实例中，简单检查一下传入的 el 是否合法
	 */
	initRootElement(options) {
		if (typeof options.el === 'string') {
			this.$el = document.querySelector(options.el);
		} else if (options.el instanceof HTMLElement) {
			this.$el = options.el;
		}
		if (!this.$el) {
			throw new Error('传入的 el 不合法，请传入合法的 css selector 或 HTMLElement');
		}
	}

	/**
	 * 利用 Object.defineProperty 将 data 中的 属性注入到 vue 实例上
	 */
	_proxyData(data) {
		Object.keys(data).forEach(key => {
			Object.defineProperty(this, key, {
				enumerable: true,
				configurable: true,
				get() {
					return data[key];
				},
				set(newValue) {
					if (newValue !== data[key]) {
						data[key] = newValue;
					}
				}
			});
		});
	}
}