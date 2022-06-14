import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import { createPinia } from 'pinia';

createApp(App)
	.use(createPinia() as any)
	.use(router as any)
	.mount('#app');
