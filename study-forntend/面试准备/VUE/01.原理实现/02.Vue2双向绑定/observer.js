import Dep from './dep.js';

export default class Observer {
	constructor(data) {
		// 初始化的时候给递归遍历所有的 data 属性
		this.traverse(data);
	}
	/** 递归遍历 data 中的所有属性 */
	traverse(data) {
		if (!data || typeof data !== 'object') {
			return;
		}
		Object.keys(data).forEach(key => {
			this.defineReactive(data, key, data[key]);
		});
	}

	/** 给传入的数据设置 getter/setter */
	defineReactive(obj, key, val) {
		// 递归遍历
		this.traverse(val);
		const that = this;
		const dep = new Dep();

		Object.defineProperty(obj, key, {
			configurable: true,
			enumerable: true,
			get() {
				Dep.target && dep.addSub(Dep.target);
				return val;
			},
			set(newValue) {
				if (newValue !== val) {
					val = newValue;
					that.traverse(newValue); // 设置的时候可能是一个对象
					dep.notify();
				}
			}
		});
	}
}
