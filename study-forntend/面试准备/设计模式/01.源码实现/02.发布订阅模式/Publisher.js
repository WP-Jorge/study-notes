module.exports = class Publisher {
	constructor(name, subHub) {
		this.name = name;
		this.subHub = subHub;
	}
	publish(type, subHub) {
		this.subHub.publish(type, subHub);
	}
};
