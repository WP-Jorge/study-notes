import fs from 'fs';

import Router from 'koa-router';
let router = new Router();

fs.readdirSync(__dirname).forEach((fileName: string) => {
	if (fileName !== 'index.js') {
		let route = require('./' + fileName);
		// console.log(route.router());

		router.use(route.router.routes());
	}
});

export { router };
