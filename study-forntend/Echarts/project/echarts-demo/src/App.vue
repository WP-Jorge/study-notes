<script setup lang="ts">
import { provide } from 'vue';
import * as echarts from 'echarts';
import { echartsKey } from './type/type';

import BaseOption from './components/BaseOption.vue';
import Bar from './components/Bar.vue';
import Pie from './components/Pie.vue';
import Line from './components/Line.vue';
import LineMany from './components/LineMany.vue';
import Scatter from './components/Scatter.vue';
import Candlestick from './components/Candlestick.vue';
import Radar from './components/Radar.vue';
import Gauge from './components/Gauge.vue';
import DataZoom from './components/DataZoom.vue';
import Dataset from './components/Dataset.vue';
import Theme from './components/Theme.vue';
import MyTheme from './components/MyTheme.vue';
import Loading from './components/Loading.vue';
import Animation from './components/Animation.vue';
import AutoResize from './components/AutoResize.vue';
import Event from './components/Event.vue';
provide(echartsKey, echarts);
</script>

<template>
	<div class="app">
		<BaseOption />
		<Bar />
		<Pie />
		<Line />
		<LineMany />
		<Scatter />
		<Candlestick />
		<Radar />
		<Gauge />
		<DataZoom />
		<Dataset />
		<Theme />
		<MyTheme />
		<Loading />
		<Animation />
		<AutoResize />
		<Event />
	</div>
</template>

<style scoped lang="scss">
.app {
	display: flex;
	flex-wrap: wrap;
}
</style>
