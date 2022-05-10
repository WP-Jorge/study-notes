/**
 * 观察者对象，每个观察者对象，拥有自己的接收订阅信息的功能以及自己的身份信息
 */

module.exports = class Observer {
	constructor(name) {
		this.name = name;
	}
	update(msg) {
		console.log(`${this.name} 收到通知：${msg}`);
	}
}