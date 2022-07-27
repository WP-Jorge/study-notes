<script setup lang="ts">
import * as echarts from 'echarts';
import { onMounted, ref } from 'vue';
const elementItem = ref(null as unknown as HTMLElement);
onMounted(() => {
	let element = echarts.init(elementItem.value as unknown as HTMLElement);
	element.setOption({
		title: {
			text: 'template'
		}
	});
});
</script>

<template>
	<div ref="elementItem" id="container">
	</div>
</template>

<style scoped lang="scss">
#container {
	width: 400px;
	height: 300px;
}
</style>
