<script setup lang="ts">
import * as echarts from 'echarts';
import { onMounted, ref } from 'vue';
const line = ref(null);
onMounted(() => {
	const element = echarts.init(line.value as unknown as HTMLElement);
	const xData = ['Mon', 'Tue', 'Wed', 'Thu', 'Fir', 'Sat', 'Sun'];
	const data = [121, 321, 234, 67, 100, 234, 124]
	const option = {
		title: {
			text: '折线图',
			subtext: '折线图-单条'
		},
		xAxis: {
			type: 'category',
			data: xData
		},
		yAxis: {
		},
		series: [
			{
				name: '数值',
				data,
				type: 'line',
				smooth: true,
				// 区域样式
				areaStyle: {},
				markPoint: {
					data: [
						{ type: 'min', name: '最小值' },
						{ type: 'max', name: '最大值' }
					]
				},
				markLine: {
					data: [
						{ type: 'average', name: '平均值' }
					]
				}
			}
		]
	};
	element.setOption(option);
});

</script>
<template>
	<div ref="line" id="line"></div>
</template>
<style lang="scss" scoped>
#line {
	width: 400px;
	height: 300px;
}
</style>
