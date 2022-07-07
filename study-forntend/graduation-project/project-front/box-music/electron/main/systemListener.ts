import { BrowserWindow, ipcMain } from 'electron';
import path from 'path';

export const registerSystemListener = (mainWindow: BrowserWindow) => {
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
	ipcMain.on('window-close', async () => {
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
	ipcMain.on('open-modal-window', () => {
		const modalWindow = new BrowserWindow({
			parent: mainWindow,
			// modal: true,
			// icon: path.join(__dirname, '../dist/logo.ico'),
			frame: false,
			webPreferences: {
				nodeIntegration: true,
				contextIsolation: true,
				// allowRunningInsecureContent: false,
				webSecurity: false, // 关闭浏览器安全限制
				preload: path.join(__dirname, '../preload')
			}
		});
		modalWindow.loadURL('http://localhost:3000');
		modalWindow.show();
	});
};
