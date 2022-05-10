/**
 * 被观察者对象，负责收集观察者，在发生信息更新的时候通知观察者
 */

module.exports = class Subject {
	constructor() {
		this.observers = [];
	}
	addObserver(observer) {
		if (Array.isArray(observer)) {
			this.observers.push(...observer);
		} else {
			this.observers.push(observer);
		}
	}
	removeObserver(observer) {
		let index = this.observers.findIndex(item => item === observer);
		if (index !== -1) {
			this.observers.splice(index, 1);
		}
	}
	notify(msg) {
		this.observers.forEach(item => {
			item.update(msg);
		});
	}
	showObservers() {
		return this.observers;
	}
};
