import path from 'path';
const isDevelopment = process.env.NODE_ENV === 'development';

export const registerListener = (mainWindow: any) => {
	// 设置窗口最大化
	mainWindow.maximize();
	mainWindow.autoHideMenuBar = false;
	// 加载 index.html
	mainWindow.loadURL(
		isDevelopment
			? 'http://localhost:3000'
			: `file://${path.join(__dirname, '../index.html')}`
	);

	// 打开开发工具
	if (isDevelopment) {
		mainWindow.webContents.openDevTools();
	}

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
};
