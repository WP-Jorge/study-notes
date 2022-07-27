<script setup lang="ts">
import * as echarts from 'echarts';
import { onMounted, ref } from 'vue';
const gauge = ref(null as unknown as HTMLElement);
onMounted(() => {
	const element = echarts.init(gauge.value);
	const option = {
		title: {
			text: '仪表盘'
		},
		series: [
			{
				type: 'gauge',
				data: [
					{
						value: 45,
						name: 'speed'
					}
				],
				detail: {
					valueAnimation: true	
				},
				progress: {
					show: true
				}
			}
		]
	};
	element.setOption(option);
});
</script>
<template>
	<div id="gauge" ref="gauge"></div>
</template>
<style lang="scss" scoped>
#gauge {
	width: 400px;
	height: 300px;
}
</style>
