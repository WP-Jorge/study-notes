module.exports = class SubHub {
	constructor() {
		this.messages = [];
		this.subscribers = [];
	}
	publish(type, content) {
		if (!this.messages[type]) {
			this.messages[type] = [];
		}
		this.messages[type].push(content);
	}
	subscribe(type, cb) {
		if (!this.subscribers[type]) {
			this.subscribers[type] = [];
		}
		this.subscribers[type].push(cb);
	}
	notify(type) {
		const messages = this.messages[type];
		const subscribers = this.subscribers[type] || [];
		subscribers.forEach((cb, index) => {
			cb.call(this, messages[index]);
		});
	}
};
