export const sleep = (time: number) => new Promise(resolve => setTimeout(resolve, time));

export const deepCopy = (obj: any, map = new WeakMap()) => {
	// 1、判断 obj 的类型，如果是对象，则进入内部进行处理
	if (typeof obj === 'object') {
		// 2、如果是 null。就直接返回 null
		if (obj === null) {
			return null;
		}
		// 3、判断对象是数组还是对象，并创建相应容器
		let res = Array.isArray(obj) ? [] : {};
		// 4、处理循环引用
		if (map.has(obj)) {
			return map.get(obj);
		}
		map.set(obj, res);
		// 5、如果是数组，循环深克隆这个数组
		if (Array.isArray(obj)) {
			obj.forEach(item => {
				(res as Array<any>).push(deepCopy(item, map));
			});
			return res;
		}
		// 6、如果是对象，进入对象的内部处理
		if (obj instanceof Object) {
			// 8、如果是普通对象，则深克隆这个对象
			for (let key in obj) {
				if (obj.hasOwnProperty.call(obj, key)) {
					res[key] = deepCopy(obj[key], map);
				}
			}
			return res;
		}
	}
	// 9、如果是函数，则使用正则提取出函数参数与函数体
	if (obj instanceof Function) {
		let paramReg = /(?<=\().+(?=\)\s+{)/;
		let bodyReg = /(?<={)(.|\n)+(?=})/m;
		let funcString = obj.toString();
		if (obj.prototype) {
			let param = paramReg.exec(funcString);
			let body = bodyReg.exec(funcString);
			if (body) {
				if (param) {
					let paramArr = param[0].split(',');
					return new Function(...paramArr, body[0]);
				}
				return new Function(body[0]);
			}
			return null;
		}
		return eval(funcString);
	}
	return obj;
};
