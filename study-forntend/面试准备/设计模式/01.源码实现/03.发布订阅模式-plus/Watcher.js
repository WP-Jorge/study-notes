export default class Watcher {
	constructor(name, cb) {
		this.name = name;
		this.cb = cb;
	}

	update(msg) {
		this.cb(`${this.name} 接收到了新内容：${msg}`);
	}
};
