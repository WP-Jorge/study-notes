const fs = require('fs');

const Router = require('koa-router')
let router = new Router();

fs.readdirSync(__dirname).forEach(fileName => {
	if (fileName !== 'index.js') {
		let route = require('./' + fileName);
		router.use(route.routes());
	}
});

module.exports = router;
