<script setup lang="ts">
import * as echarts from 'echarts';
import { onMounted, ref } from 'vue';
const bar = ref(null);
onMounted(() => {
	const element = echarts.init(bar.value as unknown as HTMLElement);
	const xData = ['美食', '数码', '日化', '蔬菜'];
	const yData = [99, 74, 55, 83];
	const option = {
		title: {
			text: '事件',
		},
		xAxis: {
		},
		yAxis: {
			data: xData,
			type: 'category'
		},
		series: [
			{
				name: '销量',
				type: 'bar',
				data: yData,
				barWidth: 20,
				// color: 'pink',
				itemStyle: {
					color: function (params: any) {
						const colorList = [
							'red',
							'pink',
							'yellowgreen',
							'skyblue'
						]
						return colorList[params.dataIndex];
					}
				},
				markPoint: {
					data: [
						{
							type: 'max',
							name: '最大值'
						},
						{
							type: 'min',
							name: '最小值'
						}
					]
				},
				markLine: {
					data: [
						{
							type: 'average',
							name: '平均值'
						}
					]
				}
			},
			{
				name: '数值',
				data: [72, 81, 64, 67],
				type: 'line',
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
	}
	element.setOption(option);
	window.addEventListener('resize', () => {
		element.resize()
	});
	// 第二个参数可以筛选需要添加的事件的图例
	element.on('click', { name: '美食', seriesIndex: 0 }, params => {
		console.log('🦃🦃params', params);
	});
});

</script>
<template>
	<div ref="bar" id="bar"></div>
</template>
<style lang="scss" scoped>
#bar {
	width: 100%;
	height: 300px;
}
</style>
