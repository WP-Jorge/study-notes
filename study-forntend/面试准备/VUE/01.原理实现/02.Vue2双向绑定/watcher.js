import Dep from './dep.js';

export default class Watcher {
	/**
	 * @param {*} vm vue 实例
	 * @param {*} key data 中的属性名
	 * @param {*} cb 负责更新视图的回调函数
	 */
	constructor(vm, key, cb) {
		this.vm = vm;
		this.key = key;
		this.cb = cb;

		Dep.target = this;
		// 这里会触发 get 方法，在 get 方法里进行一些操作
		this.oldValue = vm[key];
		Dep.target = null;
	}

	/** 当数据更新时更新视图 */
	update() {
		let newValue = this.vm[this.key];
		if (newValue !== this.oldValue) {
			this.cb(newValue);
		}
	}
}
