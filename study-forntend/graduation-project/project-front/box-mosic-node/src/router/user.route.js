const Router = require('koa-router');

const { userValidator } = require('../middleware/user.middleware')
const { login, loginStatus, refreshLogin } = require('../controller/user.controller');

const router = new Router({prefix: '/user'})

router.post('/login', userValidator, login);
router.post('/loginStatus', loginStatus);
router.post('/refreshLogin', refreshLogin);

module.exports = router;