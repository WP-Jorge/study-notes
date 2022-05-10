const {write, read, readdir} = require('myrw')

readdir('../').then(res => {
	console.log(res);
})