// 控制应用生命周期和创建原生浏览器窗口的模组
import { app, BrowserWindow } from 'electron';
import path from 'path';
import { registerListener } from './listener';
import { registerSystemListener } from './systemListener';
import { registerDownloadListener } from './downloadListener';

const isDevelopment = process.env.NODE_ENV === 'development';

function createWindow() {
	// 关闭安全策略提示
	process.env['ELECTRON_DISABLE_SECURITY_WARNINGS'] = 'true';

	// 创建浏览器窗口
	const mainWindow = new BrowserWindow({
		minWidth: 1080,
		minHeight: 600,
		show: false, // 不显示窗口
		icon: path.join(
			__dirname,
			isDevelopment ? '../../public/logo.ico' : '../logo.ico'
		),
		frame: false,
		webPreferences: {
			nodeIntegration: true,
			contextIsolation: true,
			// allowRunningInsecureContent: false,
			webSecurity: false, // 关闭浏览器安全限制
			preload: path.join(__dirname, './preload.js')
		}
	});

	// 注册自定义监听器
	registerListener(mainWindow);
	// 注册系统监听器
	registerSystemListener(mainWindow);
	// 注册下载监听器
	registerDownloadListener(mainWindow);

	return mainWindow;
}

// 这段程序将会在 Electron 结束初始化
// 和创建浏览器窗口的时候调用
// 部分 API 在 ready 事件触发后才能使用。
app.whenReady().then(async () => {
	if (isDevelopment) {
		let installExtension = require('electron-devtools-installer');
		try {
			let res = await installExtension.default(installExtension.VUEJS_DEVTOOLS);
			console.log(`安装 vue-devtools 成功：${res}`);
		} catch (err) {
			console.log(`安装 vue-devtools 失败： `, err);
		}
	}

	createWindow();

	app.on('activate', function () {
		// 通常在 macOS 上，当点击 dock 中的应用程序图标时，如果没有其他
		// 打开的窗口，那么程序会重新创建一个窗口。
		if (BrowserWindow.getAllWindows().length === 0) createWindow();
	});

	// 除了 macOS 外，当所有窗口都被关闭的时候退出程序。 因此，通常对程序和它们在
	// 任务栏上的图标来说，应当保持活跃状态，直到用户使用 Cmd + Q 退出。
	// app.on('window-all-closed', function () {
	// 	if (process.platform !== 'darwin') {
	// 		mainWindow.destroy();
	// 		process.kill(process.pid, 'SIGKILL');
	// 	}
	// });
});

// app.on('will-quit', async e => {
// 	console.log('[app] will-quit');
// 	e.preventDefault();
// });

process.on('uncaughtException', e => {
	console.error(e);
});

// 在这个文件中，你可以包含应用程序剩余的所有部分的代码，
// 也可以拆分成几个文件，然后用 require 导入。
