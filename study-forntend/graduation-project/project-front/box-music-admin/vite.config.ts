import path from 'path';

import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
// import vitePluginImp from 'vite-plugin-imp';

// https://vitejs.dev/config/
export default defineConfig({
	plugins: [
		react()
		// vitePluginImp({
		// 	libList: [
		// 		{
		// 			libName: 'antd',
		// 			style: name => `antd/lib/${name}/style/index.less`
		// 		}
		// 	]
		// })
	],
	// css: {
	// 	preprocessorOptions: {
	// 		less: {
	// 			javascriptEnabled: true
	// 		}
	// 	}
	// },
	resolve: {
		alias: {
			'@': path.resolve(__dirname, 'src')
		}
	}
});
