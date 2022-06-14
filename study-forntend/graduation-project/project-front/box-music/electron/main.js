// 控制应用生命周期和创建原生浏览器窗口的模组
const { app, BrowserWindow, ipcMain } = require('electron');
const path = require('path');

const isDevelopment = process.env.NODE_ENV === 'development';

let mainWindow = null;

const getMaximized = () => {
	return mainWindow.isMaximized();
};

function createWindow() {
	// 关闭安全策略提示
	process.env['ELECTRON_DISABLE_SECURITY_WARNINGS'] = 'true';

	// 创建浏览器窗口
	mainWindow = new BrowserWindow({
		minWidth: 980,
		minHeight: 600,
		show: false, // 不显示窗口
		icon: path.join(__dirname, '../dist/logo.ico'),
		frame: false,
		webPreferences: {
			nodeIntegration: true,
			contextIsolation: true,
			// allowRunningInsecureContent: false,
			webSecurity: false, // 关闭浏览器安全限制
			preload: path.join(__dirname, 'preload.js')
		}
	});

	// 设置窗口最大化
	mainWindow.maximize();
	mainWindow.autoHideMenuBar = false;
	mainWindow.show();
	// 加载 index.html
	mainWindow.loadURL(
		isDevelopment
			? 'http://localhost:3000'
			: `file://${path.join(__dirname, '../dist/index.html')}`
	);

	// 打开开发工具
	// if (isDevelopment) {
	mainWindow.webContents.openDevTools();
	// }

	// 自定义监听
	mainWindow.on('maximize', () => {
		mainWindow.webContents.send('window-max', true);
	});
	mainWindow.on('unmaximize', () => {
		mainWindow.webContents.send('window-max', false);
	});
	mainWindow.on('enter-full-screen', () => {
		mainWindow.webContents.send('window-screen', true);
	});
	mainWindow.on('leave-full-screen', () => {
		mainWindow.webContents.send('window-screen', false);
	});

	// 窗口最小化
	ipcMain.on('window-min', () => {
		mainWindow.minimize();
	});
	// 窗口最大化
	ipcMain.on('window-change', () => {
		if (mainWindow.isMaximized()) {
			mainWindow.restore();
		} else {
			mainWindow.maximize();
		}
	});
	// 窗口关闭
	ipcMain.on('window-close', () => {
		mainWindow.close();
	});
	// 全屏
	ipcMain.on('window-fullscreen', () => {
		mainWindow.setFullScreen(true);
	});
	ipcMain.on('window-fullscreen-exit', () => {
		console.log('window-fullscreen-exit');
		mainWindow.setFullScreen(false);
	});
	//配置ESC键退出全屏
	// globalShortcut.register('F11', () => {
	// 	mainWindow.setFullScreen(false);
	// });
}

// 这段程序将会在 Electron 结束初始化
// 和创建浏览器窗口的时候调用
// 部分 API 在 ready 事件触发后才能使用。
app.whenReady().then(async () => {
	if (isDevelopment) {
		try {
			let installer = await import('electron-devtools-installer');
			let res = await installer.default.default(installer.VUEJS3_DEVTOOLS);
			console.log(`已安装：${res}`);
		} catch (err) {
			console.log(`安装 vue-devtools: `, err);
		}
	}

	ipcMain.handle('isMaximized', getMaximized);

	createWindow();

	app.on('activate', function () {
		// 通常在 macOS 上，当点击 dock 中的应用程序图标时，如果没有其他
		// 打开的窗口，那么程序会重新创建一个窗口。
		if (BrowserWindow.getAllWindows().length === 0) createWindow();
	});
});

// 除了 macOS 外，当所有窗口都被关闭的时候退出程序。 因此，通常对程序和它们在
// 任务栏上的图标来说，应当保持活跃状态，直到用户使用 Cmd + Q 退出。
app.on('window-all-closed', function () {
	if (process.platform !== 'darwin') app.quit();
});

// 在这个文件中，你可以包含应用程序剩余的所有部分的代码，
// 也可以拆分成几个文件，然后用 require 导入。

// app.on('ready', async () => {

// 	// import('electron-devtools-installer').then(installer => {
// 	// 	installer.default
// 	// 		.default(installer.VUEJS3_DEVTOOLS.id)
// 	// 		.then(name => console.log(`已安装: ${name}`))
// 	// 		.catch(err => console.log('无法安装 vue-devtools: \n', err));
// 	// });
// });
