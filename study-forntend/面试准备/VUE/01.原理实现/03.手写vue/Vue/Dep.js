/** 
 * 发布订阅模式
 * 存储所有观察者 Watcher
 * 每个 Watcher 都有一个 update 方法
 * 当数据变化时通知每一个 Watcher 实例，触发 update 方法
 * 
 * 相当于 b 站，每一个 up 主都有一个私人的 b 站管理模块
 */
export default class Dep {
	constructor() {
		// 存储所有观察者
		// 相当于粉丝列表
		this.subs = [];
	}
	/** 
	 * 添加观察者
	 * 
	 * 相当于粉丝点关注，b 站做收集
	 */
	addSub(watcher) {
		if (watcher && watcher.update) {
			this.subs.push(watcher);
		}
	}

	/** 
	 * 发送通知
	 * 
	 * 相当于 up 主有新的视频，b 站通知对应 up 主所有的粉丝，然后收到通知的粉丝可以对这个新的视频进行相关的操作
	 */
	notify() {
		this.subs.forEach(watcher => watcher.update());
	}
}
