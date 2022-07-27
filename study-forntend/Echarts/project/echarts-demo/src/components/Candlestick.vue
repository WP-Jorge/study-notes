<script setup lang="ts">
import * as echarts from 'echarts';
import { computed, onMounted, ref } from 'vue';
const candlestick = ref(null);
const data = [[20, 34, 10, 38], [40, 35, 30, 50], [31, 38, 33, 44], [38, 15, 5, 42]];
let lineData = computed(() => data.map(item => item[1]));
onMounted(() => {
	const element = echarts.init(candlestick.value as unknown as HTMLElement);
	const option = {
		title: {
			text: 'k线'
		},
		tooltip: {
			trigger: 'axis',
			axisPoint: {
				type: 'cross'
			}
		},
		xAxis: {
			data: ['07-01', '07-02', '07-03', '07-04']
		},
		yAxis: {},
		series: [
			{
				type: 'candlestick',
				data,
				itemStyle: {
					color: 'pink',
					color0: 'yellowgreen',
					borderColor: 'pink',
					borderColor0: 'yellowgreen'
				},
				markPoint: {
					data: [
						{
							type: 'min',
							nmae: '最小值',
							valueDim: 'lowest' // 在那个维度取最小值
						},
						{
							type: 'max',
							nmae: '最大值',
							valueDim: 'highest'
						}
					]
				}
			},
			{
				type: 'line',
				smooth: true,
				data: lineData.value
			}
		]
	};
	element.setOption(option);
});
</script>
<template>
	<div id="candlestick" ref="candlestick"></div>
</template>
<style lang="scss" scoped>
#candlestick {
	width: 400px;
	height: 300px;
}
</style>
