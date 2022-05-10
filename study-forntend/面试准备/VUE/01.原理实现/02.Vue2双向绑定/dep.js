/**
 * 发布订阅模式
 * 存储所有观察者：watcher
 * 每个 watcher 都有一个 update 方法
 * 通知 subs 中的 每个 watcher 实例，触发 update 方法
 */

export default class Dep {
	constructor() {
		// 存储所有的观察者
		this.subs = [];
	}

	/** 添加观察者 */
	addSub(watcher) {
		if (watcher && watcher.update) {
			this.subs.push(watcher);
		}
	}

	/** 发送通知 */
	notify() {
		this.subs.forEach(watcher => {
			watcher.update();
		});
	}
}