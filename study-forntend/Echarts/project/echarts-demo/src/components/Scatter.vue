<script setup lang="ts">
import * as echarts from 'echarts';
import { onMounted, ref } from 'vue';

const scatter = ref(null);
onMounted(() => {
	const element = echarts.init(scatter.value as unknown as HTMLElement);
	const option = {
		title: {
			text: '散点图'
		},
		tooltip: {},
		xAxis: {},
		yAxis: {},
		series: [
			{
				type: 'scatter',
				data: [
					[9.0, 7.04],
					[18.07, 4.33], [3.0, 9.65],
					[9.05, 8.23], [18.0, 9.76], [15.0, 7.56], [23.4, 5.31], [10.1, 7.47], [16.0, 8.26], [12.7, 3.53], [9.35, 7.2], [7.4, 8.2], [3.07, 4.82], [18.2, 6.83], [2.02, 4.47], [1.05, 3.33], [4.05, 4.96], [6.03, 7.24], [17.0, 6.55], [12.0, 8.84], [8.18, 5.82], [6.32, 5.68],],
				symbolSize: 15,
				color: 'red',
				emphasis: {
					itemStyle: {
						shadowColor: 'skyblue',
						shadowBlur: 20,
						borderWidth: 5,
						borderColor: 'yellowgreen'
					}
				}
			}
		]
	};
	element.setOption(option);
});
</script>
<template>
	<div id="scatter" ref="scatter"></div>
</template>
<style lang="scss" scoped>
#scatter {
	width: 400px;
	height: 300px;
}
</style>
