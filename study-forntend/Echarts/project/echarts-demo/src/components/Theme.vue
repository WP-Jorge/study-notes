<script setup lang="ts">
import { onMounted, ref } from 'vue';
import * as echarts from 'echarts';

const bar = ref(null as unknown as HTMLElement);
onMounted(() => {
	// 1、初始化
	const element = echarts.init(bar.value, 'dark');
	// 2、设置 echarts 数据
	// 设置轴
	const xData = ['美食', '数码', '日化', '蔬菜'];
	const yData = [99, 74, 55, 83];
	// 3、设置配置项
	const option = {
		title: {
			text: 'Theme',
		},
		xAxis: {
		},
		// 把x轴的数据放到y轴中，实现横向
		yAxis: {
			data: xData,
			type: 'category' // 坐标轴的类型，value 数据轴，category 类目轴
		},
		series: [
			{
				name: '销量',
				type: 'bar',
				data: yData,
				barWidth: 20,
				// color: 'pink',
				// 单独设置每个轴的颜色
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
				// 图表的标点
				markPoint: {
					// 标注数据数组，每一项都是一个对象
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
				// 图表的标线
				markLine: {
					data: [
						{
							type: 'average',
							name: '平均值'
						}
					]
				}
			}
		]
	}
	element.setOption(option);
});
</script>
<template>
	<div ref="bar" id="bar"></div>
</template>
<style lang="scss" scoped>
#bar {
	width: 400px;
	height: 300px;
}
</style>
