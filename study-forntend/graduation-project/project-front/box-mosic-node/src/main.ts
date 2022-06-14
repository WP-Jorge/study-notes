import { APP_PORT, APP_HOST } from './config/config.default';
import { app } from './app';

app.listen(APP_PORT, () => {
	console.log(`server is running on ${APP_HOST}:${APP_PORT}`);
});
