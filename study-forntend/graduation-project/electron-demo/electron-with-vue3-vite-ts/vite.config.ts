import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';
import eslintPlugin from 'vite-plugin-eslint';

const path = require('path');

// https://vitejs.dev/config/
export default defineConfig({
	base: './',
	plugins: [vue(), eslintPlugin()],
	resolve: {
		alias: {
			'@': path.resolve(__dirname, 'src')
		}
	}
});
