const Router = require('koa-router');

const { userValidator, bcryptPassword } = require('../middleware/user.middleware')
const { login } = require('../controller/user.controller');

const router = new Router({prefix: '/user'})

router.post('/login', userValidator, bcryptPassword, login);

module.exports = router;