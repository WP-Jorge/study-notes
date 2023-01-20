<script setup lang="ts">
import { useSystemStore } from '@/store/system';
import { storeToRefs } from 'pinia';
import Header from '@/components/content/Header/index.vue';
import Aside from '@/components/content/Aside/index.vue';
import Menu from '@/components/content/Menu/index.vue';
import MusicBar from '@/components/content/MusicBar/index.vue';
import MusicDetail from '@/components/content/MusicDetail/index.vue';
import ContentMexu from './components/common/ContextMenu/index.vue';
import Login from '@/components/content/Login/index.vue';
import { useRoute } from 'vue-router';

const systemStore = useSystemStore();
const route = useRoute();
console.log('🦃🦃route.name', route.name);
const { showSiderMenu, showMain, showMusicDetail } = storeToRefs(systemStore);
// const electronAPI = window.electronAPI;
// electronAPI.getMusicInfo(
// 	'D:/CloudMusic/双笙.flac',
// 	// 'G:/Musics/1/漫长的告白-双笙-162283619.flac',
// 	(musicInfo: any) => {
// 		console.log(musicInfo);
// 	}
// );
// electronAPI.getMsg(
// 	'D:/CloudMusic/双笙.flac',
// 	'D:/CloudMusic/双笙6.flac',
// 	(data: string) => {
// 		console.log(data);
// 	},
// 	(data: string) => {
// 		console.log(data);
// 	},
// 	(data: any) => {
// 		console.log(data);
// 	}
// );
</script>
<template>
	<ContentMexu />
	<el-container>
		<el-header>
			<Header />
		</el-header>
		<el-container>
			<el-aside v-show="showSiderMenu && !showMusicDetail" width="200px">
				<Aside />
			</el-aside>
			<el-main v-show="showMain && !showMusicDetail">
				<Menu />
				<div
					class="content"
					:class="{ 'search-des-main': route.name === 'SearchDes' }">
					<router-view />
				</div>
			</el-main>
			<MusicDetail v-show="showMusicDetail" />
		</el-container>
		<el-footer><MusicBar /></el-footer>
	</el-container>
	<Login />
</template>
<style lang="scss">
@use './assets/global.scss';
.el-container {
	height: 100%;
	.el-header {
		padding: 0;
		-webkit-app-region: drag;
	}
	.el-aside {
		max-height: calc(100vh - 60px - 71px);
		overflow: overlay;
		&::-webkit-scrollbar {
			width: 5px;
			height: 8px;
			background-color: var(--el-color-info-light-9);
		}
		&::-webkit-scrollbar-thumb {
			background-color: transparent;
		}
	}
	.el-aside:hover {
		&::-webkit-scrollbar-thumb {
			background-color: var(--el-color-primary-light-5);
		}
	}
	.el-main {
		padding: 0;
		height: calc(100vh - 60px - 71px);
		min-width: 780px;
		overflow: hidden;
		& > .content {
			padding: 10px;
			overflow: overlay;
			height: calc(100% - 60px);
			&::-webkit-scrollbar {
				width: 5px;
				height: 8px;
				background-color: var(--el-color-info-light-9);
			}
			&::-webkit-scrollbar-thumb {
				background-color: transparent;
			}
		}
		& > .content:hover {
			&::-webkit-scrollbar-thumb {
				background-color: var(--el-color-primary-light-5);
			}
		}
	}
	.search-des-main {
		height: calc(100vh - 60px - 91px) !important;
	}
	.el-footer {
		padding: 0;
		height: 71px;
		border-top: 1px solid var(--el-border-color);
	}
}

.el-popper {
	.user-info-container {
		.title,
		.content {
			border-bottom: 1px solid #e0e0e0;
			padding-bottom: 5px;
			margin-bottom: 5px;
			p {
				position: relative;
				margin-top: 5px;
				font-weight: 500;
				.edit {
					display: none;
					position: absolute;
					right: 0;
					padding: 0 5px;
					cursor: pointer;
					color: var(--el-color-primary);
					font-size: 12px;
					line-height: 19.6px;
				}
			}
			p:hover {
				.edit {
					display: inline-block;
				}
			}
		}
		.footer {
			display: flex;
			justify-content: end;
			margin-top: 10px;
		}
	}
}
</style>
