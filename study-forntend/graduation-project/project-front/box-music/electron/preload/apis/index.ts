import { useIpcRenderer } from '@vueuse/electron';

const { contextBridge } = require('electron');
const ipcRenderer = useIpcRenderer();

import * as ffmpegApis from './ffmpegApis';
import * as storeApis from './storeApis';
import * as messageApis from './messageApis';
import * as utilApis from './utilApis';
import * as fileApis from './fileApis';

contextBridge.exposeInMainWorld('ipcRenderer', ipcRenderer);
contextBridge.exposeInMainWorld('electronApis', {
	...ffmpegApis,
	...storeApis,
	...messageApis,
	...utilApis,
	...fileApis
});
