import Router from 'koa-router';

import { userValidator } from '../middleware/user.middleware';

import { userController } from '../controller/user.controller';

const { login, loginStatus, refreshLogin } = userController;

const router = new Router({ prefix: '/user' });

router.post('/login', userValidator, login);
router.post('/loginStatus', loginStatus);
router.post('/refreshLogin', refreshLogin);

export { router };
