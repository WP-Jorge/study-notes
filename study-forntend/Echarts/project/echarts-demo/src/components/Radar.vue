<script setup lang="ts">
import * as echarts from 'echarts';
import { onMounted, ref } from 'vue';
const radar = ref(null as unknown as HTMLElement);
onMounted(() => {
	const element = echarts.init(radar.value);
	const option = {
		title: {
			text: '雷达图'
		},
		radar: [
			{
				shape: 'circle',
				// 雷达图特有的指示器，用来指定雷达图的多个变量、维度
				indicator: [
					{ name: '蔬菜', max: 6000, min: 1000 },
					{ name: '水果', max: 6000, min: 1000 },
					{ name: '熟食', max: 16000, min: 1000 },
					{ name: '数码', max: 16000, min: 1000 },
					{ name: '家电', max: 26000, min: 1000 },
					{ name: '日化', max: 26000, min: 1000 },
				],
				// 半径
				radius: 120,
				// 坐标轴起始角度
				srartAngle: 180,
				// 指定坐标轴圈数
				splitNumber: 5,
				axisName: {
					formatter: '{value}',
					color: 'pink'
				},
				splitArea: { // 设置分割区效果
					areaStyle: { // 设置分割区样式
						color: ['pink', 'red', 'yellow', 'yellowgreen', 'skyblue', 'orange'],
						shadowColor: 'black',
						shadowBlur: 20
					}
				}
			}
		],
		series: [
			{
				type: 'radar',
				data: [
					{
						value: [4200, 4002, 13402, 12044, 25092, 24980],
						areaStyle: {
							color: 'rgba(255, 255, 0, 0.5)'
						}
					}
				],
				lineStyle: {
					type: 'dashed'
				}
			}
		]
	};
	element.setOption(option);
});
</script>
<template>
	<div id="radar" ref="radar"></div>
</template>
<style lang="scss" scoped>
#radar {
	width: 400px;
	height: 300px;
}
</style>
