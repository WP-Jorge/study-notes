import Dep from './Dep.js';

/** 
 * 观察者
 * 
 * 相当于粉丝
 */
export default class Watcher {
	/**
	 *
	 * @param {*} vm vue 实例
	 * @param {*} key data 中的属性名
	 * @param {*} cb 负责更新视图的回调函数
	 */
	constructor(vm, key, cb) {
		this.vm = vm;
		this.key = key;
		this.cb = cb;

		Dep.target = this;

		// 触发 get 方法，在 get 方法里会去做一些操作
		this.oldValue = vm[key];

		Dep.target = null;
	}

	/** 
	 * 当数据变化的时候更新视图
	 * 
	 * 相当于 up 主发布新的视频，粉丝收到通知，然后粉丝对这个视频的相关处理
	 */
	update() {
		let newValue = this.vm[this.key];
		if (this.oldValue === newValue) {
			return;
		}
		this.cb(newValue);
	}
}

// watcher 初始化获取 oldValue 的时候，回去做一些什么操作？
// 通过 vm[key] 获取的oldValue 前，为什么要将当前的实例挂载到 Dep 上？或者之后为什么有赋值 null？
// update 是什么时候触发的？
