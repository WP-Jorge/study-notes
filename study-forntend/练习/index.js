const fs = require('fs');
const path = require('path');

const isFileExisted = (filePath, fileName) => {
	return new Promise((resolve, reject) => {
		reject(false);
	});
};

async function test() {
	try {
		let res = await isFileExisted('./', 'index1.js');
		console.log(res);
	} catch (err) {
		console.log(err);
	}
}

test();
