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

export default defineConfig({
	base: './',
	plugins: [
		vue(),
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
					enabledCollections: ['ep', 'el', 'ant-design', 'ic', 'la']
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
	}
});
