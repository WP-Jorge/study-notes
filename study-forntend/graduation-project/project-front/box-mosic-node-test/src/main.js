const {APP_PORT, APP_HOST} = require('./config/config.default');
const app = require('./app');

app.listen(APP_PORT, () => {
	console.log(`server is running on ${APP_HOST}:${APP_PORT}`);
});