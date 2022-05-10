let axios = require('axios');
let fs = require('fs');
let path = require('path');
let write = require('../write');

(async function() {
	downloadFile('http://59.110.45.28/m/api/url/yqd/id/2028aKuov8LXlv0JedS35RANBHjTGmqipbUry-9bkNr9AbrIFm7O/format/999000', './aa', 'asd.flac')
})();

async function downloadFile(url, filepath, name) {
	await write.fsdirSync(filepath);
	const mypath = path.resolve(filepath, name);
	const writer = fs.createWriteStream(mypath);
	console.log(url);
	const response = await axios({
		url,
		method: 'GET',
		responseType: 'stream'
	});
	response.data.pipe(writer);
	return new Promise((resolve, reject) => {
		writer.on('finish', resolve);
		writer.on('error', reject);
	});
}