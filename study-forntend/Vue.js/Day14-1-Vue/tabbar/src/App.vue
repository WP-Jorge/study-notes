<template>
	<div id="app">
		<main-tab-bar></main-tab-bar>
		<router-view></router-view>
	</div>
</template>

<script>
	import MainTabBar from './components/MainTabBar.vue'
	export default {
		name: 'App',
		components: {
			MainTabBar,
		}
	}
</script>

<style>
	/* 引入外部样式 */
	/* 在style中引入用@import */
	@import url("./assets/css/base.css");
</style>
