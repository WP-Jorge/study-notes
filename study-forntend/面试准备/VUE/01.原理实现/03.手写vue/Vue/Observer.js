import Dep from './Dep.js';

/** 给数据添加 Dep 依赖 */
export default class Observer {
	constructor(data) {
		this.traverse(data);
	}

	/** 递归遍历 data 中的所有属性 */
	traverse(data) {
		if (!data || typeof data !== 'object') {
			return;
		}
		Object.keys(data).forEach(key =>
			this.defineReactive(data, key, data[key])
		);
	}

	/** 给传入的数据设置 getter 和 setter */
	defineReactive(obj, key, value) {
		this.traverse(value);
		const that = this;
		const dep = new Dep();

		Object.defineProperty(obj, key, {
			enumerable: true,
			configurable: true,
			get() {
				Dep.target && dep.addSub(Dep.target);
				return value;
			},
			set(newValue) {
				if (newValue === value) {
					return;
				}
				value = newValue;
				that.traverse(newValue);
				dep.notify();
			}
		})
	}
}
