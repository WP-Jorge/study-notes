<script setup lang="ts">
import { useSystemStore } from '@/store/system';
import { storeToRefs } from 'pinia';
import Header from '@/components/content/Header/index.vue';
import Aside from '@/components/content/Aside/index.vue';
import Menu from '@/components/content/Menu/index.vue';
import MusicBar from '@/components/content/MusicBar/index.vue';
import MusicDetail from '@/components/content/MusicDetail/index.vue';
const systemStore = useSystemStore();
const { showSiderMenu, showMain, showMusicDetail } = storeToRefs(systemStore);
const ipcRenderer = window.ipcRenderer;
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
	<el-container>
		<el-header>
			<Header />
		</el-header>
		<el-container>
			<el-aside v-if="showSiderMenu && !showMusicDetail" width="200px">
				<Aside />
			</el-aside>
			<el-main v-if="showMain && !showMusicDetail">
				<Menu />
				<el-button @click="ipcRenderer.send('open-modal-window')">
					asd
				</el-button>
				<div class="content">
					<router-view />
				</div>
			</el-main>
			<MusicDetail v-if="showMusicDetail" />
		</el-container>
		<el-footer><MusicBar /></el-footer>
	</el-container>
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
	.el-footer {
		padding: 0;
		height: 71px;
		border-top: 1px solid var(--el-border-color);
	}
}
</style>
