export const getRandomNum = () => {
	return (Math.random() + '').split('.')[1];
};

export const getFormatTime = (timestamp: number) => {
	let hours = Math.floor(timestamp / 60 / 60);
	let minutes = Math.floor((timestamp - hours * 60) / 60);
	let seconds = Math.floor(timestamp - hours * 60 * 60 - minutes * 60);
	if (hours) {
		return `${hours.toString().padStart(2, '0')}:${minutes
			.toString()
			.padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
	}
	return `${minutes.toString().padStart(2, '0')}:${seconds
		.toString()
		.padStart(2, '0')}`;
};

export const getTimestamp = (timeStr: string, offsetTime = 0) => {
	let timeReg = /(.*):(.*)\.(.*)/;
	let res = timeReg.exec(timeStr);
	let timestamp = 0;
	if (res) {
		let minutes = parseInt(res[1]);
		let seconds = parseInt(res[2]);
		let milliSeconds = parseInt(res[3]);
		timestamp = minutes * 60 + seconds + milliSeconds / 1000 + offsetTime;
	}
	return timestamp < 0 ? 0 : timestamp;
};

export const formatDuration = (timestamp: number) => timestamp / 1000;
