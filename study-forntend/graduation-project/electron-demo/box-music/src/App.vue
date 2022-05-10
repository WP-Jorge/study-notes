<template>
	<div>
		<router-link to="/">home 页面</router-link> | <router-link to="/admin">admin 页面</router-link> |
		<router-link to="/login">login 页面</router-link>
	</div>
	<router-view />
</template>
<script setup lang="ts"></script>
<style lang="scss">
@import url('./assets/global.scss');
</style>
