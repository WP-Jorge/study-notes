import path from 'path';
import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';
import AutoImport from 'unplugin-auto-import/vite';
import Components from 'unplugin-vue-components/vite';
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers';
import ElementPlus from 'unplugin-element-plus/vite';
import Icons from 'unplugin-icons/vite';
import IconsResolver from 'unplugin-icons/resolver';
import EslintPlugin from 'vite-plugin-eslint';
import electron from 'vite-plugin-electron';
import renderer from 'vite-plugin-electron/renderer';

export default defineConfig({
	base: './',
	plugins: [
		vue(),
		electron({
			main: {
				entry: 'electron/main/main.ts'
			},
			preload: {
				// Must be use absolute path, this is the limit of rollup
				input: path.join(__dirname, './electron/preload/preload.ts')
			}
		}),
		renderer(),
		EslintPlugin({
			failOnError: false
		}),
		ElementPlus({
			useSource: true
		}),
		AutoImport({
			resolvers: [ElementPlusResolver()], // 这里使用ElementPlus组件库
			imports: ['vue'],
			eslintrc: {
				enabled: false // Default `false`
			}
		}),
		Components({
			resolvers: [
				ElementPlusResolver(),
				// 自动注册图标组件
				IconsResolver({
					enabledCollections: [
						'ep',
						'el',
						'ant-design',
						'ic',
						'la',
						'akar-icons',
						'ion',
						'system-uicons',
						'material-symbols'
					]
				})
			]
		}),
		Icons({
			autoInstall: true
		})
	],
	resolve: {
		alias: {
			'@': path.resolve(__dirname, 'src')
		}
	},
	build: {
		rollupOptions: {
			output: {
				format: 'es'
			}
		}
	}
});
