import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import { createPinia } from 'pinia';

// const electronApis = window.electronApis;

// electronApis.getMusicInfo(
// 	'G:/DownLoad/download/Moukhna Tsahan(Feat. Bavaush).mp3',
// 	(musicInfo: any) => {
// 		console.log('🦃🦃musicInfo', musicInfo);
// 	}
// );

createApp(App)
	.use(createPinia() as any)
	.use(router as any)
	.mount('#app');
