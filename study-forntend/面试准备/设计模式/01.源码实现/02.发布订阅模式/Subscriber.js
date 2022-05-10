module.exports = class Subscriber {
	constructor(name, subHub) {
		this.name = name;
		this.subHub = subHub;
	}
	subscribe(type, cb) {
		this.subHub.subscribe(type, cb);
	}
};