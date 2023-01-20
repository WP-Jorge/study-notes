export const debounce = (fn: () => void, delay = 300) => {
	let timer: any = null;
	return (...args: []) => {
		if (timer) {
			clearTimeout(timer);
		}
		timer = setTimeout(() => {
			fn.apply(this, args);
		}, delay);
	};
};

export const throttle = (fn: () => void, delay: number) => {
	let last: any = 0;
	return (...args: []) => {
		let curr = +new Date();
		if (curr - last > delay) {
			fn.apply(this, args);
			last = curr;
		}
	};
};

export const shuffle = (length: number) => {
	let arr = [] as number[];
	for (let i = 0; i < length; i++) {
		arr[i] = i;
	}
	for (let i = arr.length; i; i--) {
		let j = Math.floor(Math.random() * i);
		[arr[i - 1], arr[j]] = [arr[j], arr[i - 1]];
	}
	return arr;
};
