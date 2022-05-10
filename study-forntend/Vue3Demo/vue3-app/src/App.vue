<template>
	<div>{{ msg }}</div>
	<router-link to="/home">home </router-link>
	<router-link to="/about"> about</router-link>
	<router-view/>
</template>

<script>
import {
	ref
} from 'vue';

export default {
	setup() {
		let msg = ref('Vue3-app');
		return {
			msg
		};
	}
};
</script>

<style lang="scss">

</style>
