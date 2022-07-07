<script setup lang="ts">
import MusicChartCard from './components/MusicChartCard/index.vue';
import SingerChartCard from './components/SingerChartCard/index.vue';
import AlbumChartCard from './components/AlbumChartCard/index.vue';
import ChartSelectorCard from './components/ChartSelectorCard/index.vue';
import { useMusicStore } from '@/store/music';
const musicStore = useMusicStore();
</script>
<template>
	<div class="chart">
		<ChartSelectorCard />
		<MusicChartCard v-show="musicStore.chartType.id === 'music'" />
		<SingerChartCard v-show="musicStore.chartType.id === 'singer'" />
		<AlbumChartCard v-show="musicStore.chartType.id === 'album'" />
	</div>
</template>
<style lang="scss" scoped></style>
