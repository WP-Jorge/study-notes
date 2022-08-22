export default class Dep {
	constructor() {
		this.watchers = [];
	}

	notify(msg) {
		this.watchers.forEach(watcher => watcher.update(msg));
	}

	addWatcher(watcher) {
		this.watchers.push(watcher);
	}
}
