export default class Observer {
	constructor(name, dep) {
		this.name = name;
		this.dep = dep;
	}

	publish(msg) {
		this.dep.notify(`${this.name} 发布了新内容：${msg}`);
	}
}