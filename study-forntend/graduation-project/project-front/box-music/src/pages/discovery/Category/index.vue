<script setup lang="ts">
import CategorySelectorCard from './components/CategorySelectorCard/index.vue';
import CategoryMusicCard from './components/CategoryMusicCard/index.vue';
import { useMusicStore } from '@/store/music';
import { usePlaylistStore } from '@/store/playlist';
import CategoryPlaylistCard from './components/CategoryPlaylistCard/index.vue';
const musicStore = useMusicStore();
const playlistStore = usePlaylistStore();
</script>
<template>
	<div class="category">
		<CategorySelectorCard />
		<CategoryPlaylistCard v-show="playlistStore.category.categoryId" />
		<CategoryMusicCard v-show="musicStore.category.categoryId" />
	</div>
</template>
<style lang="scss" scoped></style>
