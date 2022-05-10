<template>
	<div>{{ aboutMsg }}</div>
</template>

<script>
import { ref } from 'vue';

export default {
	setup() {
		let aboutMsg = ref('About');
		return {
			aboutMsg
		};
	}
};
</script>

<style scoped>

</style>
